package edu.um.cps3230.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverMethods {
    Duration timeoutInSeconds = Duration.ofSeconds(20);
    private final WebDriver webDriver;

    public WebDriverMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void click(By element) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElement(element).click();
                    return true;
                });
    }

    public void click(By element, int index) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElements(element).get(index).click();
                    return true;
                });
    }

    public void sendKeys(By element, String charSequence) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElement(element).sendKeys(charSequence);
                    return true;
                });
    }

    public String getText(By element) {
        return new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> d.findElement(element).getText());
    }

    public String getText(By element, int index) {
        return new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> d.findElements(element).get(index).getText());
    }
}
