package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.storage.DataProvider;

public class SurvivalServerManager extends AbstractServerManager {

    protected SurvivalServerManager(DataProvider dataProvider, NetworkManager networkManager) {
        super(dataProvider, networkManager, GameMode.SURVIVAL);
    }


}
