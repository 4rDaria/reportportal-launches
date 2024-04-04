package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.common.ElWrapper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ActionMenu extends ElWrapper {
    public ActionMenu(SelenideElement element) {
        super(element);
    }

    public ElementsCollection actionMenuItems(){
        return element.$$("div[class*='menu-item'] > span");
    }

    public SelenideElement actionMenuItemByAction(String action){
        return actionMenuItems().filterBy(text(action)).first();
    }

    public ModalWindow compareModal(){
        actionMenuItemByAction("Compare").click();
        return new ModalWindow($x("//div[contains(@class, 'modalLayout__modal-window')]"));
    }

}
