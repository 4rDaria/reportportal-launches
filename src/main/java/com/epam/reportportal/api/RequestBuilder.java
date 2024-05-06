package com.epam.reportportal.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.barearToken;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.*;

public class RequestBuilder {

    public static RequestSpecification requestSpecificationWithAuth() {
        return given()
                .accept("*/*")
                .header("Authorization", barearToken());
    }

    public static RequestSpecification requestSpecificationWithAuthContentTypeJsonAndBody(String body) {
        RequestSpecification requestSpecification = requestSpecificationWithAuth()
                .contentType(ContentType.JSON)
                .body(body);
        return requestSpecification;
    }

    public static RequestSpecification incorrectRequestSpecification() {
        String incorrectToken = "Bearer yJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTM0NjY2OTksInVzZXJfbmFtZSI6ImRhcmlhIiwiYXV0a";
        return given()
                .accept("*/*")
                .header("Authorization", incorrectToken);
    }

    public static <T> T getWithOkStatusCodeAndResponse(String url, Class<T> clazz) {
        return requestSpecificationWithAuth()
                .get(url)
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .as(clazz);
    }

    public static Object getWithNotFoundStatusCodeAndResponseMessage(String url) {
        return requestSpecificationWithAuth()
                .get(url)
                .then()
                .assertThat()
                .statusCode(NOT_FOUND.value())
                .extract()
                .jsonPath()
                .get("message");
    }

    public static Response getWithUnauthorized(String url) {
        return incorrectRequestSpecification()
                .get(url);
    }

    public static Response getWithQueryParameters(String url, Integer firstLaunchToCompareId, Integer secondLaunchToCompareId) {
        return requestSpecificationWithAuth()
                .queryParam("ids", firstLaunchToCompareId)
                .queryParam("ids", secondLaunchToCompareId)
                .get(url);
    }

    public static Response post(String url) {
        return requestSpecificationWithAuth()
                .post(url);
    }

    public static Response postWithBody(String url, String body) {
        return requestSpecificationWithAuthContentTypeJsonAndBody(body)
                .post(url);
    }

    public static <T> T postWithOkStatusCodeAndResponse(String url, String body, Class<T> clazz) {
        return requestSpecificationWithAuthContentTypeJsonAndBody(body)
                .post(url)
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .as(clazz);
    }
    public static Response putWithBody(String url, String body) {
        return requestSpecificationWithAuthContentTypeJsonAndBody(body)
                .put(url);
    }

    public static Object putWithOkStatusCodeAndResponseMessage(String url, String body) {
        return requestSpecificationWithAuthContentTypeJsonAndBody(body)
                .put(url)
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .jsonPath()
                .get("message");
    }

    public static Object deleteWithOkStatusCodeAndResponseMessage(String url) {
        return requestSpecificationWithAuth()
                .delete(url)
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .jsonPath()
                .get("message");
    }

    public static Object deleteWithNotFoundStatusCodeAndResponseMessage(String url) {
        return requestSpecificationWithAuth()
                .delete(url)
                .then()
                .assertThat()
                .statusCode(NOT_FOUND.value())
                .extract()
                .jsonPath()
                .get("message");
    }

}
