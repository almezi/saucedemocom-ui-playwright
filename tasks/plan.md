# Implementation Plan: SauceDemo Login and Checkout E2E (Cucumber BDD)

## Overview

Provide automated browser test coverage for SauceDemo authentication and end-to-end checkout (`Sauce Labs Onesie`) using a **Cucumber BDD** framework integrated with **Playwright Java**, **JUnit 5**, and the **Page Object Model (POM)** pattern. Test scenarios are specified in human-readable Gherkin feature files backed by maintainable, auto-waiting step definitions.

## Existing Project Context

- `pom.xml` configures Playwright Java 1.47.0 and JUnit 5.10.3 (to be augmented with `cucumber-java` and `cucumber-junit-platform-engine`).
- `src/test/java/com/almezi/saucedemocom/` contains base fixtures, configuration, and initial page objects (`LoginPage`, `InventoryPage`).
- Test execution is triggered via standard Maven lifecycle (`mvn test`).

## Architecture & Design Decisions

1. **Cucumber BDD with Gherkin Specifications**: Specify test flows as declarative Given-When-Then scenarios in `.feature` files under `src/test/resources/features/` to maximize readability and living documentation utility.
2. **Page Object Model (POM) Integration**: Step definition classes delegate all web element locators and UI actions directly to dedicated Page classes in `pages/`, preventing locator duplication and lowering maintenance overhead.
3. **Scenario-Level BrowserContext Isolation**: Cucumber `@Before` and `@After` hooks manage fresh `BrowserContext` and `Page` instances for each scenario to prevent state leakage and guarantee independent test execution.
4. **Semantic Locators & Web-First Assertions**: Prioritize accessible roles, labels, placeholders, and stable `data-test` attributes alongside Playwright's `assertThat()` auto-waiting assertions, eliminating brittle sleeps or hardcoded waits.
5. **Centralized Configuration & State Sharing**: Store environment constants and credentials in `TestConfig.java` and use a shared `TestContext` or Cucumber dependency injection for clean state passing between steps.

## File Strategy

```
src/test/
├── java/com/almezi/saucedemocom/
│   ├── config/
│   │   └── TestConfig.java             # Environment URLs, test credentials, and global flags
│   ├── context/
│   │   └── TestContext.java            # Scenario state container and Playwright instance holder
│   ├── hooks/
│   │   └── Hooks.java                  # Cucumber @Before and @After lifecycle hooks (context setup/teardown)
│   ├── pages/
│   │   ├── LoginPage.java              # Login page actions and locators
│   │   ├── InventoryPage.java          # Products listing and cart navigation
│   │   ├── CartPage.java               # Shopping cart verification
│   │   ├── CheckoutStepOnePage.java    # Customer details form
│   │   ├── CheckoutStepTwoPage.java    # Order overview and calculations
│   │   └── CheckoutCompletePage.java   # Order dispatch confirmation
│   ├── steps/
│   │   ├── LoginSteps.java             # Step definitions for authentication flows
│   │   ├── InventorySteps.java         # Step definitions for catalog and cart additions
│   │   ├── CartSteps.java              # Step definitions for cart verification
│   │   └── CheckoutSteps.java          # Step definitions for customer info, overview, and completion
│   └── runners/
│       └── CucumberTestRunner.java     # Cucumber JUnit Platform Suite runner configuration
└── resources/
    ├── cucumber.properties             # Cucumber output and plugin configuration
    └── features/
        ├── login.feature               # Gherkin scenarios for login (success, locked-out)
        └── checkout.feature            # Gherkin scenario for end-to-end Onesie checkout
```

## Test Strategy

1. **Execution Lifecycle**:
   - Class/Suite level: Initialize `Playwright` and `Browser` singletons.
   - Scenario level: Fresh `BrowserContext` and `Page` created in `@Before` hook and cleanly closed in `@After` hook.
   - Failure diagnostics: Attach screenshots and Playwright trace files to Cucumber scenario reports on step failure.
