package dev.samkist.lumae.sagittarius.data.models.gamemode;

import org.bukkit.Location;

public class SimpleLocation {
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

    public void world(String world) {
        this.world = world;
    }

    public Double x() {
        return x;
    }

    public void x(Double x) {
        this.x = x;
    }

    public Double y() {
        return y;
    }

    public void y(Double y) {
        this.y = y;
    }

    public Double z() {
        return z;
    }

    public void z(Double z) {
        this.z = z;
    }

    public Float yaw() {
        return yaw;
    }

    public void yaw(Float yaw) {
        this.yaw = yaw;
    }

    public Float pitch() {
        return pitch;
    }

    public void pitch(Float pitch) {
        this.pitch = pitch;
    }
}
