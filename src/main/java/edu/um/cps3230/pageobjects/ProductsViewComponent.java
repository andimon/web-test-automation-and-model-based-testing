package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class for product details page object.
 */
public class ProductsViewComponent {

    private final WebDriverMethods webDriverMethods;

    /**
     * Interact with page object via web driver.
     *
     * @param webDriver WebDriver Instance
     */
    public ProductsViewComponent(WebDriver webDriver) {
        webDriverMethods = new WebDriverMethods(webDriver);
    }

    /**
     * @return Total products on page.
     */
    public int getNumberOfProducts() {
        String productCounter = webDriverMethods.getText(By.id("products_showing_counter"));
        productCounter = productCounter.replaceAll("Found [0-9]+ out of ", "");
        return Integer.parseInt(productCounter.replaceAll(" products", ""));
    }

    /**
     * Select the (index+1)th product
     *
     * @param index Location index of product to click
     * @return Name of the product clicked.
     */

    public String selectProduct(int index) {
        String productName = webDriverMethods.getText(By.xpath("//p[contains(@class, 'product_card_grid_title')]"), index);
        webDriverMethods.click(By.xpath("//div[@itemtype='http://schema.org/Product']"), index);
        return productName;
    }
}
