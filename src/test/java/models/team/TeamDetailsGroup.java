package models.team;

import java.util.Objects;

/**
 * Extended group representation returned in GET /teams/{id}.
 * Extends {@link TeamsGroup} with capacity-related fields.
 * <p>
 * Additional fields:
 * - real_devices
 * - virtual_machines
 */

public class TeamDetailsGroup extends TeamsGroup {
    private int real_devices;
    private int virtual_machines;

    public int getReal_devices() {
        return real_devices;
    }

    public void setReal_devices(int real_devices) {
        this.real_devices = real_devices;
    }

    public int getVirtual_machines() {
        return virtual_machines;
    }

    public void setVirtual_machines(int virtual_machines) {
        this.virtual_machines = virtual_machines;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TeamDetailsGroup)) return false;
        TeamDetailsGroup g = (TeamDetailsGroup) obj;
        return Objects.equals(id, g.id)
                && Objects.equals(name, g.name)
                && virtual_machines == g.virtual_machines
                && real_devices == g.real_devices;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, virtual_machines, real_devices);
    }
}
