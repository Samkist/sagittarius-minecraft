package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.storage.DataProvider;

public abstract class AbstractDataManager {
    private final DataProvider dataProvider;


    protected AbstractDataManager(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    protected DataProvider getDataManager() {
        return dataProvider;
    }
}
