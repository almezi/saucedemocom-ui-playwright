# Task List: SauceDemo Login and Checkout E2E (Cucumber BDD)

## Phase 1: Test Foundation & BDD Setup

### Task 1: Establish isolated Playwright test lifecycle and Page Objects
**Description:** Set up centralized configuration (`TestConfig`), base Playwright lifecycle fixtures, and initial Page Objects (`LoginPage`, `InventoryPage`) using accessible and `data-test` locators.

**Acceptance criteria:**
- [x] Configuration centralized in `TestConfig` for credentials and base URL.
- [x] Page Objects encapsulate locators and page interactions with Playwright auto-waiting assertions.
- [x] Standalone smoke test passes verifying homepage connectivity.

**Verification:**
- [x] Run `mvn test`.

**Dependencies:** None  
**Files touched:** `src/test/java/com/almezi/saucedemocom/config/TestConfig.java`, `src/test/java/com/almezi/saucedemocom/pages/LoginPage.java`, `src/test/java/com/almezi/saucedemocom/pages/InventoryPage.java`  
**Estimated scope:** Small

---

### Task 2: Configure Cucumber BDD Dependencies and Test Runner
**Description:** Add Cucumber dependencies (`cucumber-java`, `cucumber-junit-platform-engine`) to `pom.xml`, configure `cucumber.properties`, and implement `CucumberTestRunner`.

**Acceptance criteria:**
- [x] Maven builds successfully with Cucumber JUnit Platform Engine.
- [x] `CucumberTestRunner` configured with standard plugins (pretty, html report).
- [x] Cucumber discovers features under `src/test/resources/features/` and glue code under `com.almezi.saucedemocom`.

**Verification:**
- [x] Run `mvn test` and verify Cucumber runner triggers feature scanning.

**Dependencies:** Task 1  
**Files touched:** `pom.xml`, `src/test/resources/cucumber.properties`, `src/test/java/com/almezi/saucedemocom/runners/CucumberTestRunner.java`  
**Estimated scope:** Small

---

### Task 3: Implement Cucumber Lifecycle Hooks and Login Step Definitions
**Description:** Create `Hooks` for scenario-level Playwright setup/teardown and screenshot on failure, write `login.feature`, and implement `LoginSteps` using `LoginPage` and `InventoryPage`.

**Acceptance criteria:**
- [x] Cucumber `@Before` / `@After` hooks provide fresh `BrowserContext` and `Page` per scenario.
- [x] `login.feature` covers successful login and locked-out user scenarios.
- [x] `LoginSteps` executes steps delegating to Page Objects with web-first assertions.

**Verification:**
- [x] Run `mvn test` and verify both login scenarios pass.

**Dependencies:** Task 2  
**Files touched:** `src/test/resources/features/login.feature`, `src/test/java/com/almezi/saucedemocom/hooks/Hooks.java`, `src/test/java/com/almezi/saucedemocom/steps/LoginSteps.java`, `src/test/java/com/almezi/saucedemocom/context/TestContext.java`  
**Estimated scope:** Medium

---

## Checkpoint: Login BDD

- [x] `mvn test` passes with all scenarios in `login.feature`.
- [x] Browser contexts isolate cleanly per scenario with zero resource leaks.

---

## Phase 2: Checkout Flow & Complete POM Implementation

### Task 4: Implement Remaining Page Objects for Checkout Flow
**Description:** Implement `CartPage`, `CheckoutStepOnePage`, `CheckoutStepTwoPage`, and `CheckoutCompletePage` with semantic locators and action methods.

**Acceptance criteria:**
- [ ] `CartPage` locates items and provides checkout navigation.
- [ ] `CheckoutStepOnePage` handles customer information input.
- [ ] `CheckoutStepTwoPage` verifies overview items and summary calculations.
- [ ] `CheckoutCompletePage` verifies order confirmation message and dispatch headers.

**Verification:**
- [ ] Unit/functional verification of page object locators and methods.

**Dependencies:** Task 3  
**Files likely touched:** `src/test/java/com/almezi/saucedemocom/pages/CartPage.java`, `src/test/java/com/almezi/saucedemocom/pages/CheckoutStepOnePage.java`, `src/test/java/com/almezi/saucedemocom/pages/CheckoutStepTwoPage.java`, `src/test/java/com/almezi/saucedemocom/pages/CheckoutCompletePage.java`  
**Estimated scope:** Medium

---

### Task 5: Implement End-to-End Checkout Feature and Step Definitions
**Description:** Author `checkout.feature` specifying the full Onesie ordering journey in Gherkin and implement step definitions (`InventorySteps`, `CartSteps`, `CheckoutSteps`).

**Acceptance criteria:**
- [ ] `checkout.feature` covers the end-to-end purchasing workflow for `Sauce Labs Onesie`.
- [ ] Step definitions delegate directly to Page Objects; no raw selector logic in steps.
- [ ] Web-first assertions verify item quantity, overview details, and final confirmation.

**Verification:**
- [ ] Run `mvn test` and verify full test suite passes.

**Dependencies:** Task 4  
**Files likely touched:** `src/test/resources/features/checkout.feature`, `src/test/java/com/almezi/saucedemocom/steps/InventorySteps.java`, `src/test/java/com/almezi/saucedemocom/steps/CartSteps.java`, `src/test/java/com/almezi/saucedemocom/steps/CheckoutSteps.java`  
**Estimated scope:** Medium

---

## Checkpoint: Complete BDD Flow

- [ ] `mvn test` executes all feature files (`login.feature`, `checkout.feature`) with 100% scenario pass rate.
- [ ] Cucumber HTML report generated in `target/cucumber-reports/`.
