# Implementation Plan: SauceDemo Login and Checkout E2E

## Overview

Add automated browser coverage for a successful SauceDemo login and a complete checkout of one `Sauce Labs Onesie`. The test will use the repository's existing Java 21, Maven, Playwright Java, and JUnit 5 stack, and verify observable outcomes at each important transition rather than only checking that the page loads.

## Existing Project Context

- `pom.xml` already includes Playwright Java 1.47.0, JUnit Jupiter 5.10.3, and Maven Surefire.
- `SmokeTest` currently launches Chromium and checks only that the homepage has a title.
- The live login page exposes accessible `Username`, `Password`, and `Login` controls and lists the public `standard_user` account and shared demo password.
- The project README documents `mvn test` and the Playwright Chromium installation command.

## Architecture Decisions

- Keep the tests in JUnit 5 and use the existing Playwright dependency; do not add a new test framework.
- Use the public `standard_user` demo account. Keep credentials centralized in test setup or environment/configuration rather than repeating literals across test cases.
- Prefer semantic Playwright locators (role, label, and product name) or SauceDemo's stable `data-test` attributes. Avoid brittle positional selectors and fixed sleeps; rely on Playwright's auto-waiting and explicit assertions.
- Ensure each test gets an isolated browser context and closes its page, context, browser, and Playwright resources even when an assertion fails.
- Keep a focused login test separate from the full checkout scenario so login failures are easy to diagnose.

## Test Scenarios and Acceptance Criteria

### Login

1. Open `https://www.saucedemo.com/`.
2. Enter the valid demo account credentials and submit the login form.
3. Verify navigation to the inventory page, the `Products` heading, and absence of a visible login error.

### E2E Checkout: Sauce Labs Onesie

1. Log in as `standard_user`.
2. From inventory, add exactly one `Sauce Labs Onesie`.
3. Verify the cart badge shows one item, open the cart, and verify the cart contains the Onesie with quantity one.
4. Select checkout and enter valid first name, last name, and postal code test data.
5. Continue to the order overview and verify it contains the Onesie with quantity one and displays the order summary.
6. Finish checkout and verify the completed-order heading/message is visible (including `Thank you for your order!`) and the completion page is reached.

## Task List

### Phase 1: Test Foundation

- [ ] Task 1: Establish isolated Playwright/JUnit test lifecycle and shared login setup.
- [ ] Task 2: Add a focused successful-login test.

### Checkpoint: Login

- [ ] `mvn test` passes with the login test and existing browser smoke coverage.
- [ ] The test proves successful authentication by checking the inventory page, not just a non-empty page title.

### Phase 2: Checkout Flow

- [ ] Task 3: Add the Onesie cart and checkout completion E2E test.

### Checkpoint: Complete Flow

- [ ] `mvn test` passes with the entire checkout path.
- [ ] Assertions confirm the selected product and quantity in the cart and overview, and the final order-complete state.
- [ ] No fixed sleeps or brittle index-based locators are used.

## Risks and Mitigations

| Risk | Impact | Mitigation |
|------|--------|------------|
| SauceDemo is an external service and may be unavailable or slow | Intermittent test failures | Use Playwright auto-waiting, avoid fixed timing assumptions, and report environmental/network failures distinctly from assertion failures. |
| Public demo account behavior or page content changes | Test may fail after site updates | Use the documented `standard_user` account and assert stable user-visible outcomes; keep selectors semantic or based on stable `data-test` attributes. |
| Browser resources leak when a test fails | Later tests may hang or fail | Use JUnit lifecycle methods and try-with-resources where appropriate so all Playwright resources close reliably. |
| Exact pricing or tax values change | False failures unrelated to checkout completion | Assert product identity, quantity, and presence/consistency of summary totals; avoid hard-coding tax or total unless the requirement explicitly needs exact-price verification. |

## Verification

- Install Chromium if it is not already available, using the command documented in `README.md`.
- Run `mvn test`.
- Confirm the login test reaches the inventory page and the E2E test reaches the order-complete page with the Onesie as the checked-out item.
