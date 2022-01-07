package dev.samkist.lumae.sagittarius.listeners;

import dev.samkist.lumae.sagittarius.Sagittarius;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener {

    private Sagittarius plugin;

    public JoinQuitListener(Sagittarius plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}
