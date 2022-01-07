package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.Location;

public class Home extends MilkyModel {
    final Location location;
    public static final String scope = "homes";

    protected Home(String id, String scope, Location location) {
        super(id, scope);
        this.location = location;
    }
}
