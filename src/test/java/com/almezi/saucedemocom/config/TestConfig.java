package com.almezi.saucedemocom.config;

import com.microsoft.playwright.Playwright;

import java.util.Map;

public final class TestConfig {

    private TestConfig() {
        // Utility class
    }

    public static Playwright createPlaywright() {
        return Playwright.create(new Playwright.CreateOptions()
                .setEnv(Map.of("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1")));
    }

    public static final String BASE_URL = System.getProperty("baseUrl", "https://www.saucedemo.com");
    public static final String INVENTORY_URL = BASE_URL + "/inventory.html";
    public static final String STANDARD_USER = System.getProperty("standardUser", "standard_user");
    public static final String STANDARD_PASSWORD = System.getProperty("standardPassword", "secret_sauce");
    public static final String LOCKED_OUT_USER = System.getProperty("lockedOutUser", "locked_out_user");

    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "true"));
    public static final double SLOW_MO = Double.parseDouble(System.getProperty("slowMo", "0"));
}
