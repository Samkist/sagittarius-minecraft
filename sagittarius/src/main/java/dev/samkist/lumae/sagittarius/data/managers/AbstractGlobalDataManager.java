package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.storage.DataManager;

public abstract class AbstractGlobalDataManager extends AbstractDataManager {
    private final NetworkManager parent;

    protected AbstractGlobalDataManager(DataManager dataManager, NetworkManager parent) {
        super(dataManager);
        this.parent = parent;
    }

    protected NetworkManager getNetwork() {
        return parent;
    }
}
