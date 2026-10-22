package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutStepTwoPage {

    private final Page page;
    private final Locator title;
    private final Locator summaryItems;
    private final Locator finishButton;
    private final Locator cancelButton;
    private final Locator paymentInfoLabel;
    private final Locator shippingInfoLabel;
    private final Locator subtotalLabel;
    private final Locator taxLabel;
    private final Locator totalLabel;

    public CheckoutStepTwoPage(Page page) {
        this.page = page;
        this.title = page.locator("[data-test='title']");
        this.summaryItems = page.locator("[data-test='inventory-item']");
        this.finishButton = page.locator("[data-test='finish']");
        this.cancelButton = page.locator("[data-test='cancel']");
        this.paymentInfoLabel = page.locator("[data-test='payment-info-value']");
        this.shippingInfoLabel = page.locator("[data-test='shipping-info-value']");
        this.subtotalLabel = page.locator("[data-test='subtotal-label']");
        this.taxLabel = page.locator("[data-test='tax-label']");
        this.totalLabel = page.locator("[data-test='total-label']");
    }

    public Locator getItemByName(String itemName) {
        return summaryItems.filter(new Locator.FilterOptions().setHas(
                page.getByText(itemName, new Page.GetByTextOptions().setExact(true))));
    }

    public Locator getItemQuantity(String itemName) {
        return getItemByName(itemName).locator("[data-test='item-quantity']");
    }

    public CheckoutCompletePage clickFinish() {
        finishButton.click();
        return new CheckoutCompletePage(page);
    }

    public InventoryPage clickCancel() {
        cancelButton.click();
        return new InventoryPage(page);
    }

    public Locator getTitle() {
        return title;
    }

    public Locator getSummaryItems() {
        return summaryItems;
    }

    public Locator getFinishButton() {
        return finishButton;
    }

    public Locator getCancelButton() {
        return cancelButton;
    }

    public Locator getPaymentInfoLabel() {
        return paymentInfoLabel;
    }

    public Locator getShippingInfoLabel() {
        return shippingInfoLabel;
    }

    public Locator getSubtotalLabel() {
        return subtotalLabel;
    }

    public Locator getTaxLabel() {
        return taxLabel;
    }

    public Locator getTotalLabel() {
        return totalLabel;
    }

    public Page getPage() {
        return page;
    }
}
