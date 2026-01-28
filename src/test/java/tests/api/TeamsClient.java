package tests.api;

import io.restassured.response.Response;

public class TeamsClient extends BaseApiClient {

    private final String TEAMS_URL = "https://api.eu-central-1.saucelabs.com/team-management/v1/teams";

    public Response getTeams() {
        return requestSpec()
                .when()
                .get(TEAMS_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
