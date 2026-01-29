package models.team;

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
