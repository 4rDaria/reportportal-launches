package com.epam.reportportal.pages.launches;

import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.common.ElWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class HamburgerMenu extends ElWrapper {

    private WebDriver driver;
    public HamburgerMenu(WebElement element) {
        super(element);
    }

    public List<WebElement> menuItems() {
        return driver.findElements(By.xpath("getMenuItems"));
    }

    public WebElement menuItemByAction() {
        return driver.findElement(By.xpath("getMenuItemByAction"));
    }

    public ModalWindow deleteLaunchModal() {
        WebElement deleteLaunchModalElement = driver.findElement(By.id("deleteLaunchModal"));
        return new ModalWindow(deleteLaunchModalElement);
    }
}
