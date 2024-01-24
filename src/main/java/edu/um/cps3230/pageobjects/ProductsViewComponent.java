package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsViewComponent {

    private final WebDriverMethods webDriverMethods;

    public ProductsViewComponent(WebDriver webDriver) {
        webDriverMethods = new WebDriverMethods(webDriver);
    }

    public int getNumberOfProducts() {
        String productCounter = webDriverMethods.getText(By.id("products_showing_counter"));
        productCounter = productCounter.replaceAll("Found [0-9]+ out of ", "");
        return Integer.parseInt(productCounter.replaceAll(" products", ""));
    }

    public String selectProduct(int index) {
        String productName = webDriverMethods.getText(By.xpath("//p[contains(@class, 'product_card_grid_title')]"), index);
        webDriverMethods.click(By.xpath("//div[@itemtype='http://schema.org/Product']"));
        return productName;
    }
}
