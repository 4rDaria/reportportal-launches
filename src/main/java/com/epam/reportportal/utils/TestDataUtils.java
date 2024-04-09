package com.epam.reportportal.utils;

import java.util.stream.Stream;

import static com.epam.reportportal.constants.Constants.*;
import static com.epam.reportportal.constants.Constants.TO_INVESTIGATE_COUNT_CELL_CSS;
import static java.util.Arrays.asList;

import org.junit.jupiter.params.provider.Arguments;

public class TestDataUtils {

    public static Stream<Arguments> category() {
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

    public static Stream<Arguments> launchesToCompare() {
        return Stream.of(
                Arguments.of("two in a row", asList(1, 2)),
                Arguments.of("three in a row", asList(1, 2, 3)),
                Arguments.of("two at any order", asList(2, 3)),
                Arguments.of("three in any order", asList(1, 2, 4)),
                Arguments.of("four in a row", asList(1, 2, 3, 4))
        );
    }

    public static Stream<Arguments> donutElement() {
        return Stream.of(
                Arguments.of(PRODUCT_BUG, PRODUCT_BUG_DONUT_IDENTIFICATOR),
                Arguments.of(AUTO_BUG, AUTO_BUG_DONUT_IDENTIFICATOR),
                Arguments.of( SYSTEM_ISSUE, SYSTEM_ISSUE_DONUT_IDENTIFICATOR),
                Arguments.of(TO_INVESTIGATE, TO_INVESTIGATE_DONUT_IDENTIFICATOR)
        );
    }

    public static Stream<Arguments> countElementWithParam() {
        return Stream.of(
                Arguments.of(TOTAL, TOTAL_COUNT_CELL_CSS, "PASSED%252CFAILED%252CSKIPPED%252CINTERRUPTED"),
                Arguments.of(PASSED, PASSED_COUNT_CELL_CSS, "PASSED"),
                Arguments.of(FAILED, FAILED_COUNT_CELL_CSS, "FAILED%252CINTERRUPTED"),
                Arguments.of(SKIPPED, SKIPPED_COUNT_CELL_CSS, "SKIPPED")
        );
    }
}
