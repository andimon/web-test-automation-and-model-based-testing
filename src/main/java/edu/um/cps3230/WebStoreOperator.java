package edu.um.cps3230;

import edu.um.cps3230.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebStoreOperator {
    public WebDriver webDriver;
    private final NavigationComponent navigationComponent;
    private final ProductsViewComponent productsViewComponent;
    private final ProductDetailsComponent productDetailsComponent;

    private final WebDriverMethods webDriverMethods;


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

    public void selectProduct(int productIndex) {
        productsViewComponent.selectProduct(productIndex);
    }

    public void goToPurchasePage() {
        navigationComponent.goToPurchasePage();
    }

    public void clearCart() {
        //assuming current page is purchase page
        webDriver.findElement(By.xpath("//button[contains(@class,'btn btn-block btn-clear-cart cart-actions')]")).click();
    }

    public void addToCart() {
        webDriverMethods.click(By.id("product_add_to_cart"));
    }

    public StockStatus getStockStatus() {
        return productDetailsComponent.getStockStatus();
    }

    public void login(String user, String pass) {
        navigationComponent.login(user, pass);
    }

    public void logout() {
        try {
            //try to logout
            navigationComponent.logout();
        } catch (TimeoutException e) {
            //confirm that user is already logged out
            webDriver.findElement(By.id("login_icon_blue"));
        }
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver=webDriver;
    }
}
