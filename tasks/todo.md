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
- [x] `CartPage` locates items and provides checkout navigation.
- [x] `CheckoutStepOnePage` handles customer information input.
- [x] `CheckoutStepTwoPage` verifies overview items and summary calculations.
- [x] `CheckoutCompletePage` verifies order confirmation message and dispatch headers.

**Verification:**
- [x] Unit/functional verification of page object locators and methods.

**Dependencies:** Task 3  
**Files touched:** `src/test/java/com/almezi/saucedemocom/pages/CartPage.java`, `src/test/java/com/almezi/saucedemocom/pages/CheckoutStepOnePage.java`, `src/test/java/com/almezi/saucedemocom/pages/CheckoutStepTwoPage.java`, `src/test/java/com/almezi/saucedemocom/pages/CheckoutCompletePage.java`, `src/test/java/com/almezi/saucedemocom/pages/InventoryPage.java`  
**Estimated scope:** Medium

---

### Task 5: Implement End-to-End Checkout Feature and Step Definitions
**Description:** Author `checkout.feature` specifying the full Onesie ordering journey in Gherkin and implement step definitions (`InventorySteps`, `CartSteps`, `CheckoutSteps`).

**Acceptance criteria:**
- [x] `checkout.feature` covers the end-to-end purchasing workflow for `Sauce Labs Onesie`.
- [x] Step definitions delegate directly to Page Objects; no raw selector logic in steps.
- [x] Web-first assertions verify item quantity, overview details, and final confirmation.

**Verification:**
- [x] Run `mvn test` and verify full test suite passes.

**Dependencies:** Task 4  
**Files touched:** `src/test/resources/features/checkout.feature`, `src/test/java/com/almezi/saucedemocom/steps/InventorySteps.java`, `src/test/java/com/almezi/saucedemocom/steps/CartSteps.java`, `src/test/java/com/almezi/saucedemocom/steps/CheckoutSteps.java`  
**Estimated scope:** Medium

---

## Checkpoint: Complete BDD Flow

- [x] `mvn test` executes all feature files (`login.feature`, `checkout.feature`) with 100% scenario pass rate.
- [x] Cucumber HTML report generated in `target/cucumber-reports/`.

---

# Phase 3: Framework Review and Hardening

Baseline on 2026-10-22: `mvn -B test` passed 6/6 tests in 34.6 seconds.

---

### Task 6: Safe Dependency Upgrade (Cucumber 7.x / JUnit 5.x)

**Description:** Upgrade dependencies within their current major versions before attempting the Cucumber 8 / JUnit 6 migration.

**Acceptance criteria:**
- [ ] Playwright upgraded from `1.47.0` to `1.64.0`.
- [ ] Cucumber upgraded from `7.18.0` to `7.29.0`.
- [ ] JUnit Jupiter upgraded from `5.10.3` to `5.14.4`.
- [ ] JUnit Platform upgraded from `1.10.3` to `1.14.4`.
- [ ] `maven-surefire-plugin` upgraded from `3.2.5` to `3.6.0`.
- [ ] `maven-compiler-plugin` pinned to `3.16.0`.
- [ ] `exec-maven-plugin` `3.6.4` added for Chromium installation.
- [ ] `maven-enforcer-plugin` `3.6.3` added with rules: Java `[21,26)`, Maven `[3.9,)`, ban `junit:junit`.
- [ ] Unused `io.cucumber:cucumber-junit` dependency removed.
- [ ] Chromium reinstalled for Playwright `1.64.0`.

