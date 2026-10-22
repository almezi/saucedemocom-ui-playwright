package com.almezi.saucedemocom.hooks;

import com.almezi.saucedemocom.config.TestConfig;
import com.almezi.saucedemocom.context.TestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class Hooks {

    private static final double DEFAULT_TIMEOUT_MS = 30_000;
    private static final Path TRACE_DIRECTORY = Paths.get("target", "playwright-traces");

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
        context.setDefaultTimeout(DEFAULT_TIMEOUT_MS);
        context.setDefaultNavigationTimeout(DEFAULT_TIMEOUT_MS);
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        Page page = context.newPage();
        page.setDefaultTimeout(DEFAULT_TIMEOUT_MS);
        page.setDefaultNavigationTimeout(DEFAULT_TIMEOUT_MS);
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
                attachTrace(scenario, context);
            }
        } finally {
            closeQuietly(page);
            closeQuietly(context);
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

    private void attachScreenshot(Scenario scenario, Page page) {
        if (page == null || page.isClosed()) {
            return;
        }
        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", scenario.getName() + " screenshot");
        } catch (RuntimeException exception) {
            scenario.log("Could not attach screenshot: " + exception.getMessage());
        }
    }

    private void attachTrace(Scenario scenario, BrowserContext context) {
        if (context == null) {
            return;
        }
        try {
            Files.createDirectories(TRACE_DIRECTORY);
            Path tracePath = TRACE_DIRECTORY.resolve(
                    safeName(scenario.getName()) + "-" + System.currentTimeMillis() + ".zip");
            context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
            scenario.attach(Files.readAllBytes(tracePath), "application/zip", scenario.getName() + " trace");
            scenario.log("Playwright trace: " + tracePath);
        } catch (IOException | RuntimeException exception) {
            scenario.log("Could not attach Playwright trace: " + exception.getMessage());
        }
    }

    private String safeName(String name) {
        String safe = name.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
        if (safe.isEmpty()) {
            return "scenario";
        }
        return safe.substring(0, Math.min(safe.length(), 60));
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
}
