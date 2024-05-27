package com.epam.reportportal.pages.launches;

import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.common.ElWrapper;
import com.epam.reportportal.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionMenu extends ElWrapper {

    //private final WebDriver driver = DriverManager.getDriver();
    public ActionMenu(WebElement element) {
        super(element);
    }

    public List<WebElement> actionMenuItems() {
        return element.findElements(By.cssSelector("div[class*='menu-item'] > span"));
    }

    public WebElement actionMenuItemByAction(String action) {
        List<WebElement> menuItems = element.findElements(By.cssSelector("div[class*='menu-item'] > span"));

        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().equals(action)) {
                return menuItem;
            }
        }

        return null;
    }

    public ModalWindow compareLaunchesModal() {
        WebElement compareButton = actionMenuItemByAction("Compare");
        compareButton.click();

        WebElement modalElement = element.findElement(By.xpath("//div[contains(@class, 'modalLayout__modal-window')]"));
        return new ModalWindow(modalElement);
    }

}
