package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

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

    public InventoryPage addItemToCart(String productName) {
        Locator itemContainer = page.locator("[data-test='inventory-item']")
                .filter(new Locator.FilterOptions().setHas(page.getByText(productName)));
        itemContainer.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart")).click();
        return this;
    }

    public CartPage clickCartLink() {
        cartLink.click();
        return new CartPage(page);
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
