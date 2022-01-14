package dev.samkist.lumae.sagittarius.data.controllers;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.storage.DataManager;

import java.util.HashMap;

public abstract class AbstractDataManager {
    private final DataManager dataManager;


    protected AbstractDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    protected DataManager getDataManager() {
        return dataManager;
    }
}
