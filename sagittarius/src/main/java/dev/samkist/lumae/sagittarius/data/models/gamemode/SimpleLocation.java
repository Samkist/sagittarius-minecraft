package dev.samkist.lumae.sagittarius.data.models.gamemode;

import org.bukkit.Location;

public class SimpleLocation<T extends SimpleLocation> {
    private String world;
    private Double x, y, z;
    private Float yaw, pitch;

    public SimpleLocation(String world, Double x, Double y, Double z, Float yaw, Float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public SimpleLocation(Location location) {
        this(location.getWorld().getName(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public SimpleLocation() {

    }

    public String world() {
        return world;
    }

    public T world(String world) {
        this.world = world;
        return (T) this;
    }

    public Double x() {
        return x;
    }

    public T x(Double x) {
        this.x = x;
        return (T) this;
    }

    public Double y() {
        return y;
    }

    public T y(Double y) {
        this.y = y;
        return (T) this;
    }

    public Double z() {
        return z;
    }

    public T z(Double z) {
        this.z = z;
        return (T) this;
    }

    public Float yaw() {
        return yaw;
    }

    public T yaw(Float yaw) {
        this.yaw = yaw;
        return (T) this;
    }

    public Float pitch() {
        return pitch;
    }

    public T pitch(Float pitch) {
        this.pitch = pitch;
        return (T) this;
    }
}
