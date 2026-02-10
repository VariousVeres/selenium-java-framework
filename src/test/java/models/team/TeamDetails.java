package models.team;

import java.util.Objects;

/**
 * Detailed Team representation returned by GET /teams/{id}.
 * <p>
 * Extends {@link BaseTeam} with additional fields available
 * only in the details endpoint.
 * <p>
 * Overrides group getter/setter to use {@link TeamDetailsGroup},
 * which contains extended group information
 * (real_devices, virtual_machines).
 * <p>
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

    public TeamDetails() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDetails that = (TeamDetails) o;

        return is_default == that.is_default
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(org_uuid, that.org_uuid)
                && Objects.equals(settings, that.settings)
                && Objects.equals(description, that.description)
                && Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(is_default, description, id, name, org_uuid, settings, teamsGroup);
    }
}
