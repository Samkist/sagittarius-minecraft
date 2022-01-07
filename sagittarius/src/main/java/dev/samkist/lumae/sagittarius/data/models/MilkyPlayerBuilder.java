package dev.samkist.lumae.sagittarius.data.models;

import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class MilkyPlayerBuilder extends ModelBuilder<MilkyPlayer, MilkyPlayerBuilder> {
    private String lastUsername;
    private BigDecimal balance;
    private Integer votes;
    private Integer secondsPlayed;
    private Integer joinDate;

    public MilkyPlayerBuilder(MilkyPlayer player) {
        super(player);
    }

    public MilkyPlayerBuilder(Player player) {
        super();
        lastUsername = player.getName();
    }

    @Override
    public MilkyPlayer build() {
        return new MilkyPlayer(id(), "milkyPlayers", lastUsername, balance, votes, secondsPlayed, joinDate);
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

    public MilkyPlayerBuilder joinDate(Integer joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public Integer joinDate() {
        return joinDate;
    }


}
