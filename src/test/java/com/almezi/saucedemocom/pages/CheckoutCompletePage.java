package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutCompletePage {

    private final Page page;
    private final Locator title;
    private final Locator completeHeader;
    private final Locator completeText;
    private final Locator backHomeButton;

    public CheckoutCompletePage(Page page) {
        this.page = page;
        this.title = page.locator("[data-test='title']");
        this.completeHeader = page.locator("[data-test='complete-header']");
        this.completeText = page.locator("[data-test='complete-text']");
        this.backHomeButton = page.locator("[data-test='back-to-products']");
    }

    public InventoryPage clickBackHome() {
        backHomeButton.click();
        return new InventoryPage(page);
    }

    public Locator getTitle() {
        return title;
    }

    public Locator getCompleteHeader() {
        return completeHeader;
    }

    public Locator getCompleteText() {
        return completeText;
    }

    public Locator getBackHomeButton() {
        return backHomeButton;
    }

    public Page getPage() {
        return page;
    }
}
