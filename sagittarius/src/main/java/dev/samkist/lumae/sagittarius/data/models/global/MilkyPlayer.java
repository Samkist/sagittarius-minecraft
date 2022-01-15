package dev.samkist.lumae.sagittarius.data.models.global;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import org.bukkit.entity.Player;

public class MilkyPlayer extends MilkyModel<MilkyPlayer> {
    private String lastUsername;
    private String nickname;
    private String chatFormat;
    private String joinLeaveFormat;
    private Double balance;
    private Integer votes;
    private Integer secondsPlayed;
    private Long joinDate;
    private ServerLocation lastLocation;
    public static final String scope = "players";

    public MilkyPlayer(String id,
                       String lastUsername,
                       String nickname,
                       String chatFormat,
                       String joinLeaveFormat,
                       Double balance,
                       Integer votes,
                       Integer secondsPlayed,
                       Long joinDate,
                       ServerLocation lastLocation) {
        super(id, MilkyPlayer.scope);
        this.lastUsername = lastUsername;
        this.nickname = nickname;
        this.chatFormat = chatFormat;
        this.joinLeaveFormat = joinLeaveFormat;
        this.balance = balance;
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.joinDate = joinDate;
        this.lastLocation = lastLocation;
    }

    public MilkyPlayer(Player player) {
        uid(player.getUniqueId().toString());
        lastUsername(player.getName());
        nickname(player.getName());
        chatFormat("default");
        balance(0D);
        votes(0);
        secondsPlayed(0);
        joinDate(System.currentTimeMillis());
        lastLocation(new ServerLocation("survival", player.getLocation()));
    }



    public MilkyPlayer() {
        super();
    }

    public String lastUsername() {
        return lastUsername;
    }

    public MilkyPlayer lastUsername(String lastUsername) {
        this.lastUsername = lastUsername;
        return this;
    }

    public String nickname() {
        return nickname;
    }

    public MilkyPlayer nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String chatFormat() {
        return chatFormat;
    }

    public MilkyPlayer chatFormat(String chatFormat) {
        this.chatFormat = chatFormat;
        return this;
    }

    public String joinLeaveFormat() {
        return joinLeaveFormat;
    }

    public MilkyPlayer joinLeaveFormat(String joinLeaveFormat) {
        this.joinLeaveFormat = joinLeaveFormat;
        return this;
    }

    public Double balance() {
        return balance;
    }

    public MilkyPlayer balance(Double balance) {
        this.balance = balance;
        return this;
    }

    public Integer votes() {
        return votes;
    }

    public MilkyPlayer votes(Integer votes) {
        this.votes = votes;
        return this;
    }

    public Integer secondsPlayed() {
        return secondsPlayed;
    }

    public MilkyPlayer secondsPlayed(Integer secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
        return this;
    }

    public Long joinDate() {
        return joinDate;
    }

    public MilkyPlayer joinDate(Long joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public ServerLocation lastLocation() {
        return lastLocation;
    }

    public MilkyPlayer lastLocation(ServerLocation lastLocation) {
        this.lastLocation = lastLocation;
        return this;
    }
}
