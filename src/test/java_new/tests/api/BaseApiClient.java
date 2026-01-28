package tests.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected RequestSpecification requestSpec() {
        return given()
                .auth()
                .basic(
                        System.getProperty("api.user"),
                        System.getProperty("api.password")
                );
    }
}
