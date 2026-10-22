package com.almezi.saucedemocom.hooks;

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
            playwright = Playwright.create();
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
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
        if (page != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
            page.close();
        }
        BrowserContext context = testContext.getContext();
        if (context != null) {
            context.close();
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
