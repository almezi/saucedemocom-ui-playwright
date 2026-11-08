package com.almezi.saucedemocom.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestData {

    private static final Properties PROPERTIES = load();

    private TestData() {
        // Utility class
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Unknown test data key: " + key);
        }
        return value;
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream input = TestData.class.getClassLoader().getResourceAsStream("test-data.properties")) {
            if (input == null) {
                throw new IllegalStateException("test-data.properties not found on the test classpath");
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load test-data.properties", exception);
        }
        return properties;
    }
}
