package dev.samkist.lumae.sagittarius.listeners;

import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private Sagittarius plugin;

    public JoinQuitListener(Sagittarius plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        plugin.getLogger().info(plugin.gson.toJson(new MilkyPlayer(event.getPlayer())));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}
