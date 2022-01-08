package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SagittariusController
public class TimeController {
    @Autowired
    private final MilkyPlayerRepository players;

    public TimeController(MilkyPlayerRepository players) {
        this.players = players;
    }

    @Cacheable("playerCache")
    @GetMapping("/time/played/")
    public List<MilkyPlayer> all() {
        return players.findAll(Sort.by(Sort.Direction.DESC, "secondsPlayed"));
    }

    @Cacheable("playerCache")
    @GetMapping("/time/played/{uuid}")
    public int get(@PathVariable String uuid) {
        return players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope))
                .getSecondsPlayed();
    }

    @Cacheable("playerCache")
    @PostMapping("/time/played/")
    public String add(@RequestBody List<MilkyPlayer> activePlayers, @RequestBody int seconds) {
        for (MilkyPlayer p : activePlayers) {
            players.findById(p.uid).get().setSecondsPlayed(p.getSecondsPlayed() + seconds);
        }
        return "success:true";
    }

    @Cacheable("playerCache")
    @DeleteMapping("/time/played")
    public void delete(@RequestBody String uuid) {
        players.deleteById(uuid);
    }
}
