package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class MilkyPlayerController {
    @Autowired
    private final MilkyPlayerRepository repository;

    public MilkyPlayerController(MilkyPlayerRepository repository) {
        this.repository = repository;
    }

    @Cacheable("playerCache")
    @GetMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<MilkyPlayer> all() {
        return repository.findAll();
    }

    @Cacheable("playerCache")
    @PostMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MilkyPlayer newMilkyPlayer(@RequestBody MilkyPlayer player) {
        return repository.save(player);
    }

    @Cacheable("playerCache")
    @GetMapping(value = "/players/{uuid}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public MilkyPlayer one(@PathVariable String uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
    }

    @Cacheable("playerCache")
    @DeleteMapping(value = "/players/{uuid}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void delete(@PathVariable String uuid) {
        repository.deleteById(uuid);
    }
}
