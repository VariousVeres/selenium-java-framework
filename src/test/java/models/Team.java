package models;

import java.util.Objects;

public class Team {

    private String id;
    private Settings settings;
    private Group group;
    private boolean is_default;
    private String name;
    private String org_uuid;
    private int user_count;

    public Team() {
        // For Jackson only
    }

    private Team(TeamBuilder builder) {
        this.id = builder.id;
        this.settings = builder.settings;
        this.group = builder.group;
        this.is_default = builder.isDefault;
        this.name = builder.name;
        this.org_uuid = builder.orgUuid;
        this.user_count = builder.userCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return is_default == team.is_default &&
                user_count == team.user_count &&
                Objects.equals(id, team.id) &&
                Objects.equals(settings, team.settings) &&
                Objects.equals(group, team.group) &&
                Objects.equals(name, team.name) &&
                Objects.equals(org_uuid, team.org_uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, settings, group, is_default, name, org_uuid, user_count);
    }

    public String getId() {
        return id;
    }

    public Settings getSettings() {
        return settings;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public String getName() {
        return name;
    }

    public String getOrg_uuid() {
        return org_uuid;
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
        private Group group;
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

        public TeamBuilder group(Group group) {
            this.group = group;
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

        public Team build() {
            return new Team(this);
        }
    }
}