2. **Locator Hierarchy**:
   - Level 1: Semantic roles and accessible names (`page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"))`).
   - Level 2: User-visible text and placeholders (`page.getByPlaceholder("Username")`).
   - Level 3: Stable test identifiers (`page.locator("[data-test='shopping-cart-badge']")`).
   - Disallowed: Brittle absolute XPath selectors, class-attribute chains, or index-based navigation.
3. **Synchronization & Assertions**:
   - Use Playwright web-first assertions (`com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat`).
   - Rely strictly on built-in auto-waiting and retry mechanisms; avoid `Thread.sleep()`.

## BDD Feature Scenarios

### Feature: User Authentication (`login.feature`)

```gherkin
Feature: User Authentication

  Scenario: Successful login with standard credentials
    Given the user is on the SauceDemo login page
    When the user logs in with valid standard credentials
    Then the user should be directed to the inventory page
    And the products catalog header should be visible

  Scenario: Login failure with locked out user
    Given the user is on the SauceDemo login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then an error message stating "Sorry, this user has been locked out." should be displayed
```

### Feature: End-to-End Checkout (`checkout.feature`)

```gherkin
Feature: End-to-End Checkout

  Scenario: Successfully ordering a Sauce Labs Onesie
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    Then the cart should contain 1 item of "Sauce Labs Onesie"
    When the user proceeds to checkout
    And fills in customer information with first name "John", last name "Doe", and postal code "12345"
    And continues to the checkout overview
    Then the order summary should show "Sauce Labs Onesie" with quantity 1
    When the user finishes the checkout
    Then the order confirmation message "Thank you for your order!" should be displayed
```

## Task List

### Phase 1: Test Foundation & BDD Setup

- [x] Task 1: Establish Playwright test lifecycle, configuration, and initial Page Objects (`LoginPage`, `InventoryPage`).
- [ ] Task 2: Configure Cucumber BDD dependencies (`cucumber-java`, `cucumber-junit-platform-engine`), `cucumber.properties`, and `CucumberTestRunner`.
- [ ] Task 3: Create Cucumber lifecycle `Hooks` and map `login.feature` to `LoginSteps` using existing Page Objects.

### Checkpoint: Login BDD

- [ ] `mvn test` executes Cucumber runner and passes all scenarios in `login.feature`.
- [ ] Browser contexts isolate cleanly per scenario with zero resource leaks.

### Phase 2: Checkout Flow & Complete POM Implementation

- [ ] Task 4: Implement remaining Page Objects (`CartPage`, `CheckoutStepOnePage`, `CheckoutStepTwoPage`, `CheckoutCompletePage`).
- [ ] Task 5: Implement `checkout.feature` and corresponding step definitions (`InventorySteps`, `CartSteps`, `CheckoutSteps`).

### Checkpoint: Complete BDD Flow

- [ ] `mvn test` runs all feature files (`login.feature`, `checkout.feature`) with all scenarios passing.
- [ ] Reports generated with full step visibility and failure screenshot attachment capability.

## Risks and Mitigations

| Risk | Impact | Mitigation |
|------|--------|------------|
| Step definition bloat / duplication | Hard-to-maintain glue code | Delegate all UI interactions to Page Objects; keep step definition methods thin and declarative. |
| External site latency / volatility | Flaky scenario steps | Rely on Playwright auto-waiting assertions (`assertThat`) and configure appropriate action timeouts. |
| Scenario state pollution | Order-dependent test failures | Recreate `BrowserContext` and `Page` per scenario via `@Before`/`@After` hooks. |
| Inconsistent Gherkin wording | Unmapped steps or duplicate glue logic | Define clear domain vocabulary and reusable step patterns for standard navigation and assertion steps. |

## Verification

- Install Chromium: `mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"`
- Execute entire BDD suite: `mvn test`
- Execute specific feature: `mvn test -Dcucumber.features="src/test/resources/features/checkout.feature"`
