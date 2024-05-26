package com.epam.reportportal.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils {

    private static final Logger LOGGER = LogManager.getRootLogger();
     private WebDriver driver = DriverManager.getInstance().getDriver();

    public void dragAndDrop(WebElement from, WebElement to) {
        Actions action = new Actions(DriverManager.getDriver());
        action.dragAndDrop(from, to).perform();
    }

    public static boolean resizeElement(WebElement element, int xOffset, int yOffset) {
        boolean elementIsResized = false;
        try {
            if (element.isDisplayed()) {
                elementIsResized = true;
                Actions action = new Actions(DriverManager.getDriver());
                action.clickAndHold(element).moveByOffset(xOffset, yOffset).release().perform();
            } else {
                LOGGER.warn("Element was not displayed to drag");
            }
        } catch (Exception e) {
            LOGGER.warn("Unable to resize " + element + " - " + e.getMessage());
        }
        return elementIsResized;
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickElement(WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static boolean isScrolledIntoView (WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

        boolean isScrolledIntoView = (Boolean) ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
            "var elem = arguments[0],                 " +
                "  box = elem.getBoundingClientRect(),    " +
                "  cx = box.left + box.width / 2,         " +
                "  cy = box.top + box.height / 2,         " +
                "  e = document.elementFromPoint(cx, cy); " +
                "for (; e; e = e.parentElement) {          " +
                "  if (e === elem)                        " +
                "    return true;                         " +
                "}                                        " +
                "return false;                            ", element);
        return isScrolledIntoView;
    }
}
