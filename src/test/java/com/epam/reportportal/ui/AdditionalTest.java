package com.epam.reportportal.ui;

import com.epam.reportportal.pages.DroppablePage;
import com.epam.reportportal.pages.ResizablePage;
import com.epam.reportportal.utils.ActionsUtils;
import com.epam.reportportal.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AdditionalTest {

    private final WebDriver driver = DriverManager.getInstance().getDriver();

    @Test
    public void resizeElement() throws InterruptedException {
        ResizablePage page = new ResizablePage(driver)
            .openPage();

        ActionsUtils.resizeElement(driver, page.getResizeableElement(), 200, 200);

        //we can check if element resize or add another checking according to future task
        Thread.sleep(1000);
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        DroppablePage page = new DroppablePage(driver)
            .openPageAndSwitchToFrame();

        ActionsUtils.dragAndDrop(driver, page.getDraggableElement(), page.getDroppableElement());

        //we can check if element resize or add another checking according to future task
        Thread.sleep(1000);
    }

    @Test
    public void scrollToElementAndVerifyPresence() throws InterruptedException {
        DroppablePage page = new DroppablePage(driver)
            .openPage();

        ActionsUtils.scrollToElement(driver, page.getWidgetFactoryElement());

        assertTrue(page.getWidgetFactoryElement().isDisplayed(), "The element with text 'Widget Factory' should be visible on the page.");
        Thread.sleep(1000);
    }

    @Test
    public void clickElement() throws InterruptedException {
        DroppablePage page = new DroppablePage(driver)
            .openPage();

        ActionsUtils.scrollToElement(driver, page.getWidgetFactoryElement());
        ActionsUtils.clickElement(driver, page.getWidgetFactoryElement());

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
