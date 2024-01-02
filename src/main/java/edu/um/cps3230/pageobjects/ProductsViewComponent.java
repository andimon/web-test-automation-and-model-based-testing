package test.store.webtestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsViewComponent {
    private WebDriver webDriver;

    public ProductsViewComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public int getNumberOfProductsInPage() {
        String product_counter = webDriver.findElement(By.id("products_showing_counter")).getText();
        product_counter = product_counter.replaceAll("Found [0-9]+ out of ", "");
        return Integer.parseInt(product_counter.replaceAll(" products", ""));
    }

    public void clickOnFistProductInPage() {
        WebElement webElement = webDriver.findElements(By.className("product_detail_summary_title")).get(0);
        webElement.click();
    }
}
