package com.almezi.saucedemocom.steps;

import com.almezi.saucedemocom.context.TestContext;
import io.cucumber.java.en.When;

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
}
