package com.almezi.saucedemocom.hooks;

import com.almezi.saucedemocom.config.TestConfig;
import com.almezi.saucedemocom.context.TestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

    private static Playwright playwright;
    private static Browser browser;

    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @BeforeAll
    public static void beforeAll() {
        if (playwright == null) {
            playwright = TestConfig.createPlaywright();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(TestConfig.HEADLESS)
                            .setSlowMo(TestConfig.SLOW_MO)
            );
        }
    }

    @Before
    public void beforeEach() {
        if (browser == null) {
            beforeAll();
        }
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        testContext.setContext(context);
        testContext.setPage(page);
    }

    @After
    public void afterEach(Scenario scenario) {
        Page page = testContext.getPage();
        BrowserContext context = testContext.getContext();
        try {
            if (scenario.isFailed()) {
                attachScreenshot(scenario, page);
            }
        } finally {
            closeQuietly(page);
            closeQuietly(context);
        }
    }

    private void attachScreenshot(Scenario scenario, Page page) {
        if (page == null || page.isClosed()) {
            return;
        }
        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", scenario.getName());
        } catch (RuntimeException exception) {
            scenario.log("Could not attach screenshot: " + exception.getMessage());
        }
    }

    private void closeQuietly(Page page) {
        if (page == null || page.isClosed()) {
            return;
        }
        try {
            page.close();
        } catch (RuntimeException ignored) {
            // A failed close must not mask the scenario result.
        }
    }

    private void closeQuietly(BrowserContext context) {
        if (context == null) {
            return;
        }
        try {
            context.close();
        } catch (RuntimeException ignored) {
            // A failed close must not mask the scenario result.
        }
    }

    @AfterAll
    public static void afterAll() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
