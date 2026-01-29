package models.team;

/**
 * Base representation of a Team shared across multiple API read-models.
 *
 * Contains fields common for both:
 * - GET /teams (list)
 * - GET /teams/{id} (details)
 *
 * This class is not used directly as an API response,
 * but serves as a base for concrete DTOs.
 */

public abstract class BaseTeam {

    protected String id;
    protected Settings settings;
    protected TeamsGroup teamsGroup;
    protected boolean is_default;
    protected String name;
    protected String org_uuid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public TeamsGroup getGroup() {
        return teamsGroup;
    }

    public void setGroup(TeamsGroup teamsGroup) {
        this.teamsGroup = teamsGroup;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg_uuid() {
        return org_uuid;
    }

    public void setOrg_uuid(String org_uuid) {
        this.org_uuid = org_uuid;
    }
}
