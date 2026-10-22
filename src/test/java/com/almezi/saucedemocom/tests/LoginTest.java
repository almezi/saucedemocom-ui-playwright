package com.almezi.saucedemocom.tests;

import com.almezi.saucedemocom.base.BaseTest;
import com.almezi.saucedemocom.config.TestConfig;
import com.almezi.saucedemocom.pages.InventoryPage;
import com.almezi.saucedemocom.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LoginTest extends BaseTest {

    @Test
    @DisplayName("User should be able to log in successfully with valid credentials")
    void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();

        InventoryPage inventoryPage = loginPage.loginAsStandardUser();

        assertThat(page).hasURL(Pattern.compile(".*/inventory\\.html"));
        assertThat(inventoryPage.getTitle()).isVisible();
        assertThat(inventoryPage.getTitle()).hasText("Products");
    }

    @Test
    @DisplayName("User should see an error message when logging in with locked out credentials")
    void testLockedOutUserLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();

        loginPage.login(TestConfig.LOCKED_OUT_USER, TestConfig.STANDARD_PASSWORD);

        assertThat(loginPage.getErrorMessage()).isVisible();
        assertThat(loginPage.getErrorMessage()).containsText("Epic sadface: Sorry, this user has been locked out.");
    }
}
