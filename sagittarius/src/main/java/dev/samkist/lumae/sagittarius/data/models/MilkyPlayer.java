package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.entity.Player;

public class MilkyPlayer extends MilkyModel {
    private String lastUsername;
    private String nickname;
    private Long balance;
    private Integer votes;
    private Integer kills;
    private Integer deaths;
    private Integer secondsPlayed;
    private Long joinDate;
    private SimpleLocation lastLocation;
    public static final String scope = "players";

    public MilkyPlayer(String id,
                       String lastUsername,
                       String nickname,
                       Long balance,
                       Integer votes,
                       Integer secondsPlayed,
                       Long joinDate,
                       SimpleLocation lastLocation) {
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
        setNickname(player.displayName().examinableName());
        setBalance(0L);
        setVotes(0);
        setSecondsPlayed(0);
        setJoinDate(System.currentTimeMillis());
        setLastLocation(new SimpleLocation(player.getLocation()));
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

    public Integer getKills() {
        return this.kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return this.deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public double getKdr() {
        return ((double)this.kills)/this.deaths;
    }

    public void setKd(Integer kills, Integer deaths) {
        this.kills = kills;
        this.deaths = deaths;
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

    public SimpleLocation getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(SimpleLocation lastLocation) {
        this.lastLocation = lastLocation;
    }
}
