package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.Location;

import java.util.Map;
import java.util.stream.Collectors;

public class Homes extends MilkyModel {
    private Map<String, SimpleLocation> locationMap;
    public static final String scope = "homes";

    public Homes() {

    }

    public Homes(String uid, Map<String, SimpleLocation> locationMap) {
        super(uid, scope);
        this.locationMap = locationMap;
    }

    public Map<String, SimpleLocation> getLocationMap() {
        return locationMap;
    }

    public void setLocationMap(Map<String, SimpleLocation> locationMap) {
        this.locationMap = locationMap;
    }

    public Home getHomeById(String id) {
        return new Home(id, locationMap.get(id));
    }

    public void removeHome(String id) {
        locationMap.remove(id);
    }

    public void addHome(Home home) {
        locationMap.put(home.uid, home.getLocation());
    }
}
