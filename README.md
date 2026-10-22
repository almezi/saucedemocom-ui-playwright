# saucedemocom-ui-playwright

Minimal Maven + Java + Playwright project for UI testing of [saucedemo.com](https://www.saucedemo.com).

- groupId: `com.almezi`
- artifactId: `saucedemocom-ui-playwright`

## Commands

```bash
mvn compile                 # build
mvn test                    # run tests
mvn exec:java -Dexec.mainClass=com.almezi.saucedemocom.App
```

## Playwright browsers

Install the browsers once before running browser tests:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```
