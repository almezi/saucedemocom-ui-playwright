package com.almezi.saucedemocom.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

public class InventoryPage {

    private final Page page;
    private final Locator title;
    private final Locator cartBadge;
    private final Locator cartLink;
    private final Locator productSort;
    private final Locator itemNames;
    private final Locator menuButton;
    private final Locator logoutLink;
    private final Locator resetLink;
    private final Locator closeMenuButton;

    public InventoryPage(Page page) {
        this.page = page;
        this.title = page.locator("[data-test='title']");
        this.cartBadge = page.locator("[data-test='shopping-cart-badge']");
        this.cartLink = page.locator("[data-test='shopping-cart-link']");
        this.productSort = page.locator("[data-test='product-sort-container']");
        this.itemNames = page.locator("[data-test='inventory-item-name']");
        this.menuButton = page.locator("#react-burger-menu-btn");
        this.logoutLink = page.locator("#logout_sidebar_link");
        this.resetLink = page.locator("#reset_sidebar_link");
        this.closeMenuButton = page.locator("#react-burger-cross-btn");
    }

    public InventoryPage addItemToCart(String productName) {
        getItemContainer(productName)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart"))
                .click();
        return this;
    }

    public InventoryPage removeItemFromCart(String productName) {
        getItemContainer(productName)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
                .click();
        return this;
    }

    public InventoryPage sortBy(String label) {
        productSort.selectOption(new SelectOption().setLabel(label));
        return this;
    }

    public InventoryPage openMenu() {
        menuButton.click();
        return this;
    }

    public LoginPage logout() {
        openMenu();
        logoutLink.click();
        return new LoginPage(page);
    }

    public InventoryPage resetAppState() {
        openMenu();
        resetLink.click();
        closeMenuButton.click();
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

    public Locator getFirstItemName() {
        return itemNames.first();
    }

    public Locator getItemContainer(String productName) {
        return page.locator("[data-test='inventory-item']")
                .filter(new Locator.FilterOptions().setHas(
                        page.getByText(productName, new Page.GetByTextOptions().setExact(true))));
    }

    public Page getPage() {
        return page;
    }
}
