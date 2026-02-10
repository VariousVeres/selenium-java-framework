package tests.api;

import io.restassured.response.Response;

public class TeamsClient extends BaseApiClient {

    private final String TEAMS = "https://api.eu-central-1.saucelabs.com/team-management/v1/teams";
    private final String TEAM_ID = "https://api.eu-central-1.saucelabs.com/team-management/v1/teams/{team_id}/";

    public Response getTeams() {
        return requestSpec()
                .when()
                .get(TEAMS)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getTeam(String teamId) {
        return requestSpec()
                .when()
                .pathParam("team_id", teamId)
                .get(TEAM_ID)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
