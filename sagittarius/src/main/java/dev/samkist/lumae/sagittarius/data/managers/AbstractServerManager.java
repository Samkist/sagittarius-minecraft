package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.storage.DataProvider;

public abstract class AbstractServerManager extends AbstractDataManager {

    private final NetworkManager networkManager;
    private final GameMode gameMode;

    protected AbstractServerManager(DataProvider dataProvider, NetworkManager networkManager, GameMode gameMode) {
        super(dataProvider);
        this.networkManager = networkManager;
        this.gameMode = gameMode;
    }

    public HomesManager getHomesManager() {
        return new HomesManager(getDataManager(), this);
    }

    protected GameMode getGameMode() {
        return gameMode;
    }

    protected NetworkManager getNetworkManager() {
        return networkManager;
    }
}
