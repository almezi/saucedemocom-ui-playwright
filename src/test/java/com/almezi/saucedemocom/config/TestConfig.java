package com.almezi.saucedemocom.config;

public final class TestConfig {

    private TestConfig() {
        // Utility class
    }

    public static final String BASE_URL = System.getProperty("baseUrl", "https://www.saucedemo.com");
    public static final String STANDARD_USER = System.getProperty("standardUser", "standard_user");
    public static final String STANDARD_PASSWORD = System.getProperty("standardPassword", "secret_sauce");
    public static final String LOCKED_OUT_USER = System.getProperty("lockedOutUser", "locked_out_user");
}
