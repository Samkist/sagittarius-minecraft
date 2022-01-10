package dev.samkist.lumae.sagittarius.listeners;

import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import dev.samkist.lumae.sagittarius.test.Tests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private Sagittarius plugin;
    private Tests tests;

    public JoinQuitListener(Sagittarius plugin) {
        this.plugin = plugin;
        this.tests = plugin.getTests();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        tests.logApiStatus();
        tests.tryPrintApiPlayerResult(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}
