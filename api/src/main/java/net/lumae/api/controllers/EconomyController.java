package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EconomyController {
    @Autowired
    private final MilkyPlayerRepository players;

    public EconomyController(MilkyPlayerRepository players) {
        this.players = players;
    }

    @Cacheable("playerCache")
    @GetMapping("/economy")
    public List<MilkyPlayer> all() {
        return players.findAll(Sort.by(Sort.Direction.DESC, "balance"));
    }

    @Cacheable("playerCache")
    @GetMapping("/economy/{uuid}")
    public Long get(@PathVariable String uuid) {
        return players.findById(uuid).get().getBalance();
    }

    @Cacheable("playerCache")
    @PostMapping("/economy/set")
    public void set(@RequestBody String uuid, @RequestBody Long amount) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setBalance(amount);
        players.save(p);
    }

    @Cacheable("playerCache")
    @PostMapping("/economy/transfer")
    public boolean transfer(@RequestBody String to, @RequestBody String from, @RequestBody Long amount) {
        MilkyPlayer recipient = players.findById(to)
                .orElseThrow(() -> new RecordNotFoundException(to, MilkyPlayer.scope));
        MilkyPlayer sender = players.findById(from)
                .orElseThrow(() -> new RecordNotFoundException(from, MilkyPlayer.scope));
        if (sender.getBalance() >= amount) {
            recipient.setBalance(recipient.getBalance() + amount);
            sender.setBalance(sender.getBalance() - amount);
            players.save(recipient);
            players.save(sender);
            return true;
        }
        return false;
    }

    @Cacheable("playerCache")
    @PostMapping("/economy/deposit")
    public void deposit(@RequestBody String uuid, @RequestBody Long amount) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setBalance(amount);
        players.save(p);
    }
}
