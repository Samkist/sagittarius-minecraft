package dev.samkist.lumae.sagittarius.data.models.global;

import dev.samkist.lumae.sagittarius.data.models.gamemode.SimpleLocation;
import org.bukkit.Location;

public class ServerLocation extends SimpleLocation {

    private String server;

    public ServerLocation(String server, String world, Double x, Double y, Double z, Float yaw, Float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    public ServerLocation(String server, Location location) {
        super(location);
    }

    public ServerLocation() {
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
