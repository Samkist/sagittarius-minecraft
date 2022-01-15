package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.storage.DataManager;

public class SurvivalServerManager extends AbstractServerManager {

    protected SurvivalServerManager(DataManager dataManager, NetworkManager networkManager) {
        super(dataManager, networkManager, GameMode.SURVIVAL);
    }
}
