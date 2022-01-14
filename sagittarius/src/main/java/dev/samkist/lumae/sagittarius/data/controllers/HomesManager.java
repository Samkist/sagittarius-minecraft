package dev.samkist.lumae.sagittarius.data.controllers;

import dev.samkist.lumae.sagittarius.storage.DataManager;

public class HomesManager extends AbstractLocalDataManager {

    public HomesManager(DataManager dataManager, AbstractServerManager parent) {
        super(dataManager, parent);
    }
}
