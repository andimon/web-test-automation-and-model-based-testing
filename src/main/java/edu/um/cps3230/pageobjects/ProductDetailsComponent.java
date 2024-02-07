package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class for product details page object.
 */
public class ProductDetailsComponent {

    private final WebDriverMethods webDriverMethods;

    /**
     * Interact with page object via web driver.
     *
     * @param webDriver WebDriver Instance
     */
    public ProductDetailsComponent(WebDriver webDriver) {
        webDriverMethods = new WebDriverMethods(webDriver);
    }

    /**
     * Assuming that we are in a product details page.
     *
     * @return The product title.
     */
    public String getProductTitle() {
        return webDriverMethods.getText(By.className("product_detail_summary_title"));
    }

    /**
     * Assuming that we are in product details page.
     *
     * @return The stock status of the product.
     */
    public StockStatus getStockStatus() {
        String stockStatusMessage = webDriverMethods.getText(By.className("product_detail_summary_stock"));
        for (StockStatus stockStatus : StockStatus.values()) {
            if (stockStatusMessage.contains(stockStatus.stock_status_message)) {
                return stockStatus;
            }
        }
        throw new IllegalArgumentException("No stock status with status " + stockStatusMessage + " found");
    }
}
