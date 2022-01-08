package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.Home;
import dev.samkist.lumae.sagittarius.data.models.Homes;
import net.lumae.api.ApiApplication;
import net.lumae.api.repository.HomesRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SagittariusController
public class HomesController {

    @Autowired
    private final HomesRepository homes;

    public HomesController(HomesRepository homes) {
        this.homes = homes;
    }

    /*
    HOME CRUD
     */

    @Cacheable("homeCache")
    @GetMapping("/homes")
    public List<Homes> all() {
        return homes.findAll();
    }

    @Cacheable("homeCache")
    @GetMapping("/homes/{uuid}")
    public Homes newHomes(@PathVariable String uuid, @RequestBody Homes home) {
        if(home.uid.equalsIgnoreCase(uuid)) {
            return homes.save(home);
        } else {
            return null;
        }
    }

    @Cacheable("homeCache")
    @GetMapping("/homes/{uuid}")
    public Homes one(@PathVariable String uuid) {
        return homesById(uuid);
    }

    @Cacheable("homeCache")
    @PostMapping("/homes/{uuid}/homes")
    public Homes newHome(@PathVariable String uuid, @RequestBody Home home) {
        Homes playerHomes = homesById(uuid);
        playerHomes.addHome(home);
        return homes.save(playerHomes);
    }

    @Cacheable("homeCache")
    @GetMapping("/homes/{uuid}/homes/{name}")
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

    @Cacheable("homeCache")
    @DeleteMapping("/players/{uuid}/homes")
    public void delete(@PathVariable String uuid) {
        homes.deleteById(uuid);
    }

    private Homes homesById(String uuid) {
        return homes.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, Homes.scope));
    }
}
