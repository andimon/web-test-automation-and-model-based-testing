package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsComponent {
    private WebDriver webDriver;
    public ProductDetailsComponent(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getProductTitle(){
        return webDriver.findElement(By.className("products_grid")).getText();
    }

    public String getStockStatus(){
        return webDriver.findElement(By.className("product_detail_summary_stock")).getText();
    }
}
