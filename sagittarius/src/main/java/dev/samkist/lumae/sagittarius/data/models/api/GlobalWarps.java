package dev.samkist.lumae.sagittarius.data.models.api;

import dev.samkist.lumae.sagittarius.data.models.gamemode.Warp;

import java.util.Map;

public class GlobalWarps extends GlobalAdapter<String, Warp, GlobalWarps> {
    public static final String scope = "warps";

    public GlobalWarps(String uid, Map<String, Warp> modelMap) {
        super(uid, GlobalWarps.scope, modelMap);
    }
}
