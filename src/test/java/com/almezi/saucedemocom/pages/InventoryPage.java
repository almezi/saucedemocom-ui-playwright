package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {

    private final Page page;
    private final Locator title;
    private final Locator cartBadge;
    private final Locator cartLink;

    public InventoryPage(Page page) {
        this.page = page;
        this.title = page.locator("[data-test='title']");
        this.cartBadge = page.locator("[data-test='shopping-cart-badge']");
        this.cartLink = page.locator("[data-test='shopping-cart-link']");
    }

    public Locator getTitle() {
        return title;
    }

    public Locator getCartBadge() {
        return cartBadge;
    }

    public Locator getCartLink() {
        return cartLink;
    }

    public Page getPage() {
        return page;
    }
}
