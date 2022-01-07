package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.Location;

public class Home extends MilkyModel {
    private SimpleLocation location;
    public static final String scope = "homes";

    public Home(String id, Location location) {
        this(id, new SimpleLocation(location));
    }

    public Home(String id, SimpleLocation location) {
        super(id, scope);
        this.location = location;
    }

    public SimpleLocation getLocation() {
        return location;
    }

    public void setLocation(SimpleLocation location) {
        this.location = location;
    }
}
