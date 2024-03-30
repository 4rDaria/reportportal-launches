package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.common.ElWrapper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ActionMenu extends ElWrapper {
    public ActionMenu(SelenideElement element) {
        super(element);
    }

    public ElementsCollection actionMenuItems() {
        return $$("actionMenuItems");
    }

    public SelenideElement actionMenuItemByAction() {
        return $("actionMenuItemByAction");
    }

    public ModalWindow compareLaunchesModal() {
        return new ModalWindow($("compareLaunchesModal"));
    }

}
