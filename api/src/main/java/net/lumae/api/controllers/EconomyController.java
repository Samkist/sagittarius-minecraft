package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import net.lumae.api.repository.MilkyPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SagittariusController
public class EconomyController {
    @Autowired
    private final MilkyPlayerRepository players;

    public EconomyController(MilkyPlayerRepository players) {
        this.players = players;
    }

    @Cacheable("playerCache")
    @GetMapping("/economy/get")
    public List<MilkyPlayer> all() {
        return players.findAll(Sort.by(Sort.Direction.DESC, "balance"));
    }

    @Cacheable("playerCache")
    @GetMapping("/economy/get/{uuid}")
    public Long get(@PathVariable String uuid) {
        return players.findById(uuid).get().getBalance();
    }

    @Cacheable("playerCache")
    @GetMapping("/economy/transfer")
    public boolean transfer(@RequestBody MilkyPlayer to, @RequestBody MilkyPlayer from, @RequestBody Long amount) {
        Long senderBalance = from.getBalance();
        Long recipientBalance;
        if (senderBalance >= amount) {
            recipientBalance = to.getBalance();
            from.setBalance(senderBalance - amount);
            to.setBalance(recipientBalance + amount);
            return true;
        }
        return false;
    }

    @Cacheable("playerCache")
    @GetMapping("/economy/deposit")
    public void deposit(@RequestBody MilkyPlayer player, @RequestBody Long amount) {
        player.setBalance(player.getBalance() + amount);
    }

    @Cacheable("playerCache")
    @GetMapping("/economy/set")
    public void set(@RequestBody MilkyPlayer player, @RequestBody Long amount) {
        player.setBalance(amount);
    }
}
