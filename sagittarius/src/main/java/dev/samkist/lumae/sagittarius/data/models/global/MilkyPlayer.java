package dev.samkist.lumae.sagittarius.data.models.global;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import org.bukkit.entity.Player;

public class MilkyPlayer extends MilkyModel {
    private String lastUsername;
    private String nickname;
    private Long balance;
    private Integer votes;
    private Integer secondsPlayed;
    private Long joinDate;
    private ServerLocation lastLocation;
    public static final String scope = "players";

    public MilkyPlayer(String id,
                       String lastUsername,
                       String nickname,
                       Long balance,
                       Integer votes,
                       Integer secondsPlayed,
                       Long joinDate,
                       ServerLocation lastLocation) {
        super(id, MilkyPlayer.scope);
        this.lastUsername = lastUsername;
        this.nickname = nickname;
        this.balance = balance;
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.joinDate = joinDate;
        this.lastLocation = lastLocation;
    }

    public MilkyPlayer(Player player) {
        setUid(player.getUniqueId().toString());
        setLastUsername(player.getName());
        setNickname(player.getName());
        setBalance(0L);
        setVotes(0);
        setSecondsPlayed(0);
        setJoinDate(System.currentTimeMillis());
        setLastLocation(new ServerLocation("survival", player.getLocation()));
    }

    public MilkyPlayer() {
        super();
    }

    public String getLastUsername() {
        return lastUsername;
    }

    public void setLastUsername(String lastUsername) {
        this.lastUsername = lastUsername;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getSecondsPlayed() {
        return secondsPlayed;
    }

    public void setSecondsPlayed(Integer secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
    }

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
    }

    public ServerLocation getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(ServerLocation lastLocation) {
        this.lastLocation = lastLocation;
    }
}