package com.epam.reportportal.pages;

import com.epam.reportportal.services.AbstractPage;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class DroppablePage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String PAGE_URL = "https://jqueryui.com/droppable/";


    @FindBy(id = "draggable")
    private WebElement draggableElement;

    @FindBy(id = "droppable")
    private WebElement droppableElement;

    @FindBy(xpath = "//*[text()='Widget Factory']")
    private WebElement widgetFactoryElement;

    public DroppablePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public DroppablePage openPage() {
        driver.navigate().to(PAGE_URL);
        LOGGER.info("Page opened successfully");
        return this;
    }
}
