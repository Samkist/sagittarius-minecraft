package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.Location;

import java.math.BigDecimal;

public class MilkyPlayer extends MilkyModel {
    final String lastUsername;
    final BigDecimal balance;
    final Integer votes;
    final Integer secondsPlayed;
    final Long joinDate;
    final Location lastLocation;

    protected MilkyPlayer(String id, String scope, String lastUsername, BigDecimal balance, Integer votes, Integer secondsPlayed, Long joinDate, Location lastLocation) {
        super(id, scope);
        this.lastUsername = lastUsername;
        this.balance = balance;
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.joinDate = joinDate;
        this.lastLocation = lastLocation;
    }
}
