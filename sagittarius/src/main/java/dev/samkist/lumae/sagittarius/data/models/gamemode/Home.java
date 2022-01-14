package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class Home extends MilkyModel<Home> {
    private SimpleLocation location;
    public static final String scope = "home";

    public Home(String id, SimpleLocation location) {
        super(id, scope);
        this.location = location;
    }

    public Home() {

    }

    public SimpleLocation location() {
        return location;
    }

    public Home location(SimpleLocation location) {
        this.location = location;
        return this;
    }
}
