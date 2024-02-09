package edu.um.cps3230.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Generic Class to interact with a website.
 *
 * @author Andre
 */
public class WebDriverMethods {
    Duration timeoutInSeconds = Duration.ofSeconds(40);
    private final WebDriver webDriver;
    private int count = 0;
    private final int maxTries = 3;

    /**
     * Constructs a new WebDriverMethods objects to interact with a website given a WebDriver.
     *
     * @param webDriver
     */
    public WebDriverMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void _click(By element) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElement(element).click();
                    return true;
                });
    }

    private void _click(By element, int index) {
        new WebDriverWait(webDriver, timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) -> {
                    d.findElements(element).get(index).click();
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

    /**
     * Locates an element and clicks it.
     *
     * @param element The element to be clicked.
     */
    public void click(By element) {
        while (true) {
            try {
                _click(element);
                break;
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                webDriver.navigate().refresh();
                if ((++count) == maxTries) {
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    public void click(By element, int index) {
        while (true) {
            try {
                _click(element, index);
                break;
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                webDriver.navigate().refresh();
                if ((++count) == maxTries) {
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    /**
     * Locates an element and inputs charSequence in it.
     *
     * @param element      The element located.
     * @param charSequence The string sequence to be inputted in given element.
     */
    public void sendKeys(By element, String charSequence) {
        while (true) {
            try {
                _sendKeys(element, charSequence);
                break;
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                webDriver.navigate().refresh();
                if ((++count) == maxTries) {
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    /**
     * Locates an elements and gets its visible text.
     *
     * @param element The element located.
     * @return Returns the visible text of the given element.
     */
    public String getText(By element) {
        while (true) {
            try {
                return _getText(element);
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                webDriver.navigate().refresh();
                if ((++count) == maxTries) {
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }

    /**
     * Gets the visible text of the (index+1) sub-element.
     *
     * @param element Element evaluated.
     * @param index   Index of sub-element.
     * @return Returns the visible text of the (index+1) sub-element of the given element.
     */
    public String getText(By element, int index) {
        while (true) {
            try {
                return _getText(element, index);
            } catch (TimeoutException e) {
                //We try to refresh page in case of 504 gateway error
                webDriver.navigate().refresh();
                if ((++count) == maxTries) {
                    count = 0; //reset counter
                    throw e;
                }
            }
        }
    }
}
