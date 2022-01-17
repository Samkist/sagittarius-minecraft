package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.storage.DataProvider;

public abstract class AbstractGlobalDataManager extends AbstractDataManager {
    private final NetworkManager parent;

    protected AbstractGlobalDataManager(DataProvider dataProvider, NetworkManager parent) {
        super(dataProvider);
        this.parent = parent;
    }

    protected NetworkManager getNetwork() {
        return parent;
    }
}
