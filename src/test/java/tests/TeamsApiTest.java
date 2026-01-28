package tests;

import org.testng.annotations.Test;
import tests.api.TeamsClient;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class TeamsApiTest {
    @Test
    public void shouldReturnCorrectTeams() {
        TeamsClient teamsClient = new TeamsClient();

        teamsClient.getTeams().then()
                .body("count", is(1))
                .body("results", hasSize(1))
                .body("results[0].group.name", is("Default line of business"))
                .body("results[0].group.id", notNullValue())
                .body("results[0].name", is("Default team"))
                .body("results[0].settings.real_devices", greaterThan(0))
                .body("results[0].settings.virtual_machines", greaterThan(0))
                .body("results[0].settings.live_only", is(false))
                .body("results[0].user_count", is(1));
    }
}
