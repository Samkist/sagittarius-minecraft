package dev.samkist.lumae.sagittarius.listeners;

import dev.samkist.lumae.sagittarius.test.Tests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private Tests tests;

    public JoinQuitListener(Tests tests) {
        this.tests = tests;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        tests.logApiStatus();
        tests.printPlayerJson(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}
