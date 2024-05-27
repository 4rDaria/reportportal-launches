package com.epam.reportportal.pages.launches;

import static com.epam.reportportal.constants.Constants.DELETE_ACTION;

import com.epam.reportportal.pages.common.ElWrapper;
import com.epam.reportportal.pages.common.ModalWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HamburgerMenu extends ElWrapper {

    public HamburgerMenu(WebElement element) {
        super(element);
    }

    public List<WebElement> getMenuItems() {
        return element.findElements(By.cssSelector("div.hamburgerMenuItem__hamburger-menu-item--oPTFm"));
    }

    public WebElement getMenuItemByAction(String action) {
        for (WebElement menuItem : getMenuItems()) {
            if (menuItem.getText().equals(action)) {
                return menuItem;
            }
        }
        return null;
    }

    public ModalWindow deleteLaunchModal(WebDriver driver) {
        WebElement deleteButton = getMenuItemByAction(DELETE_ACTION);
        deleteButton.click();

        WebElement modalWindowElement = driver.findElement(By.xpath("//div[contains(@class, 'modalLayout__modal-window')]"));
        return new ModalWindow(modalWindowElement);
    }

    public void deleteLaunch(WebDriver driver) throws InterruptedException {
        deleteLaunchModal(driver).buttonWithText(DELETE_ACTION).click();
        Thread.sleep(1000);
        driver.navigate().refresh();
    }
}