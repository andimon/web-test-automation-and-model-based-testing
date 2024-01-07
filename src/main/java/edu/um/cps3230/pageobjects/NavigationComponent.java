package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//The search component consists of a text field and a search button.
public class NavigationComponent {
    private WebDriver webDriver;
    public NavigationComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void returnToHomePage(){
        webDriver.findElement(By.id("home_logo")).click();
    }


    private void inputExpression(String expression){
        webDriver.findElement(By.name("header-search-text")).sendKeys(expression);
    }

    private void clickSearchButton(){
        webDriver.findElement(By.xpath("//i[contains(@class, 'fas fa-search')]")).click();
    }

    public void search(String searchVal){
        inputExpression(searchVal);
        clickSearchButton();
    }

    public void clickSectionById(Category category){
        webDriver.findElement(By.xpath("//button[@id='"+ category.button_id+"']")).click();
        webDriver.findElement(By.xpath("//button[@onclick=\"location.href='/shop?c=1049&t=_computing';\"]")).click();
    }
}
