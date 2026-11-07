package com.almezi.saucedemocom.steps;

import com.almezi.saucedemocom.config.TestConfig;
import com.almezi.saucedemocom.context.TestContext;
import com.almezi.saucedemocom.pages.CartPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartSteps {

    private final TestContext testContext;

    public CartSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("the cart should contain {int} item of {string}")
    public void cartShouldContainItem(int quantity, String itemName) {
        CartPage cartPage = testContext.getCartPage();
        assertThat(cartPage.getItemByName(itemName)).isVisible();
        assertThat(cartPage.getItemQuantity(itemName)).hasText(String.valueOf(quantity));
    }

    @Then("the cart should be empty")
    public void cartShouldBeEmpty() {
        assertThat(testContext.getCartPage().getCartItems()).hasCount(0);
    }

    @Then("the user should be on the cart page")
    public void userShouldBeOnCartPage() {
        assertThat(testContext.getPage()).hasURL(TestConfig.CART_URL);
    }

    @When("the user proceeds to checkout")
    public void userProceedsToCheckout() {
        testContext.getCartPage().clickCheckout();
    }

    @When("the user removes {string} from the shopping cart")
    public void userRemovesItemFromShoppingCart(String itemName) {
        testContext.getCartPage().removeItem(itemName);
    }

    @When("the user continues shopping")
    public void userContinuesShopping() {
        testContext.getCartPage().continueShopping();
    }
}
