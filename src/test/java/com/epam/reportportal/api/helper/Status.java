package com.epam.reportportal.api.helper;

public enum Status {
        PASSED("passed"),
        FAILED("failed"),
        SKIPPED("skipped");

        private final String value;

        Status(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }

        public static Status fromString(String text) {
                for (Status status : Status.values()) {
                        if (status.value.equalsIgnoreCase(text)) {
                                return status;
                        }
                }
                throw new IllegalArgumentException("No constant with text " + text + " found");
        }
}
