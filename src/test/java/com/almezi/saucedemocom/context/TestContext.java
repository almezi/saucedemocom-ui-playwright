package com.almezi.saucedemocom.context;

import com.almezi.saucedemocom.pages.InventoryPage;
import com.almezi.saucedemocom.pages.LoginPage;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class TestContext {

    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

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
}
