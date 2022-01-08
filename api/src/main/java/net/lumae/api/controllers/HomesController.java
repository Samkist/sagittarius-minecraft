package net.lumae.api.controllers;

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
        return homes.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException(uuid, Homes.scope));
    }

    @Cacheable("homeCache")
    @DeleteMapping("/players/{uuid}/homes")
    public void delete(@PathVariable String uuid) {
        homes.deleteById(uuid);
    }
}
