package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.storage.DataProvider;

public class HomesManager extends AbstractLocalDataManager {

    public HomesManager(DataProvider dataProvider, AbstractServerManager parent) {
        super(dataProvider, parent);
    }
}
