package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class Warp extends MilkyModel {
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

    public SimpleLocation getLocation() {
        return location;
    }

    public void setLocation(SimpleLocation location) {
        this.location = location;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
