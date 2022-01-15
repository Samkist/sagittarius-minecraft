package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.storage.DataManager;

public abstract class AbstractDataManager {
    private final DataManager dataManager;


    protected AbstractDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    protected DataManager getDataManager() {
        return dataManager;
    }
}
