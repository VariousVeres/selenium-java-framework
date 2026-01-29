package tests.test_data;

import models.Group;
import models.Settings;
import models.Team;
import models.responses_wrappers.TeamsResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamsTestData {

    public static TeamsResponse defaultTeamResponse() {

        Team.TeamBuilder teamBuilder = Team.builder();
        Team team = teamBuilder.
                id("9ecf1eb535ca4c798eb834b3c101a57d")
                .settings(new Settings(false, 1, 1))
                .group(new Group("531a28500ca245748360f5e9ad51d131", "Default line of business"))
                .isDefault(true)
                .name("Default team")
                .orgUuid("b123f8cf07c84762b2cb73951ce06c4c")
                .userCount(1).build();

        Map<String,String> linksMap = new HashMap<>();
        linksMap.put("next", null);
        linksMap.put("previous", null);
        linksMap.put("first", "https://api.eu-central-1.saucelabs.com/team-management/v1/teams?limit=20&offset=0");
        linksMap.put("last", "https://api.eu-central-1.saucelabs.com/team-management/v1/teams?limit=20&offset=0");

        TeamsResponse response = new TeamsResponse();
        response.setCount(1);
        response.setResults(List.of(team));
        response.setLinks(linksMap);
        return response;
    }
}