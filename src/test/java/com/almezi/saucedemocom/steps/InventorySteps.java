package com.almezi.saucedemocom.steps;

import com.almezi.saucedemocom.context.TestContext;
import com.almezi.saucedemocom.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InventorySteps {

    private final TestContext testContext;

    public InventorySteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("the user adds {string} to the shopping cart")
    public void userAddsItemToShoppingCart(String productName) {
        testContext.getInventoryPage().addItemToCart(productName);
    }

    @When("navigates to the shopping cart")
    public void userNavigatesToShoppingCart() {
        testContext.getInventoryPage().clickCartLink();
    }

    @When("the user sorts products by {string}")
    public void userSortsProductsBy(String sortLabel) {
        testContext.getInventoryPage().sortBy(sortLabel);
    }

    @When("the user logs out")
    public void userLogsOut() {
        testContext.getInventoryPage().logout();
    }

    @When("the user resets the app state")
    public void userResetsAppState() {
        testContext.getInventoryPage().resetAppState();
    }

    @Then("the first product should be {string}")
    public void firstProductShouldBe(String productName) {
        assertThat(testContext.getInventoryPage().getFirstItemName()).hasText(productName);
    }

    @Then("the cart badge should show {int} items")
    public void cartBadgeShouldShowItems(int count) {
        InventoryPage inventoryPage = testContext.getInventoryPage();
        assertThat(inventoryPage.getCartBadge()).isVisible();
        assertThat(inventoryPage.getCartBadge()).hasText(String.valueOf(count));
    }

    @Then("the cart badge should not be visible")
    public void cartBadgeShouldNotBeVisible() {
        assertThat(testContext.getInventoryPage().getCartBadge()).isHidden();
    }
}
