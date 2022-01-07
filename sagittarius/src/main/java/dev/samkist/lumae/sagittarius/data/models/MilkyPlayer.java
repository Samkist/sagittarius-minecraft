package dev.samkist.lumae.sagittarius.data.models;

import java.math.BigDecimal;

public class MilkyPlayer extends MilkyModel {
    final String lastUsername;
    final BigDecimal balance;
    final Integer votes;
    final Integer secondsPlayed;
    final Integer joinDate;

    protected MilkyPlayer(String id, String scope, String lastUsername, BigDecimal balance, Integer votes, Integer secondsPlayed, Integer joinDate) {
        super(id, "milkyPlayers");
        this.lastUsername = lastUsername;
        this.balance = balance;
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.joinDate = joinDate;
    }
}
