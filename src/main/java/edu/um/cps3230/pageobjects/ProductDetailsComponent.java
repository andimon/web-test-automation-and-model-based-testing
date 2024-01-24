package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsComponent {

    private final WebDriverMethods webDriverMethods;

    public ProductDetailsComponent(WebDriver webDriver) {
        webDriverMethods = new WebDriverMethods(webDriver);
    }


    public String getProductTitle() {
        return webDriverMethods.getText(By.className("product_detail_summary_title"));
    }

    public StockStatus getStockStatus() {
        String stockStatusMessage = webDriverMethods.getText(By.className("product_detail_summary_stock"));
        for (StockStatus stockStatus : StockStatus.values()) {
            if (stockStatus.stock_status_message.equals(stockStatusMessage)) {
                return stockStatus;
            }
        }
        throw new IllegalArgumentException("No stock status with status " + stockStatusMessage + " found");
    }
}
