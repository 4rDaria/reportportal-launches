package com.epam.reportportal.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class DateTimeUtils {

    private DateTimeUtils() {

    }

    public static String convertEpochMilliToDateTimeString(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);

        return LocalDateTime.ofInstant(instant, UTC).toString();
    }

    public static long convertDateTimeStringToEpochMilli(String dateTimeString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);

        return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static String startDateFormatted() {
        LocalDateTime currentTime = LocalDateTime.now().minusHours(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return currentTime.format(formatter);
    }

    public static String currentDateFormatted() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return currentTime.format(formatter);
    }

    public static List<String> descendingSortDateTimeStringFormat(List<String> dateTimeListStringFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimeListStringFormat.sort(Comparator.comparing((String s) -> LocalDateTime.parse(s, formatter)).reversed());

        return dateTimeListStringFormat;
    }
}
