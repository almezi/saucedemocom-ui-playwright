package com.almezi.saucedemocom;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SmokeTest {

    @Test
    void browserLaunches() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(BrowserType.LaunchOptions.builder().setHeadless(true).build());
            Page page = browser.newPage();
            page.navigate("https://www.saucedemo.com");
            assertNotNull(page.title());
            System.out.println("Title: " + page.title());
            browser.close();
        }
    }
}
