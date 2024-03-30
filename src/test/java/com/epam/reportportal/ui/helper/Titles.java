package com.epam.reportportal.ui.helper;

public enum Titles {
    NAME(1),
    START_TIME(2),
    TOTAL(3),
    PASSED(4),
    FAILED(5),
    SKIPPED(6),
    PRODUCT_BUG(7),
    AUTO_BUG(8),
    SYSTEM_ISSUE(9),
    TO_INVESTIGATE(10);

    private final int value;

    Titles(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
