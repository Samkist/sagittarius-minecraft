package net.lumae.api.controllers;

import dev.samkist.lumae.sagittarius.data.models.Homes;
import dev.samkist.lumae.sagittarius.data.models.Warp;
import net.lumae.api.repository.RecordNotFoundException;
import net.lumae.api.repository.WarpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarpController {

    @Autowired
    private final WarpRepository warps;

    public WarpController(WarpRepository warps) {
        this.warps = warps;
    }

    @Cacheable("warpCache")
    @GetMapping("/warps")
    public List<Warp> all() {
        return warps.findAll();
    }

    @Cacheable("warpCache")
    @PostMapping("/warps")
    public Warp newWarp(@RequestBody Warp warp) {
        return warps.save(warp);
    }

    @Cacheable("warpCache")
    @GetMapping("/warps/{id}")
    public Warp one(@PathVariable String id) {
        return warps.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, Warp.scope));
    }

    @Cacheable("warpCache")
    @DeleteMapping("/warps/{id}")
    public void delete(@PathVariable String id) {
        warps.deleteById(id);
    }
}
