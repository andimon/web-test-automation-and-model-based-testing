package edu.um.cps3230;

import edu.um.cps3230.pageobjects.ProductDetailsComponent;
import edu.um.cps3230.pageobjects.ProductsViewComponent;
import edu.um.cps3230.pageobjects.NavigationComponent;
import edu.um.cps3230.pageobjects.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebStoreOperator {
    private WebDriver webDriver;
    private NavigationComponent searchComponent;
    private ProductsViewComponent productsViewComponent;

    private ProductDetailsComponent productDetailsComponent;

    public WebStoreOperator(WebDriver webDriver) {
        this.webDriver = webDriver;
        searchComponent = new NavigationComponent(webDriver);
        productsViewComponent = new ProductsViewComponent(webDriver);
        productDetailsComponent = new ProductDetailsComponent(webDriver);
    }

    public void initiateWebStore() {
        webDriver.manage().window().maximize();
        webDriver.get("https://www.klikk.com.mt");
    }

    public void searchProduct(String searchQuery) {
        searchComponent.search(searchQuery);
    }

    public void returnToHome() {
        searchComponent.returnToHomePage();
    }

    public boolean isProductPageEmpty(){
        return productsViewComponent.getNumberOfProducts()==0;
    }

    public void selectFirstProduct(){
        int firstProductIndex = 0;
        productsViewComponent.selectProduct(firstProductIndex);
    }

    public void clickSectionById(Category category){
        webDriver.findElement(By.xpath("//button[@id='"+ category.button_id+"']")).click();
        webDriver.findElement(By.xpath("//button[@onclick=\"location.href='/shop?c=1049&t=_computing';\"]")).click();
    }

    public boolean isProductInStock(){
        return productDetailsComponent.getStockStatus().equals("In stock");
    }
}
