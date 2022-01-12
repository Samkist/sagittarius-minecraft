package net.lumae.api.controllers.gamemode;

import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class KillDeathController {
    @Autowired
    private final MilkyPlayerRepository players;

    public KillDeathController(MilkyPlayerRepository players) {
        this.players = players;
    }

    @Cacheable("playerCache")
    @GetMapping("/players/{uuid}/kdr")
    public Double get(@PathVariable String uuid) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        return 0.0;
    }
/*
    //TODO: Make /players/{uuid}/kd set Kills and Deaths.

    @Cacheable("playerCache")
    @GetMapping("/players/{uuid}/kills")
    public Integer getKills(@PathVariable String uuid) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        return p.getKills();
    }

    @Cacheable("playerCache")
    @GetMapping("/players/{uuid}/deaths")
    public Integer getDeaths(@PathVariable String uuid) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        return p.getDeaths();
    }

    @Cacheable("playerCache")
    @PostMapping("/players/{uuid}/kills")
    public void setKills(@PathVariable String uuid, @RequestBody Integer kills) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setKills(kills);
    }

    @Cacheable("playerCache")
    @PostMapping("/players/{uuid}/deaths")
    public void setDeaths(@PathVariable String uuid, @RequestBody Integer deaths) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setDeaths(deaths);
    }

    @Cacheable("playerCache")
    @DeleteMapping("/players/{uuid}/kd")
    public void delete(@PathVariable String uuid) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setKd(0, 0);
        players.save(p);
    }*/
}
