package models.team;

import java.util.Objects;

public class TeamsGroup {
    private String id;
    private String name;

    public TeamsGroup() {
        // For Jackson only
    }

    public TeamsGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(!(obj instanceof TeamsGroup)) return false;
        TeamsGroup g = (TeamsGroup) obj;
        return Objects.equals(g.id, this.id) &&
                Objects.equals(g.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
