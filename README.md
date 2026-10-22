# saucedemocom-ui-playwright

Behavior-Driven Development (BDD) UI test automation suite for [saucedemo.com](https://www.saucedemo.com) built with Java 21, Playwright, Cucumber BDD, JUnit 5, and Page Object Model (POM).

- groupId: `com.almezi`
- artifactId: `saucedemocom-ui-playwright`

## Architecture Highlights

- **BDD Gherkin Features**: Human-readable test scenarios located under `src/test/resources/features/`.
- **Page Object Model (POM)**: Encapsulated UI components and auto-waiting semantic locators under `src/test/java/com/almezi/saucedemocom/pages/`.
- **Scenario Context Isolation**: Clean `BrowserContext` and `Page` instantiation per scenario with Playwright web-first assertions.

## Commands

```bash
mvn compile                 # Build project
mvn test                    # Run all tests (Cucumber BDD scenarios & JUnit tests)
mvn exec:java -Dexec.mainClass=com.almezi.saucedemocom.App
```

### Running Specific Scenarios or Features

```bash
# Run specific feature file
mvn test -Dcucumber.features="src/test/resources/features/login.feature"

# Run scenarios with specific tags
mvn test -Dcucumber.filter.tags="@smoke"
```

## Playwright Browsers Setup

Install the browsers once before running browser tests:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```
