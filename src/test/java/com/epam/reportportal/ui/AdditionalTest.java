package com.epam.reportportal.ui;

import com.epam.reportportal.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AdditionalTest {

    private final WebDriver driver = DriverManager.getDriver();

    @Test
    public void resizeElement() throws InterruptedException {
        driver.get("https://jqueryui.com/resizable/");
        driver.switchTo().frame(0);

        WebElement resizeableElement = driver.findElement(By.xpath("//div[contains(@class,'ui-resizable-se')]"));
        resize(resizeableElement, 200, 200);
        Thread.sleep(1000);
    }

    public void resize(WebElement elementToResize, int xOffset, int yOffset) {
        try {
            if (elementToResize.isDisplayed()) {
                Actions action = new Actions(driver);
                action.clickAndHold(elementToResize).moveByOffset(xOffset, yOffset).release().build().perform();
            } else {
                System.out.println("Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + elementToResize + "is not attached to the page document "  + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to resize" + elementToResize + " - " + e.getStackTrace());
        }
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        dragAndDropAction(draggable, droppable);
        Thread.sleep(1000);
    }

    public void dragAndDropAction(WebElement from, WebElement to) {
        Actions action = new Actions(driver);
        action.dragAndDrop(from,to).perform();
    }

    @Test
    public void testScrollToElementAndVerifyPresence() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        WebElement widgetFactoryElement = driver.findElement(By.xpath("//*[text()='Widget Factory']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widgetFactoryElement);

        assertTrue(widgetFactoryElement.isDisplayed(), "The element with text 'Widget Factory' should be visible on the page.");
        Thread.sleep(1000);
    }

    @Test
    public void scrollToElementAndVerifyPresence() {
        driver.get("https://jqueryui.com/droppable/");
        WebElement widgetFactoryElement = driver.findElement(By.xpath("//*[text()='Widget Factory']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widgetFactoryElement);

        boolean isScrolledIntoView = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {          " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            ", widgetFactoryElement);

        assertTrue(isScrolledIntoView, "The element with text 'Widget Factory' should be scrolled into view.");
    }

    @Test
    public void clickElement() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        WebElement widgetFactoryElement = driver.findElement(By.xpath("//*[text()='Widget Factory']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetFactoryElement);

        String expectedUrl = "https://jqueryui.com/widget/";
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(1000);

        assertEquals(expectedUrl, currentUrl);
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
