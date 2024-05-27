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
public class ResizablePage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String PAGE_URL = "https://jqueryui.com/resizable/";

    @FindBy(xpath = "//div[contains(@class,'ui-resizable-se')]")
    private WebElement resizeableElement;

    public ResizablePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ResizablePage openPage() {
        driver.navigate().to(PAGE_URL);
        driver.switchTo().frame(0);
        LOGGER.info("Page opened successfully");
        return this;
    }
}
