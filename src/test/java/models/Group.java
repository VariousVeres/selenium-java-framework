package models;

import java.util.Objects;

public class Group {
    private String id;
    private String name;

    public Group() {
        // For Jackson only
    }

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(!(obj instanceof Group)) return false;
        Group g = (Group) obj;
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
