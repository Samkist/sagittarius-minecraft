package dev.samkist.lumae.sagittarius.storage;

import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.exceptions.APIRuntimeException;
import kong.unirest.HttpResponse;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class DataManager {

    private final RESTManager restManager;


    public DataManager(RESTManager restManager) {
        this.restManager = restManager;
    }

    public MilkyPlayer getOrGenerateMilkyPlayer(Player player) {
        HttpResponse<MilkyPlayer> response = restManager.getMilkyPlayer(player.getUniqueId().toString());
        if(response.isSuccess()) {
            return response.getBody();
        } else {
            return switch (response.getStatus()) {
                case 404 -> saveMilkyPlayer(new MilkyPlayer(player));
                default -> throw new APIRuntimeException("Failed API response.");
            };
        }
    }

    public Optional<MilkyPlayer> getMilkyPlayerByUuid(String uuid) {
        return Optional.ofNullable(restManager.getMilkyPlayer(uuid).getBody());
    }

    public Optional<MilkyPlayer> getMilkyPlayerByUuid(UUID uuid) {
        return getMilkyPlayerByUuid(uuid.toString());
    }

    public MilkyPlayer saveMilkyPlayer(MilkyPlayer milkyPlayer) {
        HttpResponse<MilkyPlayer> response = restManager.saveMilkyPlayer(milkyPlayer);
        if(!response.isSuccess()) {
            throw new APIRuntimeException("Failed API response.");
        } else {
            return milkyPlayer;
        }
    }
}
