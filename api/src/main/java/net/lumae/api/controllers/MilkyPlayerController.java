package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import net.lumae.api.ApiApplication;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MilkyPlayerController {
    @Autowired
    private final MilkyPlayerRepository players;

    public MilkyPlayerController(MilkyPlayerRepository players) {
        this.players = players;
    }

    /*
    PLAYER CRUD
     */

    @Cacheable("playerCache")
    @GetMapping("/players")
    public List<MilkyPlayer> all() {
        return players.findAll();
    }

    @Cacheable("playerCache")
    @PostMapping("/players")
    public MilkyPlayer newMilkyPlayer(@RequestBody MilkyPlayer player) {
        return players.save(player);
    }

    @Cacheable("playerCache")
    @GetMapping("/players/{uuid}")
    public MilkyPlayer one(@PathVariable String uuid) {
        return players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
    }

    @Cacheable("playerCache")
    @DeleteMapping("/players/{uuid}")
    public void delete(@PathVariable String uuid) {
        players.deleteById(uuid);
    }




}
