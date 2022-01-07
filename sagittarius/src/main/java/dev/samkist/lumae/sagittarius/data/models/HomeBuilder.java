package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.Location;

public class HomeBuilder extends ModelBuilder<Home, HomeBuilder> {
    private Location location;

    public HomeBuilder(Location location) {
        this.location = location;
    }

    @Override
    public Home build() {
        return new Home(id(), "homes", location);
    }

    public HomeBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public Location location() {
        return location;
    }
}
