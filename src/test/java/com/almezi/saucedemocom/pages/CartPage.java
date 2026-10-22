package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage {

    private final Page page;
    private final Locator title;
    private final Locator checkoutButton;
    private final Locator continueShoppingButton;
    private final Locator cartItems;
    private final Locator cartBadge;

    public CartPage(Page page) {
        this.page = page;
        this.title = page.locator("[data-test='title']");
        this.checkoutButton = page.locator("[data-test='checkout']");
        this.continueShoppingButton = page.locator("[data-test='continue-shopping']");
        this.cartItems = page.locator("[data-test='inventory-item']");
        this.cartBadge = page.locator("[data-test='shopping-cart-badge']");
    }

    public Locator getItemByName(String itemName) {
        return getItemContainer(itemName);
    }

    public Locator getItemQuantity(String itemName) {
        return getItemByName(itemName).locator("[data-test='item-quantity']");
    }

    public CartPage removeItem(String itemName) {
        getItemContainer(itemName)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
                .click();
        return this;
    }

    public InventoryPage continueShopping() {
        continueShoppingButton.click();
        return new InventoryPage(page);
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

    public Locator getContinueShoppingButton() {
        return continueShoppingButton;
    }

    public Locator getCartItems() {
        return cartItems;
    }

    public Locator getCartBadge() {
        return cartBadge;
    }

    public Locator getItemContainer(String itemName) {
        return cartItems.filter(new Locator.FilterOptions().setHas(
                page.getByText(itemName, new Page.GetByTextOptions().setExact(true))));
    }

    public Page getPage() {
        return page;
    }
}
