package dev.samkist.lumae.sagittarius.data.controllers;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.storage.DataManager;
import org.bukkit.entity.Player;

public class PlayerManager extends AbstractGlobalDataManager {

    private Player player;
    private MilkyPlayer milkyPlayer;
    private TypeToken current;

    public PlayerManager(DataManager dataManager, NetworkManager networkManager, Player player) {//TODO take in either player object or milkyplayer object
        super(dataManager, networkManager);
        this.player = player;
        this.milkyPlayer = null;
        current = new TypeToken<Player>(){};
    }

    public PlayerManager(DataManager dataManager, NetworkManager networkManager, MilkyPlayer player) {
        super(dataManager, networkManager);
        this.player = null;
        this.milkyPlayer = player;
        current = new TypeToken<MilkyPlayer>(){};
    }





}
