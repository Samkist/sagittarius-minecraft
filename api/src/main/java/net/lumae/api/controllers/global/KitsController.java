package net.lumae.api.controllers.global;

import dev.samkist.lumae.sagittarius.data.models.gamemode.Kit;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.exceptions.PlayerNotFoundRuntimeException;
import net.lumae.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class KitsController {

    @Autowired
    private final MilkyPlayerRepository players;
    @Autowired
    private final KitRepository kits;

    public KitsController(MilkyPlayerRepository players, KitRepository kits) {
        this.players = players;
        this.kits = kits;
    }

    @Cacheable("playerCache")
    @PostMapping("/kits/{id}")
    public Kit get(@RequestBody String uuid, @PathVariable String kitName) {
        MilkyPlayer p = players.findById(uuid)
                .orElseThrow(() -> new PlayerNotFoundRuntimeException(uuid, MilkyPlayer.scope));
        Kit k = kits.findById(kitName)
                .orElseThrow(() -> new RecordNotFoundException(kitName, Kit.scope));
        return k;
    }


}
