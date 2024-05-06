package com.epam.reportportal.api;

import com.epam.reportportal.api.dto.common.LaunchDto;
import com.epam.reportportal.api.dto.response.LaunchesDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static com.epam.reportportal.api.RequestBuilder.*;
import static com.epam.reportportal.api.helper.Routes.*;
import static com.epam.reportportal.api.helper.TestDataGenerator.*;
import static com.epam.reportportal.api.helper.TestHelper.*;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.setProxyProperties;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.*;

public class LaunchesApiTest {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final int INCORRECT_LAUNCH_ID = 12;

    @BeforeAll
    public static void setUp() {
        setProxyProperties();

        var response = post(demo_data_generation_url, "{\"createDashboard\": true}");

        assertEquals(OK.value(), response.statusCode());
        LOGGER.info("Demo data generated successfully");
    }

    @Test
    public void whenRemoveLaunchByValidId_thenReturn200AndValidMessage() {
        int launchId = randomLaunchId();

        var responseMessage = deleteWithOkStatusCodeAndResponseMessage(format(delete_launches_url, launchId));
        assertNotNull(responseMessage);

        String expectedResponseMessage = format("Launch with ID = '%s' successfully deleted.", launchId);
        assertEquals(expectedResponseMessage, responseMessage);

        LOGGER.info("Launch with id=" + launchId + " was successfully deleted");
    }

    @Test
    public void whenRemoveLaunchByInvalidId_thenReturn404AndValidMessage() {
        var responseMessage = deleteWithNotFoundStatusCodeAndResponseMessage(format(delete_launches_url, INCORRECT_LAUNCH_ID));
        assertNotNull(responseMessage);

        String expectedResponseMessage = format("Launch '%s' not found. Did you use correct Launch ID?", INCORRECT_LAUNCH_ID);
        assertEquals(expectedResponseMessage, responseMessage);

        LOGGER.info("Launch with id=" + INCORRECT_LAUNCH_ID + " not found");
    }

    @ParameterizedTest(name = "{index} => user is able o get all launches via GET request by filter''{0}''")
    @MethodSource("com.epam.reportportal.api.helper.TestHelper#launchFiltersProvider")
    public void whenGetAllLaunchesByAuthorizedUserAndValidFilter_thenReturn200AndValidResponse(String paramName, String paramValue) {
        var response = getWithOkStatusCodeAndResponse(urlWithFilter(paramName, paramValue), LaunchesDto.class);
        assertThat(response.getContent().size()).isGreaterThanOrEqualTo(1);

        LOGGER.info("All launches by filter " + paramName + " are received successfully");

    }

    @ParameterizedTest(name = "{index} => user is able o get all launches via GET request by filter''{0}''")
    @MethodSource("com.epam.reportportal.api.helper.TestHelper#launchFiltersProvider")
    public void whenGetAllLaunchesByAuthorizedUserAndValidFilter_thenReturn401(String paramName, String paramValue) {
        var response = getForUnauthorized(urlWithFilter(paramName, paramValue));
        assertEquals(UNAUTHORIZED.value(), response.statusCode());

        LOGGER.info("All launches by filter " + paramName + " are not received. User unauthorized");

    }

    private String urlWithFilter(String paramName, String paramValue) {
        String filter = "?filter." + format("%s=%s", paramName, paramValue);
        return get_all_launches_url + filter;
    }

    @Test
    public void whenCompareLaunchesWithValidParamsAndEndpointPaths_thenReturn200AndValidResponse() {
        List<Integer> launchIds = randomLaunches();
        Integer firstLaunchToCompareId = launchIds.get(0);
        Integer secondLaunchToCompareId = launchIds.get(1);

        var response = getWithQueryParameters(launches_compare_url, firstLaunchToCompareId, secondLaunchToCompareId);

        assertEquals(OK.value(), response.statusCode());

        LOGGER.info("Launches with ids " + firstLaunchToCompareId + " and " +   secondLaunchToCompareId +
                " sre successfully compared");
        //we can't validate response with DTO, because we don't have the same count of fields in value
    }

    @Test
    public void whenCompareLaunchesWithInvalidEndpointPaths_thenReturn404() {
        List<Integer> launchIds = randomLaunches();
        Integer firstLaunchToCompareId = launchIds.get(0);
        Integer secondLaunchToCompareId = launchIds.get(1);

        var response = getWithQueryParameters(launches_compare_incorrect_url, firstLaunchToCompareId, secondLaunchToCompareId);

        assertEquals(NOT_FOUND.value(), response.statusCode());

        LOGGER.info("Launches with ids " + firstLaunchToCompareId + " and " +   secondLaunchToCompareId +
                " can't be compared. Invalid endpoint paths");
    }

    @Test
    public void whenMergeLaunchesWithValidFieldValues_thenReturn200AndValidResponse() {
        var response = postWithOkStatusCodeAndResponse(merge_launches_url, createMergeLaunchesRQ(), LaunchDto.class);

        assertThat(response.getDescription()).isEqualTo("test description");
        assertThat(response.getName()).isEqualTo("Merge Launch");

        LOGGER.info("Launches successfully merged");
    }

    @Test
    public void whenMergeLaunchesWithInvalidFieldValues_thenReturn400() {
        var response = post(merge_launches_url, createIncorrectMergeLaunchesRQ());

        assertEquals(BAD_REQUEST.value(), response.statusCode());
        LOGGER.info("Launches can't be merged. Bad request");
    }

    @Test
    public void whenMergeLaunchesWithInvalidHttpMethods_thenReturn405() {
        var response = put(merge_launches_url, createMergeLaunchesRQ());

        assertEquals(METHOD_NOT_ALLOWED.value(), response.statusCode());
        LOGGER.info("Launches can't be merged. Method not allowed");
    }

    @Test
    public void whenUpdateLaunchByValidLaunchId_thenReturn200AndValidMessage() {
        int launchId = randomLaunchId();
        var responseMessage = putWithOkStatusCodeAndResponseMessage(format(update_launch_url, launchId),createUpdateLaunchRQ());

        String expectedMessage = format("Launch with ID = '%s' successfully updated.", launchId);
        assertEquals(expectedMessage, responseMessage);
        LOGGER.info("Launches successfully updated");
    }

    @Test
    public void whenUpdateLaunchByInvalidLaunchId_thenReturn404AndValidMessage() {
        var response = put(format(update_launch_url, INCORRECT_LAUNCH_ID), createUpdateLaunchRQ());

        assertEquals(NOT_FOUND.value(), response.statusCode());
        LOGGER.info("Launches can't be updated. Launch id = " + INCORRECT_LAUNCH_ID + " not allowed");
    }

    @Test
    public void whenUpdateLaunchWithInvalidHttpMethods_thenReturn405() {
        var response = post(format(update_launch_url, randomLaunchId()), createUpdateLaunchRQ());

        assertEquals(METHOD_NOT_ALLOWED.value(), response.statusCode());
        LOGGER.info("Launches can't be updated. Method not allowed");
    }

}
