package com.almezi.saucedemocom.steps;

import com.almezi.saucedemocom.context.TestContext;
import com.almezi.saucedemocom.pages.CheckoutCompletePage;
import com.almezi.saucedemocom.pages.CheckoutStepOnePage;
import com.almezi.saucedemocom.pages.CheckoutStepTwoPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutSteps {

    private final TestContext testContext;

    public CheckoutSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("fills in customer information with first name {string}, last name {string}, and postal code {string}")
    public void fillsInCustomerInformation(String firstName, String lastName, String postalCode) {
        CheckoutStepOnePage stepOnePage = testContext.getCheckoutStepOnePage();
        stepOnePage.fillCustomerInformation(firstName, lastName, postalCode);
    }

    @When("continues to the checkout overview")
    public void continuesToCheckoutOverview() {
        testContext.getCheckoutStepOnePage().clickContinue();
    }

    @Then("the order summary should show {string} with quantity {int}")
    public void orderSummaryShouldShowItemWithQuantity(String itemName, int quantity) {
        CheckoutStepTwoPage stepTwoPage = testContext.getCheckoutStepTwoPage();
        assertThat(stepTwoPage.getItemByName(itemName)).isVisible();
        assertThat(stepTwoPage.getItemQuantity(itemName)).hasText(String.valueOf(quantity));
    }

    @When("the user finishes the checkout")
    public void userFinishesCheckout() {
        testContext.getCheckoutStepTwoPage().clickFinish();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void orderConfirmationMessageShouldBeDisplayed(String expectedMessage) {
        CheckoutCompletePage completePage = testContext.getCheckoutCompletePage();
        assertThat(completePage.getCompleteHeader()).isVisible();
        assertThat(completePage.getCompleteHeader()).hasText(expectedMessage);
    }
}
