package com.epam.reportportal.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private WebDriver driver = DriverManager.getInstance().getDriver();

    public static void dragAndDrop(WebDriver driver, WebElement from, WebElement to) {
        Actions action = new Actions(driver);
        action.dragAndDrop(from, to).perform();
    }

    public static void resizeElement(WebDriver driver, WebElement element, int xOffset, int yOffset) {
        //boolean elementIsResized = false;
        try {
            if (element.isDisplayed()) {
                //elementIsResized = true;
                Actions action = new Actions(driver);
                action.clickAndHold(element).moveByOffset(xOffset, yOffset).release().build().perform();
                LOGGER.info("Element has been resized");
            } else {
                LOGGER.warn("Element was not displayed to drag");
            }
        } catch (NoSuchElementException e) {
            LOGGER.warn("Element " + element + " was not found in DOM " + e.getStackTrace());
        }
        //return elementIsResized;
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickElement(WebDriver driver,WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

}
