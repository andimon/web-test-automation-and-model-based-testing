package edu.um.cps3230.pageobjects;

import org.openqa.selenium.*;
public class NavigationComponent {

    private final WebDriverMethods webDriverMethods;


    public NavigationComponent(WebDriver webDriver) {
        this.webDriverMethods = new WebDriverMethods(webDriver);
    }

    public void returnToHomePage() {
        webDriverMethods.click(By.id("home_logo"));
    }


    private void inputExpression(String expression) {
        webDriverMethods.sendKeys(By.name("header-search-text"), expression);
    }

    private void clickSearchButton() {
        webDriverMethods.click(By.name("header_search_submit"));
    }

    public void search(String searchVal) {
        inputExpression(searchVal);
        clickSearchButton();
    }

    public void clickSectionById(Category category) {
        webDriverMethods.click(By.xpath("//button[@id='" + category.buttonId + "']"));
        webDriverMethods.click(By.xpath("//button[@type='button' and contains(@onclick, 't=" + category.queryParam + "')]"));
    }

    public void login(String email, String pass) {
        webDriverMethods.click(By.xpath("//i[contains(@class, 'far fa-user fa-lg')]"));
        webDriverMethods.sendKeys(By.xpath("//input[contains(@id, 'login_container_details_email')]"), email);
        webDriverMethods.sendKeys(By.xpath("//input[contains(@id, 'login_container_details_password')]"), pass);
        webDriverMethods.click(By.xpath("//div[contains(@class, 'login_container_details_button_blue')]"));
    }

    public void logout() {
        webDriverMethods.click(By.id("login_icon_blue"));
        webDriverMethods.click(By.xpath("//div[.='Logout']"));
    }

    public void goToPurchasePage() {
        webDriverMethods.click(By.xpath("//div[contains(@class,'d-inline')]"));
    }
}
