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
# Smoke test suite
mvn test -Dcucumber.filter.tags="@smoke"

# Sanity scenarios (default runner tag)
mvn test -Dcucumber.filter.tags="@sanity"

# Regression suite
mvn test -Dcucumber.filter.tags="@regression"
```

### 3. Browser Execution Configuration
```bash
# Headless mode
mvn test -Dheadless=true

# Adjust slow motion delay (ms)
mvn test -DslowMo=1000
```

---

## 📊 Viewing HTML Reports

- **Standard Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **Cucumber JSON Report**: `target/cucumber-reports/cucumber.json`

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
