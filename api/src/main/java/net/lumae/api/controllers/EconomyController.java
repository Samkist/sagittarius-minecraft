package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class TransferContext {
    String to;
    String from;
    Long amount;
}

class TransactionContext {
    String uuid;
    Long amount;
}

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
    public void set(@RequestBody TransactionContext transaction) {
        MilkyPlayer p = players.findById(transaction.uuid)
                .orElseThrow(() -> new RecordNotFoundException(transaction.uuid, MilkyPlayer.scope));
        p.setBalance(transaction.amount);
        players.save(p);
    }

    @Cacheable("playerCache")
    @PostMapping("/economy/transfer")
    public boolean transfer(@RequestBody TransferContext transfer) {
        MilkyPlayer recipient = players.findById(transfer.to)
                .orElseThrow(() -> new RecordNotFoundException(transfer.to, MilkyPlayer.scope));
        MilkyPlayer sender = players.findById(transfer.from)
                .orElseThrow(() -> new RecordNotFoundException(transfer.from, MilkyPlayer.scope));
        if (sender.getBalance() >= transfer.amount) {
            recipient.setBalance(recipient.getBalance() + transfer.amount);
            sender.setBalance(sender.getBalance() - transfer.amount);
            players.save(recipient);
            players.save(sender);
            return true;
        }
        return false;
    }

    @Cacheable("playerCache")
    @PostMapping("/economy/deposit")
    public void deposit(@RequestBody TransactionContext transaction) {
        MilkyPlayer p = players.findById(transaction.uuid)
                .orElseThrow(() -> new RecordNotFoundException(transaction.uuid, MilkyPlayer.scope));
        p.setBalance(transaction.amount);
        players.save(p);
    }

    @Cacheable("playerCache")
    @DeleteMapping("/economy/{uuid}")
    public void delete(@PathVariable String uuid) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setBalance(0L);
        players.save(p);
    }
}
