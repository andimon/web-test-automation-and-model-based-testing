package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsComponent {
    private WebDriver webDriver;
    public ProductDetailsComponent(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getProductTitle(){
        return webDriver.findElement(By.className("product_detail_summary_title")).getText();
    }

    public StockStatus getStockStatus(){
        String stock_status_message =  webDriver.findElement(By.className("product_detail_summary_stock")).getText();
        for (StockStatus stockStatus : StockStatus.values()){
            if(stockStatus.stock_status_message.equals(stock_status_message)){
                return stockStatus;
            }
        }
        throw new IllegalArgumentException("No stock status with status " + stock_status_message + " found");
    }
}
