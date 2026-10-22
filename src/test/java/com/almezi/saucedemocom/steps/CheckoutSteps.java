package com.almezi.saucedemocom.steps;

import com.almezi.saucedemocom.context.TestContext;
import com.almezi.saucedemocom.pages.CheckoutCompletePage;
import com.almezi.saucedemocom.pages.CheckoutStepOnePage;
import com.almezi.saucedemocom.pages.CheckoutStepTwoPage;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {

    private static final Pattern MONEY = Pattern.compile("\\$([0-9]+\\.[0-9]{2})");

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

    @When("the user cancels checkout")
    public void userCancelsCheckout() {
        testContext.getCheckoutStepOnePage().clickCancel();
    }

    @When("the user cancels the checkout overview")
    public void userCancelsCheckoutOverview() {
        testContext.getCheckoutStepTwoPage().clickCancel();
    }

    @Then("the order summary should show {string} with quantity {int}")
    public void orderSummaryShouldShowItemWithQuantity(String itemName, int quantity) {
        CheckoutStepTwoPage stepTwoPage = testContext.getCheckoutStepTwoPage();
        assertThat(stepTwoPage.getItemByName(itemName)).isVisible();
        assertThat(stepTwoPage.getItemQuantity(itemName)).hasText(String.valueOf(quantity));
    }

    @Then("a checkout error message stating {string} should be displayed")
    public void checkoutErrorMessageShouldBeDisplayed(String expectedMessage) {
        Locator errorMessage = testContext.getCheckoutStepOnePage().getErrorMessage();
        assertThat(errorMessage).isVisible();
        assertThat(errorMessage).containsText(expectedMessage);
    }

    @Then("the order subtotal plus tax should equal the total")
    public void orderSubtotalPlusTaxShouldEqualTotal() {
        CheckoutStepTwoPage stepTwoPage = testContext.getCheckoutStepTwoPage();
        double subtotal = amount(stepTwoPage.getSubtotalLabel());
        double tax = amount(stepTwoPage.getTaxLabel());
        double total = amount(stepTwoPage.getTotalLabel());
        assertEquals(subtotal + tax, total, 0.01, "Subtotal plus tax must equal the order total");
    }

    @When("the user finishes the checkout")
    public void userFinishesCheckout() {
        testContext.getCheckoutStepTwoPage().clickFinish();
    }

    @When("the user returns to the products page")
    public void userReturnsToProductsPage() {
        testContext.getCheckoutCompletePage().clickBackHome();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void orderConfirmationMessageShouldBeDisplayed(String expectedMessage) {
        CheckoutCompletePage completePage = testContext.getCheckoutCompletePage();
        assertThat(completePage.getCompleteHeader()).isVisible();
        assertThat(completePage.getCompleteHeader()).hasText(expectedMessage);
    }

    private double amount(Locator label) {
        String text = label.textContent();
        Matcher matcher = MONEY.matcher(text);
        if (!matcher.find()) {
            throw new AssertionError("No monetary amount found in: " + text);
        }
        return Double.parseDouble(matcher.group(1));
    }
}
