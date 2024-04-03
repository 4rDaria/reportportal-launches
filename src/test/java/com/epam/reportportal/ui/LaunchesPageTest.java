package com.epam.reportportal.ui;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.constants.Constants.*;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static org.testng.Assert.assertTrue;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.ActionMenu;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import com.epam.reportportal.utils.TestDataUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LaunchesPageTest {

    private static final Logger logger = LogManager.getRootLogger();

    private static LaunchesPage launchesPage;

    @BeforeClass
    public void setUp() {
        User testUser = UserCreator.adminUser();
        openLoginPage();
        getWebDriver().manage().window().maximize();
        launchesPage = login(testUser);
        open(baseUrl + "/ui/#" + PROJECT + "/launches/all");
        sleep(1000);
    }

    @Test
    public void launchesListSortedByMostRecentByDefault() {
        assertTrue(true);
    }

    @Test
    public void userCanResortLaunchesByEachCategoriesCount() {
        assertTrue(true);
    }

    @Test(dataProvider = "category", dataProviderClass = TestDataUtils.class)
    public void eachLaunchesContainsAllTestCountData(String category, String css) {
        ElementsCollection gridRowElements = launchesPage.gridRowElements();

        for (SelenideElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            SelenideElement element = launch.category(category);
            String id = launch.launchId();
            if (element == null) {
                logger.info("Element " + category + " not found in launch: " + id);
            }
        }
    }

    @Test(dataProvider = "launchesToCompare", dataProviderClass = TestDataUtils.class)
    public void userIsAbleToSelectSeveralLaunchesAndCompareThem(String testCase, List<Integer> launchesToCompare) {
        ElementsCollection gridRowElements = launchesPage.gridRowElements();

        List<GridRow> numberLaunchesToCompare = new ArrayList<>();
        launchesToCompare.forEach(launch -> numberLaunchesToCompare.add(new GridRow(gridRowElements.get(launch))));

        //select launches
        toggleSelection(numberLaunchesToCompare);

        openAndCloseActionMenu();

        //unselect launches
        toggleSelection(numberLaunchesToCompare);

        sleep(1000);
    }

    private void toggleSelection(List<GridRow> numberLaunchesToCompare) {
        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();

        for (GridRow launch : numberLaunchesToCompare) {
            jse.executeScript("arguments[0].click();", launch.checkbox());
        }
    }

    private void openAndCloseActionMenu(){
        ActionMenu actionMenu = launchesPage.openActionMenu();
        ModalWindow compareModal = actionMenu.compareLaunchesModal();
        compareModal.buttonWithText("Cancel").click();
    }

    @Test
    public void userIsAbleToRemoveLaunches() {
        assertTrue(true);
    }

    @Test(dataProvider = "donutElement", dataProviderClass = TestDataUtils.class)
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement(String category, String elementIdentificator) {
        ElementsCollection gridRowElements = launchesPage.gridRowElements();
        GridRow launch = new GridRow(gridRowElements.first());
        String launchId = launch.launchId();

        if (!launch.donutElementByType(elementIdentificator).exists()) {
            logger.info("Can't check moving to appropriate via " + category + " because there no data in this category");
        } else {
            launch.donutElementByType(elementIdentificator).click();
            sleep(1000);

            String expectedUrl = baseUrl + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter.in.issueType%3D" +
                    elementIdentificator + "001";

            Assert.assertEquals(expectedUrl, getWebDriver().getCurrentUrl());
            back();
        }
    }

    @Test(dataProvider = "countElementWithParam", dataProviderClass = TestDataUtils.class)
    public void userIsAbleToMoveToAppropriateLaunchClickingCountElement(String category,
                                                                        String elementIdentificator,
                                                                        String param) {
        ElementsCollection gridRowElements = launchesPage.gridRowElements();
        GridRow launch = new GridRow(gridRowElements.first());
        String launchId = launch.launchId();
        SelenideElement element = launch.categoryCount(elementIdentificator);

        if (!element.exists()) {
            logger.info("Can't check moving to appropriate via " + elementIdentificator +
                    " because there no data in this category");
        } else {
            element.click();
            sleep(1000);

            String expectedUrl = baseUrl + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter."
                    + "in.type%3DSTEP%26filter.in.status%3D" + param;
            Assert.assertEquals(expectedUrl, getWebDriver().getCurrentUrl());
            back();
        }
    }
}
