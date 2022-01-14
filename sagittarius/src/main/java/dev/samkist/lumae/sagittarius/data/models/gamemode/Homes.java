package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

import java.util.Map;

public class Homes extends MilkyModel<Homes> {
    private Map<String, SimpleLocation> locationMap;
    public static final String scope = "homes";

    public Homes() {

    }

    public Homes(String uid, Map<String, SimpleLocation> locationMap) {
        super(uid, scope);
        this.locationMap = locationMap;
    }

    public Map<String, SimpleLocation> locationMap() {
        return locationMap;
    }

    public Homes locationMap(Map<String, SimpleLocation> locationMap) {
        this.locationMap = locationMap;
        return this;
    }

    public Home homeById(String id) {
        return new Home(id, locationMap.get(id));
    }

    public Homes removeHome(String id) {
        locationMap.remove(id);
        return this;
    }

    public Homes saveHome(Home home) {
        locationMap.put(home.uid, home.location());
        return this;
    }
}
