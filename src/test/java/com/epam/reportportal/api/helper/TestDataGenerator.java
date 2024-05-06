package com.epam.reportportal.api.helper;

import com.epam.reportportal.api.dto.common.AttributeDto;
import com.epam.reportportal.api.dto.request.MergeLaunchesRqDto;
import com.epam.reportportal.api.dto.request.*;
import com.epam.reportportal.api.dto.common.LaunchDto;

import static com.epam.reportportal.api.helper.TestHelper.randomLaunchToGetParamsForFilter;
import static com.epam.reportportal.api.helper.TestHelper.randomLaunches;
import static com.epam.reportportal.utils.DateTimeUtils.*;
import static com.epam.reportportal.utils.DateTimeUtils.convertEpochMilliToDateTimeString;
import static com.epam.reportportal.utils.JsonUtils.writeValueAsString;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;
import static java.util.Arrays.asList;

public class TestDataGenerator {

    public static final String PROJECT = projectNameForCurrentEnv();

    private static AttributeDto attributes(){
        return AttributeDto.builder()
                .key("platform")
                .value("ios")
                .build();
    }
    public static String createMergeLaunchesRQ(){
        return writeValueAsString(MergeLaunchesRqDto.builder()
                .attributes(asList(attributes()))
                .description("test description")
                .endTime(currentDateFormatted())
                .extendSuitesDescription(true)
                .launches(randomLaunches())
                .mergeType("BASIC")
                .mode("DEFAULT")
                .name("Merge Launch")
                .startTime(startDateFormatted())
                .build());
    }

    public static String createIncorrectMergeLaunchesRQ(){
        return writeValueAsString(IncorrectMergeLaunchesRqDto.builder()
                .attributes(asList(attributes()))
                .description("test description")
                .endTime("")
                .extendSuitesDescription(true)
                .launches(asList("", ""))
                .mergeType("BASIC")
                .mode("DEFAULT")
                .name("Merge Launch")
                .startTime("")
                .build());
    }

    public static String createUpdateLaunchRQ() {
        return writeValueAsString(UpdateLaunchRqDto.builder()
                .description("test description")
                .mode("DEFAULT")
                .build());
    }

    public static FilterDto createFilters() {
        LaunchDto launchToGetParamsForFilter = randomLaunchToGetParamsForFilter();
        String startTime = convertEpochMilliToDateTimeString(launchToGetParamsForFilter.getStartTime());
        String endTime = convertEpochMilliToDateTimeString(launchToGetParamsForFilter.getEndTime());
        AttributeDto attribute = launchToGetParamsForFilter.getAttributes().get(0);
        return FilterDto.builder()
                 .attributeKey(attribute.getKey())
                 .attributeValue(attribute.getValue())
                 .endTime(endTime)
                 .hasRetries(launchToGetParamsForFilter.isHasRetries())
                 .id(launchToGetParamsForFilter.getId())
                 .name(launchToGetParamsForFilter.getName())
                 .number(launchToGetParamsForFilter.getNumber())
                 .startTime(startTime)
                 .status(launchToGetParamsForFilter.getStatus())
                 .user(launchToGetParamsForFilter.getOwner())
                 .uuid(launchToGetParamsForFilter.getUuid())
                 .build();
    }
}

