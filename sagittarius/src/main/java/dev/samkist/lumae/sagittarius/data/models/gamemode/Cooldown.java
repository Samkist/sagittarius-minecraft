package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class Cooldown extends MilkyModel<Cooldown> {
    public static final String scope = "cooldown";

    private Long expiry;

    public Cooldown(String uid, String scope, Long expiry) {
        super(uid, scope);
        this.expiry = expiry;
    }
}

