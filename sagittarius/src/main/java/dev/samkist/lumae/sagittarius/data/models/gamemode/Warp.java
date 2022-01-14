package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class Warp extends MilkyModel<Warp> {
    private SimpleLocation location;
    private String ownerUuid;
    private String permission;
    private Boolean enabled;

    public static final String scope = "warps";

    public Warp() {

    }

    public Warp(String uid, String scope, SimpleLocation location, String ownerUuid, String permission, Boolean enabled) {
        super(uid, Warp.scope);
        this.location = location;
        this.ownerUuid = ownerUuid;
        this.permission = permission;
        this.enabled = enabled;
    }

    public SimpleLocation location() {
        return location;
    }

    public Warp location(SimpleLocation location) {
        this.location = location;
        return this;
    }

    public String ownerUuid() {
        return ownerUuid;
    }

    public Warp ownerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
        return this;
    }

    public String permission() {
        return permission;
    }

    public Warp permission(String permission) {
        this.permission = permission;
        return this;
    }

    public Boolean enabled() {
        return enabled;
    }

    public Warp enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
