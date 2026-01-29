package models.team;

import java.util.Objects;

/**
 * Team representation returned by GET /teams (list endpoint).
 * <p>
 * Extends {@link BaseTeam} with list-specific fields.
 * <p>
 * Contains {@code user_count}, which represents the number of users
 * assigned to the team and is available only in the list response.
 * <p>
 * Used as an item inside {@link TeamsResponse}.
 */

public class TeamListItem extends BaseTeam {

    private int user_count;

    public TeamListItem() {
        // For Jackson only
    }

    private TeamListItem(TeamBuilder builder) {
        this.id = builder.id;
        this.settings = builder.settings;
        this.teamsGroup = builder.teamsGroup;
        this.is_default = builder.isDefault;
        this.name = builder.name;
        this.org_uuid = builder.orgUuid;
        this.user_count = builder.userCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamListItem)) return false;

        TeamListItem that = (TeamListItem) o;

        return is_default == that.is_default
                && user_count == that.user_count
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(org_uuid, that.org_uuid)
                && Objects.equals(settings, that.settings)
                && Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                user_count,
                id,
                name,
                org_uuid,
                settings,
                teamsGroup,
                is_default
        );
    }


    public int getUser_count() {
        return user_count;
    }

    public static TeamBuilder builder() {
        return new TeamBuilder();
    }


    public static class TeamBuilder {

        private String id;
        private Settings settings;
        private TeamsGroup teamsGroup;
        private boolean isDefault;
        private String name;
        private String orgUuid;
        private int userCount;

        public TeamBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TeamBuilder settings(Settings settings) {
            this.settings = settings;
            return this;
        }

        public TeamBuilder group(TeamsGroup teamsGroup) {
            this.teamsGroup = teamsGroup;
            return this;
        }

        public TeamBuilder isDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public TeamBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TeamBuilder orgUuid(String orgUuid) {
            this.orgUuid = orgUuid;
            return this;
        }

        public TeamBuilder userCount(int userCount) {
            this.userCount = userCount;
            return this;
        }

        public TeamListItem build() {
            return new TeamListItem(this);
        }
    }
}

