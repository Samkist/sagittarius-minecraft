package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.storage.DataProvider;

public abstract class AbstractLocalDataManager extends AbstractDataManager {

    private final AbstractServerManager parent;

    protected AbstractLocalDataManager(DataProvider dataProvider, AbstractServerManager parent) {
        super(dataProvider);
        this.parent = parent;
    }

    protected AbstractServerManager getServerManager() {
        return this.parent;
    }
}
