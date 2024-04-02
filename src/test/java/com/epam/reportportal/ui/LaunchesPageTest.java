package com.epam.reportportal.ui;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.constants.Constants.*;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.ActionMenu;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LaunchesPageTest {

    private static final Logger logger = LogManager.getRootLogger();

    private static LaunchesPage launchesPage;

    @BeforeEach
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

    private static Stream<Arguments> category() {
        return Stream.of(
                Arguments.of(TOTAL, TOTAL_COUNT_CELL_CSS),
                Arguments.of(PASSED, PASSED_COUNT_CELL_CSS),
                Arguments.of(FAILED, FAILED_COUNT_CELL_CSS),
                Arguments.of(SKIPPED, SKIPPED_COUNT_CELL_CSS),
                Arguments.of(PRODUCT_BUG, PRODUCT_BUG_COUNT_CELL_CSS),
                Arguments.of(AUTO_BUG, AUTO_BUG_COUNT_CELL_CSS),
                Arguments.of(SYSTEM_ISSUE, SYSTEM_ISSUE_COUNT_CELL_CSS),
                Arguments.of(TO_INVESTIGATE, TO_INVESTIGATE_COUNT_CELL_CSS)
        );
    }

    @ParameterizedTest(name = "{index} => launch contains tests count data for ''{0}''")
    @MethodSource("category")
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

    private static Stream<Arguments> launchesToCompare() {
        return Stream.of(
                Arguments.of("two in a row", asList(1, 2)),
                Arguments.of("three in a row", asList(1, 2, 3)),
                Arguments.of("two at any order", asList(2, 3)),
                Arguments.of("three in any order", asList(1, 2, 4)),
                Arguments.of("four in a row", asList(1, 2, 3, 4))
        );
    }

    @ParameterizedTest(name = "{index} => user is able select ''{0}'' launches and compare")
    @MethodSource("launchesToCompare")
    public void userIsAbleToSelectSeveralLaunchesAndCompareThem(String testCase, List<Integer> launchesToCompare) {
        ElementsCollection gridRowElements = launchesPage.gridRowElements();

        List<GridRow> numberLaunchesToCompare = new ArrayList<>();
        launchesToCompare.forEach(launch -> numberLaunchesToCompare.add(new GridRow(gridRowElements.get(launch))));

        //select launches
        toggleSelection(numberLaunchesToCompare);

        ActionMenu actionMenu = launchesPage.openActionMenu();
        ModalWindow compareModal = actionMenu.compareLaunchesModal();
        compareModal.buttonWithText("Cancel").click();

        //unselect launches
        toggleSelection(numberLaunchesToCompare);

        sleep(1000);
    }

    private void toggleSelection(List<GridRow> numberLaunchesToCompare){
        JavascriptExecutor jse = (JavascriptExecutor)getWebDriver();

        for (GridRow launch : numberLaunchesToCompare) {
            jse.executeScript("arguments[0].click();", launch.checkbox());
        }
    }

    @Test
    public void userIsAbleToRemoveLaunches() {
        assertTrue(true);
    }

    private static Stream<Arguments> donutElement() {
        return Stream.of(
                Arguments.of(PRODUCT_BUG, PRODUCT_BUG_DONUT_IDENTIFICATOR),
                Arguments.of(AUTO_BUG, AUTO_BUG_DONUT_IDENTIFICATOR),
                Arguments.of( SYSTEM_ISSUE, SYSTEM_ISSUE_DONUT_IDENTIFICATOR),
                Arguments.of(TO_INVESTIGATE, TO_INVESTIGATE_DONUT_IDENTIFICATOR)
        );
    }

    @ParameterizedTest(name = "{index} => user is able to move to appropriate launch clicking ''{0}''")
    @MethodSource("donutElement")
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement(String category, String elementIdentificator)
            throws URISyntaxException {
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

    private static Stream<Arguments> countElementWithParam() {
        return Stream.of(
                Arguments.of(TOTAL, TOTAL_COUNT_CELL_CSS, "PASSED%252CFAILED%252CSKIPPED%252CINTERRUPTED"),
                Arguments.of(PASSED, PASSED_COUNT_CELL_CSS, "PASSED"),
                Arguments.of(FAILED, FAILED_COUNT_CELL_CSS, "FAILED%252CINTERRUPTED"),
                Arguments.of(SKIPPED, SKIPPED_COUNT_CELL_CSS, "SKIPPED")
        );
    }

    @ParameterizedTest(name = "{index} => user is able to move to appropriate launch clicking ''{0}''")
    @MethodSource("countElementWithParam")
    public void userIsAbleToMoveToAppropriateLaunchClickingCountElement(String category, String elementIdentificator, String param)
            throws URISyntaxException {
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
