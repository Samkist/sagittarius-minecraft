package net.lumae.api.controllers.gamemode;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.api.GlobalWarps;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Warp;
import net.lumae.api.repository.RecordNotFoundException;
import net.lumae.api.repository.WarpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WarpController {

    @Autowired
    private final WarpRepository globalWarps;

    public WarpController(WarpRepository globalWarps) {
        this.globalWarps = globalWarps;
    }

    @Cacheable("warpCache")
    @GetMapping("/servers/{server}/warps")
    public List<Warp> all(@PathVariable String server) {
        return warpsByServer(server);
    }

    @Cacheable("warpCache")
    @PostMapping("/servers/{server}/warps")
    public Warp newWarp(@PathVariable String server, @RequestBody Warp warp) {
        return saveWarp(server, warp);
    }

    @Cacheable("warpCache")
    @GetMapping("/servers/{server}/warps/{id}")
    public Warp one(@PathVariable String server, @PathVariable String id) {
        return warpById(server, id)
                .orElseThrow(() -> new RecordNotFoundException(id, Warp.scope));
    }

    @Cacheable("warpCache")
    @DeleteMapping("/servers/{server}/warps/{id}")
    public void delete(@PathVariable String server, @PathVariable String id) {
        GlobalWarps global = globalWarpsByServer(server);
        Optional.ofNullable(global.removeModel(id))
                .orElseThrow(() -> new RecordNotFoundException(id, Warp.scope));
        globalWarps.save(global);
    }

    private Optional<Warp> warpById(String server, String id) {
        GlobalWarps global = globalWarpsByServer(server);
        return Optional.ofNullable(global.getModelByKey(id));
    }

    private List<Warp> warpsByServer(String server) {
        return globalWarpsByServer(server).modelMap().values().stream().collect(Collectors.toList());
    }

    private GlobalWarps globalWarpsByServer(String server) {
        if(Objects.nonNull(GameMode.getByName(server))) {
            if(!globalWarps.existsById(server)) {
                GlobalWarps empty = new GlobalWarps(server, new HashMap<>());
                globalWarps.save(empty);
                return empty;
            } else {
                return globalWarps.findById(server).orElse(null);
            }
        } else {
            throw new RecordNotFoundException(server, GameMode.class);
        }
    }

    private Warp saveWarp(String server, Warp warp) {
        GlobalWarps global = globalWarpsByServer(server);
        global.saveModel(warp.uid, warp);
        globalWarps.save(global);
        return warp;
    }
}
