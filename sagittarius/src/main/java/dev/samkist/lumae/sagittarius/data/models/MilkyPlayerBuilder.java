package dev.samkist.lumae.sagittarius.data.models;

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

    @Override
    public MilkyPlayer build() {
        return new MilkyPlayer(id(), scope(), lastUsername, balance, votes, secondsPlayed, joinDate);
    }

    public MilkyPlayerBuilder lastUsername(String lastUsername) {
        this.lastUsername = lastUsername;
        return this;
    }

    public String lastUsername() {
        return lastUsername;
    }


}
