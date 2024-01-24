package edu.um.cps3230;

import edu.um.cps3230.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebStoreOperator {
    private WebDriver webDriver;
    private NavigationComponent navigationComponent;
    private ProductsViewComponent productsViewComponent;

    private ProductDetailsComponent productDetailsComponent;


    public WebStoreOperator(WebDriver webDriver) {
        this.webDriver = webDriver;
        initiateWebStore();
        navigationComponent = new NavigationComponent(webDriver);
        productsViewComponent = new ProductsViewComponent(webDriver);
        productDetailsComponent = new ProductDetailsComponent(webDriver);
    }

    public void initiateWebStore() {
        webDriver.manage().window().maximize();
        webDriver.get("https://www.klikk.com.mt");
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

    public void clickSectionById(Category category) {
        webDriver.findElement(By.xpath("//button[@id='" + category.buttonId + "']")).click();
        webDriver.findElement(By.xpath("//button[@onclick=\"location.href='/shop?c=1049&t=_computing';\"]")).click();
    }

    public void goToPurchasePage() {
        navigationComponent.goToPurchasePage();
    }

    public void clearCart() {
        //assuming current page is purchase page
        webDriver.findElement(By.xpath("btn btn-block btn-clear-cart cart-actions")).click();
    }

    public void addToCart() {
        webDriver.findElement(By.id("product_add_to_cart")).click();
    }

    public StockStatus getStockStatus() {
        return productDetailsComponent.getStockStatus();
    }

    public boolean isProductInStock() {
        return productDetailsComponent.getStockStatus().equals("In stock");
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
}
