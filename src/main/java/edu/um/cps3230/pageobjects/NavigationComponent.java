package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//The search component consists of a text field and a search button.
public class SearchComponent {
    private WebDriver webDriver;
    public SearchComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void inputExpression(String expression){
        webDriver.findElement(By.name("header-search-text")).sendKeys(expression);
    }

    public void clickSearchButton(){
        webDriver.findElement(By.xpath("//i[contains(@class, 'fas fa-search')]")).click();
    }

    public void search(String expression){
        inputExpression(expression);
        clickSearchButton();
    }
}
