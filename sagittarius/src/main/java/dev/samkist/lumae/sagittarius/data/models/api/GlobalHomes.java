package dev.samkist.lumae.sagittarius.data.models.api;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Homes;

import java.util.Map;

public class GlobalHomes extends GlobalAdapter<Homes> {
    public static final String scope = "homes";

    public GlobalHomes() {
    }

    public GlobalHomes(String uid, Map<GameMode, Homes> modelMap) {
        super(uid, GlobalHomes.scope, modelMap);
    }
}
