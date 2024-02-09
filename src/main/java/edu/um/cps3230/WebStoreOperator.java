package edu.um.cps3230;

import edu.um.cps3230.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

/**
 * This class provides an SUT for model based testing.
 */
public class WebStoreOperator {
    public WebDriver webDriver;
    private final NavigationComponent navigationComponent;
    private final ProductsViewComponent productsViewComponent;
    private final ProductDetailsComponent productDetailsComponent;

    private final WebDriverMethods webDriverMethods;

    public boolean isCartEmpty = true;
    public boolean inStock = false;
    public boolean loggedIn = false;
    public int productsInPage = 0;


    public WebStoreOperator(WebDriver webDriver) {
        this.webDriver = webDriver;
        navigationComponent = new NavigationComponent(webDriver);
        productsViewComponent = new ProductsViewComponent(webDriver);
        productDetailsComponent = new ProductDetailsComponent(webDriver);
        webDriverMethods = new WebDriverMethods(webDriver);
    }


    public void searchProduct(String searchQuery) {
        webDriver.navigate().refresh();
        navigationComponent.search(searchQuery);
    }

    public void returnToHome() {
        navigationComponent.returnToHomePage();
    }

    public int getNumberOfProducts() {
        return productsViewComponent.getNumberOfProducts();
    }

    public String selectProduct(int productIndex) {
        String productName = productsViewComponent.selectProduct(productIndex);
        productsInPage = 0;
        return productName;
    }

    public void goToPurchasePage() {
        navigationComponent.goToPurchasePage();
        inStock = false;
        productsInPage = 0;
    }

    /**
     * Assuming that we are in the purchase page then we clear cart.
     */
    public void clearCart() {
        //assuming current page is purchase page
        webDriverMethods.click(By.xpath("//button[contains(@class,'btn btn-block btn-clear-cart cart-actions')]"));
        isCartEmpty = true;
    }

    public void addToCart() {
        webDriverMethods.click(By.id("product_add_to_cart"));
        isCartEmpty = false;
    }

    public StockStatus getStockStatus() {
        StockStatus stockStatus = productDetailsComponent.getStockStatus();
        inStock = stockStatus == StockStatus.IN_STOCK;
        return stockStatus;
    }

    public void login(String user, String pass) {
        navigationComponent.login(user, pass);
        loggedIn = true;
    }

    /**
     * Logout
     * If we cannot manage then check if user is already logged out.
     * If the user is not logged out then this method will throw an exception.
     */
    public void logout() {
        try {
            //try to log out
            navigationComponent.logout();
        } catch (TimeoutException e) {
            //confirm that user is already logged out
            webDriver.findElement(By.id("login_icon_blue"));
        }
        loggedIn = false;
    }
}
