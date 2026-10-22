# SauceDemo UI Automation with Playwright & Cucumber

An automated UI test suite for [SauceDemo](https://www.saucedemo.com) using **Microsoft Playwright for Java**, **Cucumber (BDD)**, **JUnit 6 Platform Suite**, and HTML reporting.

---

## Tech Stack

- **Browser engine:** Microsoft Playwright (Chromium)
- **BDD framework:** Cucumber Java 8 and Gherkin feature files
- **Test runner:** JUnit Platform Suite (`@Suite`)
- **Assertions:** Playwright web-first assertions
- **Reporting:** Cucumber HTML and JSON reports

---

## Prerequisites

- Java 21 or newer
- Maven 3.9 or newer
- Network access to `https://www.saucedemo.com`

The build enforces the Java and Maven versions.

---

## Setup

Install the Chromium build that matches the Playwright version in `pom.xml`:

```bash
mvn -B exec:java -Dexec.args="install chromium"
```

On Linux CI, install the OS dependencies too:

```bash
mvn -B exec:java -Dexec.args="install --with-deps chromium"
```

Playwright browser downloads are skipped during test execution. Run the install command after every Playwright upgrade.

---

## Project Structure

```
├── pom.xml
├── README.md
├── src
│   └── test
│       ├── java
│       │   └── com
│       │       └── almezi
│       │           └── saucedemocom
│       │               ├── config/         # Environment and runtime configuration
│       │               ├── context/        # Scenario state container
│       │               ├── hooks/          # Browser lifecycle and failure capture
│       │               ├── pages/          # Page Object Model classes
│       │               ├── runners/        # JUnit Platform suite runner
│       │               └── steps/          # Step definitions
│       └── resources
│           ├── junit-platform.properties
│           └── features/
│               ├── login.feature
│               ├── inventory.feature
│               ├── cart.feature
│               ├── checkout.feature
│               └── menu.feature
```

---

## Running Tests

### Full suite

```bash
mvn clean test
```

No tag filter is applied by default. All discovered scenarios run.

### Run by tag

```bash
# Smoke suite
mvn test -Dcucumber.filter.tags="@smoke"

# Sanity suite
mvn test -Dcucumber.filter.tags="@sanity"

# Regression suite
mvn test -Dcucumber.filter.tags="@regression"
```

Tags are additive: a scenario can carry `@smoke`, `@sanity`, and `@regression` at the same time.

### Browser configuration

Headless mode is enabled by default. To watch a local run:

```bash
mvn test -Dheadless=false -DslowMo=250
```

`slowMo` is in milliseconds and defaults to `0`.

### Override the target environment

```bash
mvn test -DbaseUrl="https://www.saucedemo.com"
```

Credentials can be overridden with `-DstandardUser`, `-DstandardPassword`, and `-DlockedOutUser`.

---

## Reports

- Cucumber HTML: `target/cucumber-reports/cucumber.html`
- Cucumber JSON: `target/cucumber-reports/cucumber.json`
- Surefire reports: `target/surefire-reports/`

Open the HTML report:

**macOS:**

```bash
open target/cucumber-reports/cucumber.html
```

**Linux:**

```bash
xdg-open target/cucumber-reports/cucumber.html
```

**Windows:**

```powershell
start target/cucumber-reports/cucumber.html
```

---

## Continuous Integration

The GitHub Actions workflow runs the suite on every push and pull request. It installs Chromium, runs the regression suite headlessly, and uploads the Cucumber and Surefire reports plus Playwright traces.
