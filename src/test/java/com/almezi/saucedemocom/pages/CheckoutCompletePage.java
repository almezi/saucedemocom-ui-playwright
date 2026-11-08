package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutCompletePage {

    private final Page page;
    private final Locator completeHeader;
    private final Locator backHomeButton;

    public CheckoutCompletePage(Page page) {
        this.page = page;
        this.completeHeader = page.locator("[data-test='complete-header']");
        this.backHomeButton = page.locator("[data-test='back-to-products']");
    }

    public InventoryPage clickBackHome() {
        backHomeButton.click();
        return new InventoryPage(page);
    }

    public Locator getCompleteHeader() {
        return completeHeader;
    }
}
