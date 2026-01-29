package models.team;

/**
 * Extended group representation returned in GET /teams/{id}.
 * Extends {@link TeamsGroup} with capacity-related fields.
 *
 * Additional fields:
 * - real_devices
 * - virtual_machines
 */

public class TeamDetailsGroup extends TeamsGroup{
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
}
