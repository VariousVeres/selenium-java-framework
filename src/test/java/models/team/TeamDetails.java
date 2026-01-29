package models.team;

/**
 * Detailed Team representation returned by GET /teams/{id}.
 *
 * Extends {@link BaseTeam} with additional fields available
 * only in the details endpoint.
 *
 * Overrides group getter/setter to use {@link TeamDetailsGroup},
 * which contains extended group information
 * (real_devices, virtual_machines).
 *
 * Jackson uses the overridden setter to deserialize the "group"
 * field into {@link TeamDetailsGroup}.
 */

public class TeamDetails extends BaseTeam {

    private String description;
    private String created_at;
    private String updated_at;

    @Override
    public TeamDetailsGroup getGroup() {
        return (TeamDetailsGroup) super.teamsGroup;
    }

    public void setGroup(TeamDetailsGroup group) {
        super.teamsGroup = group;
    }

    public TeamDetails()  {

    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
