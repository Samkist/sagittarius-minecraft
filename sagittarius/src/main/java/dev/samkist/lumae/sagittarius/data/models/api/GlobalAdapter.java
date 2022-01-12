package dev.samkist.lumae.sagittarius.data.models.api;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

import java.util.Map;

public abstract class GlobalAdapter<T extends MilkyModel> extends MilkyModel {
    private Map<GameMode, T> modelMap;

    public GlobalAdapter() {
        super();
    }

    public GlobalAdapter(String uid, String scope, Map<GameMode, T> modelMap) {
        super(uid, scope);
        this.modelMap = modelMap;
    }

    public T getModelByGamemode(GameMode gameMode) {
        return modelMap.getOrDefault(gameMode, null);
    }

    public Map<GameMode, T> getModelMap() {
        return modelMap;
    }

    public void setModelMap(Map<GameMode, T> modelMap) {
        this.modelMap = modelMap;
    }

    public void saveModel(GameMode gameMode, T model) {
        modelMap.put(gameMode, model);
    }

    public void removeModel(GameMode gameMode) {
        modelMap.remove(gameMode);
    }

    public boolean hasModel(GameMode gameMode) {
        return modelMap.containsKey(gameMode);
    }
}
