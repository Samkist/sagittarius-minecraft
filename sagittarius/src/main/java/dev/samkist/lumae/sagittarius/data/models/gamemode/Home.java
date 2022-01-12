package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class Home extends MilkyModel {
    private SimpleLocation location;
    public static final String scope = "home";

    public Home(String id, SimpleLocation location) {
        super(id, scope);
        this.location = location;
    }

    public Home() {

    }

    public SimpleLocation getLocation() {
        return location;
    }

    public void setLocation(SimpleLocation location) {
        this.location = location;
    }
}
