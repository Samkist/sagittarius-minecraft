package dev.samkist.lumae.sagittarius.data.managers;

import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.global.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
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

    public PlayerManager chatFormat(ChatFormat format) {
        getDataManager().setPlayerChatFormat(uuid, format);
        return this;
    }

    public PlayerManager chatFormat(String format) {
        getDataManager().setPlayerChatFormat(uuid, format);
        return this;
    }

    public JoinLeaveFormat joinLeaveFormat() {
        return getDataManager().getJLFormat(uuid);
    }

    public Double balance() {
        return getDataManager().getBalance(uuid);
    }

    public PlayerManager addBalance(Double add) {
        getDataManager().addBalance(uuid, add);
        return this;
    }

    public PlayerManager subtractBalance(Double subtract) {
        getDataManager().subtractBalance(uuid, subtract);
        return this;
    }

    public String nickname() {
        return getDataManager().getPlayerNickname(uuid);
    }

    public PlayerManager nickname(String nickname) {
        getDataManager().setPlayerNickname(uuid, nickname);
        return this;
    }
}
