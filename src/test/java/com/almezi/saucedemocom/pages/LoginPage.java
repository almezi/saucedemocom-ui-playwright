package com.almezi.saucedemocom.pages;

import com.almezi.saucedemocom.config.TestConfig;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private final Page page;
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.getByPlaceholder("Username");
        this.passwordInput = page.getByPlaceholder("Password");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        this.errorMessage = page.locator("[data-test='error']");
    }

    public LoginPage navigate() {
        page.navigate(TestConfig.BASE_URL);
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameInput.fill(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    public void clickLogin() {
        loginButton.click();
    }

    public InventoryPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage(page);
    }

    public InventoryPage loginAsStandardUser() {
        return login(TestConfig.STANDARD_USER, TestConfig.STANDARD_PASSWORD);
    }

    public Locator getErrorMessage() {
        return errorMessage;
    }

    public Locator getUsernameInput() {
        return usernameInput;
    }

    public Locator getPasswordInput() {
        return passwordInput;
    }

    public Locator getLoginButton() {
        return loginButton;
    }
}
