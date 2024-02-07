package edu.um.cps3230.pageobjects;

import org.openqa.selenium.*;

/**
 * Class for navigation pane page object.
 *
 * @author Andre
 */
public class NavigationComponent {

    private final WebDriverMethods webDriverMethods;

    /**
     * Interact with page object via web driver.
     *
     * @param webDriver WebDriver Instance
     */
    public NavigationComponent(WebDriver webDriver) {
        this.webDriverMethods = new WebDriverMethods(webDriver);
    }

    private void inputExpression(String expression) {
        webDriverMethods.sendKeys(By.name("header-search-text"), expression);
    }

    private void clickSearchButton() {
        webDriverMethods.click(By.name("header_search_submit"));
    }


    public void returnToHomePage() {
        webDriverMethods.click(By.id("home_logo"));
    }

    /**
     * Search for an item.
     *
     * @param searchVal The search text to be inputted.
     */
    public void search(String searchVal) {
        inputExpression(searchVal);
        clickSearchButton();
    }

    /**
     * Click a main category.
     *
     * @param category The category to be clicked
     */
    public void clickSectionById(Category category) {
        webDriverMethods.click(By.xpath("//button[@id='" + category.buttonId + "']"));
        webDriverMethods.click(By.xpath("//button[@type='button' and contains(@onclick, 't=" + category.queryParam + "')]"));
    }

    /**
     * Login.
     *
     * @param email The email to be inputted.
     * @param pass  The password to be inputted.
     */
    public void login(String email, String pass) {
        webDriverMethods.click(By.xpath("//i[contains(@class, 'far fa-user fa-lg')]"));
        webDriverMethods.sendKeys(By.xpath("//input[contains(@id, 'login_container_details_email')]"), email);
        webDriverMethods.sendKeys(By.xpath("//input[contains(@id, 'login_container_details_password')]"), pass);
        webDriverMethods.click(By.xpath("//div[contains(@class, 'login_container_details_button_blue')]"));
    }

    /**
     * Logout (assuming that we are already logged in).
     */

    public void logout() {
        webDriverMethods.click(By.id("login_icon_blue"));
        webDriverMethods.click(By.xpath("//div[.='Logout']"));
    }

    /**
     * Go to purchase page (cart details + proceed to payment).
     */
    public void goToPurchasePage() {
        webDriverMethods.click(By.xpath("//a[contains(@href,'/cart')]"));
    }
}
