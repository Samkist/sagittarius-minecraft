package dev.samkist.lumae.sagittarius.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class MilkyPlayer extends MilkyModel {
    private String lastUsername;
    private Long balance;
    private Integer votes;
    private Integer secondsPlayed;
    private Long joinDate;
    private SimpleLocation lastLocation;
    public static final String scope = "players";

    public MilkyPlayer(String id,
                       String lastUsername,
                       Long balance,
                       Integer votes,
                       Integer secondsPlayed,
                       Long joinDate,
                       SimpleLocation lastLocation) {
        super(id, MilkyPlayer.scope);
        this.lastUsername = lastUsername;
        this.balance = balance;
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.joinDate = joinDate;
        this.lastLocation = lastLocation;
    }

    public MilkyPlayer(Player player) {
        setId(player.getUniqueId().toString());
        setLastUsername(player.getName());
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

    public SimpleLocation getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(SimpleLocation lastLocation) {
        this.lastLocation = lastLocation;
    }
}
