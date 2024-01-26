package edu.um.cps3230.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverMethods {
    Duration timeoutInSeconds = Duration.ofSeconds(40);
    private final WebDriver webDriver;
    private int count = 0;
    private final int maxTries = 3;


    public WebDriverMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //A timeout exception may have been caused by a 504 gateway error
    //We refresh the page at a maximum of 3 times to clear the error
    public void click(By element) {
        while (true) {
            try {
                _click(element);
                break;
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                if ((++count) == maxTries) {
                    webDriver.navigate().refresh();
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    public void sendKeys(By element, String charSequence) {
        while (true) {
            try {
                _sendKeys(element, charSequence);
                break;
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                if ((++count) == maxTries) {
                    webDriver.navigate().refresh();
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    public String getText(By element) {
        while (true) {
            try {
                return _getText(element);
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                if ((++count) == maxTries) {
                    webDriver.navigate().refresh();
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    public String getText(By element, int index) {
        while (true) {
            try {
                return _getText(element, index);
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                if ((++count) == maxTries) {
                    webDriver.navigate().refresh();
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }


    private void _click(By element) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElement(element).click();
                    return true;
                });
    }


    private void _sendKeys(By element, String charSequence) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElement(element).sendKeys(charSequence);
                    return true;
                });
    }

    private String _getText(By element) {
        return new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> d.findElement(element).getText());
    }

    private String _getText(By element, int index) {
        return new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> d.findElements(element).get(index).getText());
    }
}
