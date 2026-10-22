package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage {

    private final Page page;
    private final Locator title;
    private final Locator checkoutButton;
    private final Locator cartItems;

    public CartPage(Page page) {
        this.page = page;
        this.title = page.locator("[data-test='title']");
        this.checkoutButton = page.locator("[data-test='checkout']");
        this.cartItems = page.locator("[data-test='inventory-item']");
    }

    public Locator getItemByName(String itemName) {
        return cartItems.filter(new Locator.FilterOptions().setHas(page.getByText(itemName)));
    }

    public Locator getItemQuantity(String itemName) {
        return getItemByName(itemName).locator("[data-test='item-quantity']");
    }

    public CheckoutStepOnePage clickCheckout() {
        checkoutButton.click();
        return new CheckoutStepOnePage(page);
    }

    public Locator getTitle() {
        return title;
    }

    public Locator getCheckoutButton() {
        return checkoutButton;
    }

    public Locator getCartItems() {
        return cartItems;
    }

    public Page getPage() {
        return page;
    }
}
