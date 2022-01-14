package dev.samkist.lumae.sagittarius.data.controllers;

import dev.samkist.lumae.sagittarius.storage.DataManager;

public abstract class AbstractLocalDataManager extends AbstractDataManager {

    private final AbstractServerManager parent;

    protected AbstractLocalDataManager(DataManager dataManager, AbstractServerManager parent) {
        super(dataManager);
        this.parent = parent;
    }

    protected AbstractServerManager getServerManager() {
        return this.parent;
    }
}
