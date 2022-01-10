package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.Home;
import dev.samkist.lumae.sagittarius.data.models.Homes;
import net.lumae.api.repository.HomesRepository;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class HomesController {

    @Autowired
    private final HomesRepository homes;

    @Autowired
    private final MilkyPlayerRepository players;

    public HomesController(HomesRepository homes, MilkyPlayerRepository players) {
        this.homes = homes;
        this.players = players;
    }

    /*
    HOME CRUD
     */

    @Cacheable("homeCache")
    @GetMapping("/players/{uuid}/homes")
    public Homes one(@PathVariable String uuid) {
        return homesById(uuid);
    }

    @Cacheable("homeCache")
    @PostMapping("/players/{uuid}/homes")
    public Homes newHome(@PathVariable String uuid, @RequestBody Home home) {
        Homes playerHomes = homesById(uuid);
        playerHomes.addHome(home);
        return homes.save(playerHomes);
    }

    @Cacheable("homeCache")
    @GetMapping("/players/{uuid}/homes/{name}")
    public Home oneHome(@PathVariable String uuid, @PathVariable String name) {
        return homesById(uuid).getHomeById(name);
    }

    @Cacheable("homeCache")
    @DeleteMapping("/players/{uuid}/homes/{name}")
    public void delete(@PathVariable String uuid, @PathVariable String name) {
        Homes playerHomes = homesById(uuid);
        playerHomes.removeHome(name);
        homes.save(playerHomes);
    } //TODO: Add deleteHomeByName(uuid, name) function

    private Homes homesById(String uuid) {
        if(players.existsById(uuid)) {
            if(!homes.existsById(uuid)) {
                Homes empty = new Homes(uuid, new HashMap<>());
                homes.save(empty);
                return empty;
            } else {
                return homes.findById(uuid).orElse(null);
            }
        } else {
            return null;
        }
    }
}
