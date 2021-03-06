package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.storage.DataProvider;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class NetworkManager extends AbstractDataManager {

    private final SurvivalServerManager survivalManager;
    private final SagittariusApi parentApi;

    public NetworkManager(DataProvider dataProvider, SagittariusApi parentApi) {
        super(dataProvider);
        this.survivalManager = new SurvivalServerManager(dataProvider, this);
        this.parentApi = parentApi;
    }

    public AbstractServerManager getServerManager(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> getSurvivalManager();
            default -> null;
        };
    }

    public SurvivalServerManager getSurvivalManager() {
        return survivalManager;
    }

    public HomesManager homesManagerByGameMode(GameMode gameMode) {
        return Objects.nonNull(getServerManager(gameMode)) ? getServerManager(gameMode).getHomesManager() : null;
    }

    public PlayerManager getPlayerManager(MilkyPlayer milkyPlayer) {
        return new PlayerManager(getDataManager(), this, milkyPlayer);
    }

    public PlayerManager getPlayerManager(Server server, MilkyPlayer milkyPlayer) {
        Player player = server.getPlayer(UUID.fromString(milkyPlayer.uid()));
        return Objects.nonNull(player) ?
                new PlayerManager(getDataManager(), this, player) :
                getPlayerManager(milkyPlayer);
    }

    public SagittariusApi api() {
        return this.parentApi;
    }
}
