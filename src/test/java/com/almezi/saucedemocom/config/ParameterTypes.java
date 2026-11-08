package com.almezi.saucedemocom.config;

import io.cucumber.java.ParameterType;

/**
 * Resolves short keys used in feature files (e.g. {@code onesie}) to the
 * values maintained in test-data.properties.
 */
public class ParameterTypes {

    @ParameterType(".*")
    public String product(String key) {
        return TestData.get("product." + key);
    }

    @ParameterType(".*")
    public String sort(String key) {
        return TestData.get("sort." + key);
    }
}
