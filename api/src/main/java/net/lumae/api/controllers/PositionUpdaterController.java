package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import dev.samkist.lumae.sagittarius.data.models.SimpleLocation;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SagittariusController
public class PositionUpdaterController {
    @Autowired
    private final MilkyPlayerRepository players;

    public PositionUpdaterController(MilkyPlayerRepository players) {
        this.players = players;
    }

    @Cacheable("playerCache")
    @GetMapping("/pos/")
    public List<MilkyPlayer> all() {
        return players.findAll();
    }

    @Cacheable("playerCache")
    @GetMapping("/pos/{uuid}")
    public SimpleLocation get(@PathVariable String uuid) {
        return players.findById(uuid).get().getLastLocation();
    }

    @Cacheable("playerCache")
    @PostMapping("/pos/")
    public void set(@RequestBody String uuid, @RequestBody SimpleLocation loc) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setLastLocation(loc);
        players.save(p);
    }

    @Cacheable("playerCache")
    @DeleteMapping("/pos/")
    public void delete(@RequestBody String uuid, @RequestBody Long amount) {
        players.deleteById(uuid);
    }
}
