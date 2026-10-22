package com.almezi.saucedemocom;

import com.almezi.saucedemocom.base.BaseTest;
import com.almezi.saucedemocom.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class SmokeTest extends BaseTest {

    @Test
    @DisplayName("Smoke Test: Application home page loads successfully")
    void browserLaunches() {
        page.navigate(TestConfig.BASE_URL);
        assertThat(page).hasTitle("Swag Labs");
    }
}
