package net.lumae.api.controllers.gamemode;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.api.GlobalHomes;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Home;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Homes;
import net.lumae.api.repository.HomesRepository;
import net.lumae.api.repository.MilkyPlayerRepository;
import net.lumae.api.repository.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static dev.samkist.lumae.sagittarius.data.gamemode.GameMode.*;

@RestController
public class HomesController {

    @Autowired
    private final HomesRepository globalHomes;

    @Autowired
    private final MilkyPlayerRepository players;

    public HomesController(HomesRepository globalHomes, MilkyPlayerRepository players) {
        this.globalHomes = globalHomes;
        this.players = players;
    }

    /*
    HOME CRUD
     */

    @Cacheable("homeCache")
    @GetMapping("/servers/{server}/players/{uuid}/homes")
    public Homes one(@PathVariable String server, @PathVariable String uuid) {
        Homes homes = homesById(server, uuid);
        return homes;
    }

    @Cacheable("homeCache")
    @PostMapping("/servers/{server}/players/{uuid}/homes")
    public Homes newHome(@PathVariable String server, @PathVariable String uuid, @RequestBody Home home) {
        Homes playerHomes = homesById(server, uuid);
        playerHomes.addHome(home);
        return saveHomes(server, playerHomes);
    }

    @Cacheable("homeCache")
    @GetMapping("/servers/{server}/players/{uuid}/homes/{name}")
    public Home oneHome(@PathVariable String server, @PathVariable String uuid, @PathVariable String name) {
        return homesById(server, uuid).getHomeById(name);
    }

    @Cacheable("homeCache")
    @DeleteMapping("/servers/{server}/players/{uuid}/homes/{name}")
    public void delete(@PathVariable String server, @PathVariable String uuid, @PathVariable String name) {
        Homes playerHomes = homesById(server, uuid);
        playerHomes.removeHome(name);
        saveHomes(server, playerHomes);
    }

    private Homes homesById(String server, String uuid) {
        GlobalHomes globalHomes = globalHomesById(uuid);
        if(Objects.isNull(globalHomes)) {
            throw new RecordNotFoundException(uuid, globalHomes.getClass());
        }
        GameMode gameMode = getByName(server);
        if(globalHomes.hasModel(gameMode)) {
            return Optional.ofNullable(globalHomes.getModelByKey(gameMode))
                    .orElseThrow(() -> new RecordNotFoundException(uuid, Homes.class));
        } else {
            Homes empty = new Homes(uuid, new HashMap<>());
            saveHomes(server, empty);
            return homesById(server, uuid);
        }
    }

    private GlobalHomes globalHomesById(String uuid) {
        if(players.existsById(uuid)) {
            if(!globalHomes.existsById(uuid)) {
                GlobalHomes empty = new GlobalHomes(uuid, new HashMap<>());
                globalHomes.save(empty);
                return globalHomesById(uuid);
            } else {
                return globalHomes.findById(uuid).orElse(null);
            }
        } else {
            return null;
        }
    }

    private Homes saveHomes(String server, Homes homes) {
        GlobalHomes global = globalHomesById(homes.getUid());
        global.saveModel(getByName(server), homes);
        globalHomes.save(global);
        return homes;
    }

    private String getColl(String server) {
        return getMilkyColl(server, Homes.scope);
    }
}
