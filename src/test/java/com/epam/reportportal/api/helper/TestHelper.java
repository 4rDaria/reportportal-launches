package com.epam.reportportal.api.helper;

import com.epam.reportportal.api.dto.common.LaunchDto;
import com.epam.reportportal.api.dto.request.FilterDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.epam.reportportal.api.RequestBuilder.requestSpecificationWithAuth;
import static com.epam.reportportal.api.helper.Routes.get_all_launches_url;
import static com.epam.reportportal.api.helper.TestDataGenerator.PROJECT;
import static com.epam.reportportal.api.helper.TestDataGenerator.createFilters;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class TestHelper {

    public static List<LaunchDto> getAllLaunches() {
        Response response = requestSpecificationWithAuth()
                .get(format(get_all_launches_url, PROJECT));

        JsonPath jsonPath = response.jsonPath();
        List<LaunchDto> launches = jsonPath.getList("content", LaunchDto.class);

        return launches;
    }

    private static List<Integer> launchesIdsList() {
        return getAllLaunches().stream()
                .map(LaunchDto::getId)
                .collect(toList());
    }

    public static Integer randomLaunchId() {
        List<Integer> launchesIdsList = launchesIdsList();
        Random random = new Random();
        int randomIndex = random.nextInt(launchesIdsList.size());
        return launchesIdsList.get(randomIndex);
    }

    public static List<Integer> randomLaunches() {
        Random random = new Random();

        int randomNumber1 = random.nextInt(launchesIdsList().size());
        int randomNumber2;
        do {
            randomNumber2 = random.nextInt(launchesIdsList().size());
        } while (randomNumber2 == randomNumber1);
        return asList(launchesIdsList().get(randomNumber1), launchesIdsList().get(randomNumber2));
    }

    public static LaunchDto randomLaunchToGetParamsForFilter() {
        List<LaunchDto> allLaunches = getAllLaunches();
        Random random = new Random();
        int randomIndex = random.nextInt(allLaunches.size());
        return allLaunches.get(randomIndex);
    }

    public static Stream<Arguments> launchFiltersProvider() {
        FilterDto filters = createFilters();
        return Stream.of(
                Arguments.of("has.attributeKey", filters.getAttributeKey()),
                Arguments.of("has.attributeValue",  filters.getAttributeValue()),
                Arguments.of("eq.endTime", filters.getEndTime()),
                Arguments.of("eq.hasRetries", String.valueOf(filters.isHasRetries())),
                Arguments.of("eq.id", String.valueOf(filters.getId())),
                Arguments.of("eq.name", filters.getName()),
                Arguments.of("eq.number", String.valueOf(filters.getNumber())),
                Arguments.of("eq.startTime", filters.getStartTime()),
                Arguments.of("eq.status", filters.getStatus()),
                Arguments.of("eq.user", filters.getUser()),
                Arguments.of("eq.uuid", filters.getUuid())
        );
    }
}
