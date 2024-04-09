package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.pages.common.ElWrapper;

import static com.codeborne.selenide.Selenide.$;
import static com.epam.reportportal.constants.Constants.START_TIME_CELL_CSS;
import static java.lang.String.format;

public class GridRow extends ElWrapper {

    public GridRow(SelenideElement element) {
        super(element);
    }

    public String launchId() {
        return element.getAttribute("data-id");
    }

    public SelenideElement category(String category) {
        return element.$(format("div[class*='%s']", category));
    }

    public SelenideElement categoryCount(String category) {
        return category(category).$("div").$("a");
    }

    public String startTime() {
        return category(START_TIME_CELL_CSS).$("div").$$("span").last().innerText();
    }

    public SelenideElement checkbox() {
        return element.$("input[type='checkbox']");
    }

    public HamburgerMenu hamburgerMenu() {
        element.$("div[class*='hamburger-icon--']").click();
        return new HamburgerMenu($("div[class*='hamburger-menu-actions']"));
    }

    public SelenideElement donutElementByType(String type) {
        return element.$x(format(".//div[contains(@class,'launchSuiteGrid__%s')]//*[@class='donut']", type));
    }
}
