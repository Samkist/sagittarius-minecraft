package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class MilkyPlayerBuilder extends ModelBuilder<MilkyPlayer, MilkyPlayerBuilder> {
    private String lastUsername;
    private BigDecimal balance;
    private Integer votes;
    private Integer secondsPlayed;
    private Long joinDate;
    private Location lastLocation;

    public MilkyPlayerBuilder(MilkyPlayer player) {
        super(player);
    }

    public MilkyPlayerBuilder(Player player) {
        super();
        lastUsername = player.getName();
        balance = new BigDecimal(0);
        votes = 0;
        secondsPlayed = 0;
        joinDate = System.currentTimeMillis();
        lastLocation = player.getLocation();
    }

    @Override
    public MilkyPlayer build() {
        return new MilkyPlayer(id(), "milky-players", lastUsername, balance, votes, secondsPlayed, joinDate, lastLocation);
    }

    public MilkyPlayerBuilder lastUsername(String lastUsername) {
        this.lastUsername = lastUsername;
        return this;
    }

    public String lastUsername() {
        return lastUsername;
    }

    public MilkyPlayerBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public BigDecimal balance() {
        return balance;
    }

    public MilkyPlayerBuilder votes(Integer votes) {
        this.votes = votes;
        return this;
    }

    public Integer votes() {
        return votes;
    }

    public MilkyPlayerBuilder secondsPlayed(Integer secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
        return this;
    }

    public Integer secondsPlayed() {
        return secondsPlayed;
    }

    public MilkyPlayerBuilder joinDate(Long joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public Long joinDate() {
        return joinDate;
    }

    public MilkyPlayerBuilder lastLocation(Location location) {
        this.lastLocation = location;
        return this;
    }

    public Location lastLocation() {
        return lastLocation;
    }


}