**Verification:**
```bash
mvn -B clean test
mvn -B dependency:tree
mvn -B exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

**Dependencies:** None  
**Files touched:** `pom.xml`  
**Estimated scope:** Small

---

### Task 7: Runtime and Configuration Cleanup

**Description:** Remove CI blockers, duplicate test suites, and conflicting Cucumber configuration.

**Acceptance criteria:**
- [ ] `TestConfig.HEADLESS` defaults to `true`.
- [ ] `TestConfig.SLOW_MO` defaults to `0`.
- [ ] Hardcoded `@regression` filter removed from `CucumberTestRunner`.
- [ ] Empty `cucumber.filter.tags` property removed from `pom.xml`.
- [ ] One Cucumber configuration source remains; duplicate glue/plugin/feature settings removed from `junit-platform.properties` and runner annotations.
- [ ] `LoginTest.java` deleted; Cucumber covers login behavior.
- [ ] `SmokeTest.java` and `BaseTest.java` deleted; Cucumber becomes the single test suite.
- [ ] `@smoke` tag added to the successful-login scenario.
- [ ] README documents Java 21, Maven 3.9, Chromium install, correct tag commands, and report paths.
- [ ] Failure screenshot is skipped when the page is closed and attach failures are caught in `Hooks.java`.

**Verification:**
```bash
mvn -B clean test
mvn -B test -Dcucumber.filter.tags="@smoke"      # 1 scenario
mvn -B test -Dcucumber.filter.tags="@sanity"     # 1 scenario
mvn -B test -Dcucumber.filter.tags="@regression" # 3 scenarios
mvn -B test                                      # 3 scenarios
```

**Dependencies:** Task 6  
**Files touched:** `src/test/java/com/almezi/saucedemocom/config/TestConfig.java`, `src/test/java/com/almezi/saucedemocom/runners/CucumberTestRunner.java`, `src/test/java/com/almezi/saucedemocom/hooks/Hooks.java`, `src/test/resources/cucumber.properties`, `src/test/resources/junit-platform.properties`, `src/test/resources/features/login.feature`, `src/test/java/com/almezi/saucedemocom/tests/LoginTest.java`, `src/test/java/com/almezi/saucedemocom/SmokeTest.java`, `src/test/java/com/almezi/saucedemocom/base/BaseTest.java`, `pom.xml`, `README.md`  
**Estimated scope:** Medium

---

### Task 8: Continuous Integration

**Description:** Run the suite automatically on every push and pull request.

**Acceptance criteria:**
- [ ] GitHub Actions workflow added at `.github/workflows/ui-tests.yml`.
- [ ] Workflow uses Ubuntu, Temurin Java 21, and Maven dependency caching.
- [ ] Chromium installed with `install --with-deps chromium`.
- [ ] `mvn -B test` executed headless.
- [ ] `target/cucumber-reports/` and `target/surefire-reports/` uploaded as artifacts.
- [ ] Playwright traces uploaded on failure.
- [ ] Optional Java 21 / 25 matrix configured.

**Verification:**
- [ ] Workflow passes on push and pull request.
- [ ] Reports are downloadable from the workflow run.

**Dependencies:** Task 7  
**Files touched:** `.github/workflows/ui-tests.yml`  
**Estimated scope:** Small

---

### Task 9: Failure Diagnostics

**Description:** Capture enough evidence to diagnose failures without rerunning locally.

**Acceptance criteria:**
- [ ] Playwright tracing started per scenario.
- [ ] Trace stopped and attached to the Cucumber report on failure.
- [ ] Page default timeout and navigation timeout set to 30 seconds.
- [ ] Suite-level timeout configured in Surefire.
- [ ] At most one retry added after traces are verified; retries do not replace root-cause fixes.

**Verification:**
- [ ] Deliberately fail one scenario and confirm screenshot plus trace are attached.
- [ ] Confirm the original failure message is preserved when attachment fails.

**Dependencies:** Task 8  
**Files touched:** `src/test/java/com/almezi/saucedemocom/hooks/Hooks.java`, `pom.xml`  
**Estimated scope:** Medium

---

### Task 10: Coverage Expansion

**Description:** Add high-value scenarios for behavior not currently covered.

**Acceptance criteria:**
- [ ] Login: empty fields, invalid password, `problem_user`, `performance_glitch_user`.
- [ ] Inventory: sort by name and price, add multiple items.
- [ ] Cart: badge count, remove item, continue shopping.
- [ ] Checkout: missing first name or postal code, cancel from both checkout steps, subtotal plus tax equals total.
- [ ] Order completion: back to products.
- [ ] Menu: logout and reset app state.
- [ ] Optional: accessibility scan with axe.

**Verification:**
- [ ] `mvn -B test` passes with the expanded suite.
- [ ] New scenarios have appropriate `@smoke`, `@sanity`, or `@regression` tags.

**Dependencies:** Task 9  
**Files touched:** `src/test/resources/features/`, `src/test/java/com/almezi/saucedemocom/steps/`, `src/test/java/com/almezi/saucedemocom/pages/`  
**Estimated scope:** Medium

---

### Task 11: Modern Major Upgrade (Separate Branch)

**Description:** Upgrade to Cucumber 8 and JUnit 6 only after Phase 3 is green. Do not combine this with Task 6.

**Acceptance criteria:**
- [ ] Cucumber upgraded to `8.0.4`.
- [ ] JUnit Jupiter and JUnit Platform upgraded to `6.1.3`.
- [ ] Java release remains `21`; both dependencies require Java 17 or newer.
- [ ] Remaining JUnit 5 pins removed.
- [ ] README updated from JUnit 5 to JUnit 6.
- [ ] Full regression suite passes with reports and traces working.
- [ ] If migration is blocked, the branch is abandoned and Task 6 versions remain.

**Verification:**
```bash
mvn -B clean test
mvn -B test -Dcucumber.filter.tags="@regression"
```

**Dependencies:** Tasks 8, 9, 10  
**Files touched:** `pom.xml`, `README.md`  
**Estimated scope:** Medium

---

## Checkpoint: Framework Hardening Complete

- [ ] Latest compatible dependencies pinned and verified.
- [ ] Single Cucumber suite; no duplicate JUnit login or smoke tests.
- [ ] CI runs headless on every push and pull request.
- [ ] Failures produce screenshot and Playwright trace.
- [ ] Expanded regression suite passes.
- [ ] Cucumber 8 / JUnit 6 migration evaluated separately.
