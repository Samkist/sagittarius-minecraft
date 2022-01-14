package dev.samkist.lumae.sagittarius.data.controllers;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.storage.DataManager;

public class SurvivalManager extends AbstractServerManager {


    protected SurvivalManager(DataManager dataManager, NetworkManager networkManager) {
        super(dataManager, networkManager, GameMode.SURVIVAL);
    }
}
