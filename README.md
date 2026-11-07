# SauceDemo UI Automation with Playwright & Cucumber

An automated UI test suite for [SauceDemo](https://www.saucedemo.com) designed to maximize test coverage, defect detection, and execution efficiency using **Microsoft Playwright for Java**, **Cucumber (BDD)**, **JUnit 5 Platform Suite**, and comprehensive **HTML Reporting**.

---

## 🚀 Key Features & Tech Stack
- **Browser Engine**: Microsoft Playwright (Chromium, with headed/headless support)
- **BDD Framework**: Cucumber Java 7 & Gherkin Features for human-readable specification
- **Test Runner**: JUnit Platform Suite (`@Suite`)
- **Assertions**: Playwright web-first assertions
- **Reporting**:
  - Built-in Cucumber HTML Reporter (`target/cucumber-reports/cucumber.html`)
  - Cucumber JSON Report (`target/cucumber-reports/cucumber.json`)

---

## 📁 Project Structure

```
├── pom.xml
├── README.md
├── src
│   └── test
│       ├── java
│       │   └── com
│       │       └── almezi
│       │           └── saucedemocom
│       │               ├── pages/          # Page Object Model classes
│       │               ├── runners/
│       │               │   └── CucumberTestRunner.java  # JUnit 5 suite runner
│       │               └── steps/          # Step definitions
│       └── resources
│           └── features/
│               ├── login.feature
│               └── checkout.feature
```

---

## 🧪 Testing & Test Execution

### 1. Execute the Full Test Suite
```bash
mvn clean test
```

### 2. Run Scenarios by Tag
```bash
# Smoke test suite (critical path: successful login)
mvn test -Dcucumber.filter.tags="@smoke"

# Sanity scenarios
mvn test -Dcucumber.filter.tags="@sanity"

# Regression suite (default runner tag)
mvn test -Dcucumber.filter.tags="@regression"
```

### 3. Run a Specific Feature File
```bash
mvn test -Dcucumber.features="src/test/resources/features/login.feature"
```

### 4. Browser Execution Configuration

By default, tests run in headed mode (`headless=false`) with a `500ms` delay (`slowMo=500`) between actions so you can visually see the browser interactions:

```bash
# Headless mode (e.g. for CI/CD)
mvn test -Dheadless=true

# Adjust slow motion delay (ms)
mvn test -DslowMo=1000

# Fast headed execution without slow motion delay
mvn test -DslowMo=0
```

---

## 🌐 Playwright Browsers Setup

Install the browsers once before running the tests:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

---

## 📊 Viewing Reports

- **Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **Cucumber JSON Report**: `target/cucumber-reports/cucumber.json` (for CI/CD integration)
- **Maven Surefire Reports**: `target/surefire-reports/` (JUnit XML summaries for pipelines)

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
