package com.almezi.saucedemocom.context;

import com.almezi.saucedemocom.pages.CartPage;
import com.almezi.saucedemocom.pages.CheckoutCompletePage;
import com.almezi.saucedemocom.pages.CheckoutStepOnePage;
import com.almezi.saucedemocom.pages.CheckoutStepTwoPage;
import com.almezi.saucedemocom.pages.InventoryPage;
import com.almezi.saucedemocom.pages.LoginPage;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class TestContext {

    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    public void setContext(BrowserContext context) {
        this.context = context;
    }

    public BrowserContext getContext() {
        return context;
    }

    public void setPage(Page page) {
        this.page = page;
        this.loginPage = new LoginPage(page);
        this.inventoryPage = new InventoryPage(page);
        this.cartPage = new CartPage(page);
        this.checkoutStepOnePage = new CheckoutStepOnePage(page);
        this.checkoutStepTwoPage = new CheckoutStepTwoPage(page);
        this.checkoutCompletePage = new CheckoutCompletePage(page);
    }

    public Page getPage() {
        return page;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public InventoryPage getInventoryPage() {
        return inventoryPage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public CheckoutStepOnePage getCheckoutStepOnePage() {
        return checkoutStepOnePage;
    }

    public CheckoutStepTwoPage getCheckoutStepTwoPage() {
        return checkoutStepTwoPage;
    }

    public CheckoutCompletePage getCheckoutCompletePage() {
        return checkoutCompletePage;
    }
}
