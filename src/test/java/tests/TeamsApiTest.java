package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.team.TeamDetails;
import models.team.TeamsResponse;
import org.testng.annotations.Test;
import tests.api.TeamsClient;
import tests.test_data.TeamsTestData;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TeamsApiTest {
    @Test(priority = 1)
    public void shouldReturnCorrectTeams() {
        TeamsClient teamsClient = new TeamsClient();

        Response teamsResponse = teamsClient.getTeams()
                .then()
                .body("results[0].id", notNullValue(String.class))
                .body("count", is(1))
                .body("results", hasSize(1))
                .body("results[0].group.name", is("Default line of business"))
                .body("results[0].group.id", notNullValue())
                .body("results[0].name", is("Default team"))
                .body("results[0].settings.real_devices", greaterThan(0))
                .body("results[0].settings.virtual_machines", greaterThan(0))
                .body("results[0].settings.live_only", is(false))
                .body("results[0].user_count", is(1))
                .extract().response();
        System.out.println("TEAMS: " + teamsResponse.prettyPrint());
        TeamsResponse teamsR = teamsResponse.as(TeamsResponse.class);
        TeamsResponse expected = TeamsTestData.defaultTeamResponse();
        assertThat("Response is invalid", teamsR, is(expected));

        String teamResponseString = teamsResponse.asString();
        Gson gson = new Gson();
        TeamsResponse expectedFromJSON = gson.fromJson(teamResponseString, TeamsResponse.class);

        assertThat("Response is invalid", teamsResponse, is(expectedFromJSON));

    }

    @Test(priority = 2)
    public void shouldReturnCorrectTeamById() {
        TeamsClient teamsClient = new TeamsClient();
        TeamsResponse tr = teamsClient.getTeams().then().extract().as(TeamsResponse.class);
        String teamId = tr.getResults().get(0).getId();


        Response res = teamsClient.getTeam(teamId)
                .then()
                .statusCode(200)
                .extract().response();

        TeamDetails t = res.as(TeamDetails.class);
    }
}
