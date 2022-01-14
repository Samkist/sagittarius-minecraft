package dev.samkist.lumae.sagittarius.data.controllers;

import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.storage.DataManager;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class NetworkManager extends AbstractDataManager {

    private final SurvivalManager survivalManager;
    private final SagittariusApi parentApi;

    public NetworkManager(DataManager dataManager, SagittariusApi parentApi) {
        super(dataManager);
        this.survivalManager = new SurvivalManager(dataManager, this);
        this.parentApi = parentApi;
    }

    public AbstractServerManager getServerManager(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> getSurvivalManager();
            default -> null;
        };
    }

    public SurvivalManager getSurvivalManager() {
        return survivalManager;
    }

    public HomesManager homesManagerByGameMode(GameMode gameMode) {
        return Objects.nonNull(getServerManager(gameMode)) ? getServerManager(gameMode).getHomesManager() : null;
    }

    public PlayerManager getPlayerManager(MilkyPlayer milkyPlayer) {
        return new PlayerManager(getDataManager(), this, milkyPlayer);
    }

    public PlayerManager getPlayerManager(Server server, MilkyPlayer milkyPlayer) {
        Player player = server.getPlayer(UUID.fromString(milkyPlayer.getUid()));
        return Objects.nonNull(player) ?
                new PlayerManager(getDataManager(), this, player) :
                getPlayerManager(milkyPlayer);
    }

    public SagittariusApi api() {
        return this.parentApi;
    }
}
