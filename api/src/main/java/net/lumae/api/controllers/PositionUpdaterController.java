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
import java.util.Map;

class PosContext {
    public PosContext(String uuid, SimpleLocation location) {
        this.uuid = uuid;
        this.location = location;
    }
    String uuid;
    SimpleLocation location;
}

@RestController
public class PositionUpdaterController {
    @Autowired
    private final MilkyPlayerRepository players;

    public PositionUpdaterController(MilkyPlayerRepository players) {
        this.players = players;
    }

    @Cacheable("playerCache")
    @GetMapping("/pos")
    public List<PosContext> all() {
        List<PosContext> positions = null;
        for (MilkyPlayer p : players.findAll()) {
            positions.add(new PosContext(p.uid, p.getLastLocation()));
        }
        return positions;
    }

    @Cacheable("playerCache")
    @PostMapping("/pos")
    public List<PosContext> allOnline(@RequestBody List<String> uuids) {
        List<PosContext> positions = null;
        Iterable res = players.findAllById(uuids);
        res.forEach(obj -> {
            MilkyPlayer p = ((MilkyPlayer) obj);
            positions.add(new PosContext(p.uid, p.getLastLocation()));
        });
        return positions;
    }

    @Cacheable("playerCache")
    @GetMapping("/pos/{uuid}")
    public SimpleLocation get(@PathVariable String uuid) {
        return players.findById(uuid).get().getLastLocation();
    }

    @Cacheable("playerCache")
    @PostMapping("/pos/{uuid}")
    public void set(@RequestParam String uuid, @RequestBody SimpleLocation loc) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, MilkyPlayer.scope));
        p.setLastLocation(loc);
        players.save(p);
    }

    @Cacheable("playerCache")
    @DeleteMapping("/pos/{uuid}")
    public void delete(@RequestParam String uuid) {
        players.deleteById(uuid);
    }
}
