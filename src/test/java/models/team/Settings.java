package models.team;

import java.util.Objects;

public class Settings {
    private boolean live_only;
    private int real_devices;
    private int virtual_machines;

    public Settings()  {
        //ForJackson only
    }

    public Settings(boolean liveOnly, int realDevices, int virtualMachines) {
        this.live_only=liveOnly;
        this.real_devices=realDevices;
        this.virtual_machines=virtualMachines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return live_only == settings.live_only && real_devices == settings.real_devices && virtual_machines == settings.virtual_machines;
    }

    @Override
    public int hashCode() {
        return Objects.hash(live_only, real_devices, virtual_machines);
    }

    public boolean isLive_only() {
        return live_only;
    }

    public void setLive_only(boolean live_only) {
        this.live_only = live_only;
    }

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