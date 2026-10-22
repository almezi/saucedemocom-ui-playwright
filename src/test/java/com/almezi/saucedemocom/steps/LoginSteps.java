package com.almezi.saucedemocom.steps;

import com.almezi.saucedemocom.config.TestConfig;
import com.almezi.saucedemocom.context.TestContext;
import com.almezi.saucedemocom.pages.InventoryPage;
import com.almezi.saucedemocom.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginSteps {

    private final TestContext testContext;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("the user is on the SauceDemo login page")
    public void userIsOnSauceDemoLoginPage() {
        testContext.getLoginPage().navigate();
    }

    @Given("the user is logged into SauceDemo as a standard user")
    public void userIsLoggedIntoSauceDemoAsStandardUser() {
        testContext.getLoginPage().navigate();
        testContext.getLoginPage().loginAsStandardUser();
    }

    @When("the user logs in with valid standard credentials")
    public void userLogsInWithValidStandardCredentials() {
        testContext.getLoginPage().loginAsStandardUser();
    }

    @When("the user logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        testContext.getLoginPage().login(username, password);
    }

    @Then("the user should be directed to the inventory page")
    public void userShouldBeDirectedToInventoryPage() {
        assertThat(testContext.getPage()).hasURL(TestConfig.INVENTORY_URL);
    }

    @And("the products catalog header should be visible")
    public void productsCatalogHeaderShouldBeVisible() {
        InventoryPage inventoryPage = testContext.getInventoryPage();
        assertThat(inventoryPage.getTitle()).isVisible();
        assertThat(inventoryPage.getTitle()).hasText("Products");
    }

    @Then("an error message stating {string} should be displayed")
    public void anErrorMessageStatingShouldBeDisplayed(String expectedErrorMessage) {
        LoginPage loginPage = testContext.getLoginPage();
        assertThat(loginPage.getErrorMessage()).isVisible();
        assertThat(loginPage.getErrorMessage()).containsText(expectedErrorMessage);
    }

    @Then("the user should be on the SauceDemo login page")
    public void userShouldBeOnSauceDemoLoginPage() {
        assertThat(testContext.getLoginPage().getLoginButton()).isVisible();
    }
}
