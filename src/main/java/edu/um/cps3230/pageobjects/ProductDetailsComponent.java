package test.store.webtestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsComponent {
    private WebDriver webDriver;
    public ProductDetailsComponent(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getProductTitle(){
        return webDriver.findElement(By.className("product_card_grid_title")).getText();
    }
}
