package com.epam.reportportal.utils;

import org.testng.annotations.DataProvider;

import static com.epam.reportportal.constants.Constants.*;
import static java.util.Arrays.asList;

public class TestDataUtils {

    @DataProvider(name = "category")
    public static Object[][] testDataCategory() {
        return new Object[][]
                {
                        {TOTAL, TOTAL_COUNT_CELL_CSS},
                        {PASSED, PASSED_COUNT_CELL_CSS},
                        {FAILED, FAILED_COUNT_CELL_CSS},
                        {SKIPPED, SKIPPED_COUNT_CELL_CSS},
                        {PRODUCT_BUG, PRODUCT_BUG_COUNT_CELL_CSS},
                        {AUTO_BUG, AUTO_BUG_COUNT_CELL_CSS},
                        {SYSTEM_ISSUE, SYSTEM_ISSUE_COUNT_CELL_CSS},
                        {TO_INVESTIGATE, TO_INVESTIGATE_COUNT_CELL_CSS}
                };
    }

    @DataProvider(name = "launchesToCompare")
    public Object[][] testDataLaunchesToCompare()
    {
        return new Object[][]
                {
                        {"two in a row", asList(1,2)},
                        {"three in a row", asList(1, 2, 3)},
                        {"two at any order", asList(2, 3)},
                        {"three in any order", asList(1, 2, 4)},
                        {"four in a row", asList(1, 2, 3, 4)}
                };
    }

    @DataProvider(name = "donutElement")
    public Object[][] testDataDonutElement()
    {
        return new Object[][]
                {
                        {PRODUCT_BUG, PRODUCT_BUG_DONUT_IDENTIFICATOR},
                        {AUTO_BUG, AUTO_BUG_DONUT_IDENTIFICATOR},
                        {SYSTEM_ISSUE, SYSTEM_ISSUE_DONUT_IDENTIFICATOR},
                        {TO_INVESTIGATE, TO_INVESTIGATE_DONUT_IDENTIFICATOR}
                };
    }

    @DataProvider(name = "countElementWithParam")
    public Object[][] testDataCountElementWithParam()
    {
        return new Object[][]
                {
                        {TOTAL, TOTAL_COUNT_CELL_CSS, "PASSED%252CFAILED%252CSKIPPED%252CINTERRUPTED"},
                        {PASSED, PASSED_COUNT_CELL_CSS, "PASSED"},
                        {FAILED, FAILED_COUNT_CELL_CSS, "FAILED%252CINTERRUPTED"},
                        {SKIPPED, SKIPPED_COUNT_CELL_CSS, "SKIPPED"}
                };
    }
}
