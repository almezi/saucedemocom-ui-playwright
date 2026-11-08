package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutStepOnePage {

    private final Page page;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator postalCodeInput;
    private final Locator continueButton;
    private final Locator cancelButton;
    private final Locator errorMessage;

    public CheckoutStepOnePage(Page page) {
        this.page = page;
        this.firstNameInput = page.getByPlaceholder("First Name");
        this.lastNameInput = page.getByPlaceholder("Last Name");
        this.postalCodeInput = page.getByPlaceholder("Zip/Postal Code");
        this.continueButton = page.locator("[data-test='continue']");
        this.cancelButton = page.locator("[data-test='cancel']");
        this.errorMessage = page.locator("[data-test='error']");
    }

    public CheckoutStepOnePage enterFirstName(String firstName) {
        firstNameInput.fill(firstName);
        return this;
    }

    public CheckoutStepOnePage enterLastName(String lastName) {
        lastNameInput.fill(lastName);
        return this;
    }

    public CheckoutStepOnePage enterPostalCode(String postalCode) {
        postalCodeInput.fill(postalCode);
        return this;
    }

    public CheckoutStepOnePage fillCustomerInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        return this;
    }

    public CheckoutStepTwoPage clickContinue() {
        continueButton.click();
        return new CheckoutStepTwoPage(page);
    }

    public CartPage clickCancel() {
        cancelButton.click();
        return new CartPage(page);
    }

    public Locator getErrorMessage() {
        return errorMessage;
    }
}
