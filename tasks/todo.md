# Task List: SauceDemo Login and Checkout E2E

## Phase 1: Test Foundation

### Task 1: Establish isolated Playwright/JUnit test lifecycle

**Description:** Refactor or extend the existing `SmokeTest` test setup so browser tests use isolated contexts and reliably close Playwright resources. Provide a shared, non-duplicated way to open SauceDemo and authenticate as the public `standard_user` demo account.

**Acceptance criteria:**
- [x] Tests create an isolated browser context and close context, browser, and Playwright resources after each run, including assertion failures.
- [x] The demo username and password are not duplicated across test methods.
- [x] Existing browser-launch coverage remains meaningful or is replaced by stronger user-flow assertions.

**Verification:**
- [x] Run `mvn test`.
- [x] Confirm the test starts Chromium headlessly and cleans up without leaving browser processes owned by the test.

**Dependencies:** None  
**Files likely touched:** `src/test/java/com/almezi/saucedemocom/SmokeTest.java`  
**Estimated scope:** Small

### Task 2: Add a focused successful-login test

**Description:** Exercise the login form with valid demo credentials and verify the application reaches the authenticated inventory screen.

**Acceptance criteria:**
- [x] The test enters credentials using accessible labels or stable selectors and submits the login form.
- [x] The test verifies the inventory URL/page and visible `Products` heading.
- [x] A failed login remains a test failure rather than being hidden by a fallback.

**Verification:**
- [x] Run the focused test with `mvn -Dtest=SmokeTest test` (or the renamed test class selector).
- [x] Run `mvn test` to check the complete suite.

**Dependencies:** Task 1  
**Files likely touched:** `src/test/java/com/almezi/saucedemocom/SmokeTest.java`  
**Estimated scope:** Small

## Checkpoint: Login

- [x] `mvn test` passes.
- [x] Login success is asserted by authenticated inventory content, not only by successful browser launch.

## Phase 2: Checkout Flow

### Task 3: Add the Onesie checkout E2E test

**Description:** Add the `Sauce Labs Onesie` to the cart, proceed through customer information and order overview, finish checkout, and assert the final order-complete state.

**Acceptance criteria:**
- [ ] The cart badge and cart page show exactly one `Sauce Labs Onesie`, quantity one.
- [ ] Checkout information is submitted and the overview shows the expected item and quantity.
- [ ] Completing checkout displays the order confirmation (including `Thank you for your order!`).
- [ ] Locators are semantic or use stable `data-test` attributes; no fixed sleeps or positional selectors are introduced.

**Verification:**
- [ ] Run the checkout test with `mvn -Dtest=SmokeTest test` (or the renamed test class selector).
- [ ] Run `mvn test` and confirm all tests pass.

**Dependencies:** Task 1  
**Files likely touched:** `src/test/java/com/almezi/saucedemocom/SmokeTest.java`  
**Estimated scope:** Medium

## Checkpoint: Complete Flow

- [ ] Login and checkout tests pass with `mvn test`.
- [ ] The final assertion proves checkout reached the completed-order page for the expected product.
