package dev.samkist.lumae.sagittarius.data.controllers;

import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.exceptions.TodoException;
import dev.samkist.lumae.sagittarius.storage.DataManager;
import org.bukkit.entity.Player;

public class PlayerManager extends AbstractGlobalDataManager {

    private final Player player;
    private final String uuid;

    public PlayerManager(DataManager dataManager, NetworkManager networkManager, Player player) {//TODO take in either player object or milkyplayer object
        super(dataManager, networkManager);
        this.player = player;
        this.uuid = player.getUniqueId().toString();
    }

    public PlayerManager(DataManager dataManager, NetworkManager networkManager, MilkyPlayer player) {
        super(dataManager, networkManager);
        this.player = null;
        this.uuid = player.uid();
    }

    public ChatFormat chatFormat() {
        return getDataManager().getPlayerChatFormat(uuid);
    }

    public void chatFormat(ChatFormat format) {
        getDataManager().setPlayerChatFormat(uuid, format);
    }

    public void chatFormat(String format) {
        getDataManager().setPlayerChatFormat(uuid, format);
    }

    public Double balance() {
        return 0.0;
    }

    public void balance(Double balance) {
        throw new TodoException();
    }

    public void addBalance(Double add) {
        throw new TodoException();
    }

    public void subtractBalance(Double subtract) {
        throw new TodoException();
    }

    public String nickname() {
        throw new TodoException();
    }

    public void nickname(String nickname) {
        throw new TodoException();
    }
}
