package net.lumae.orion;

import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.events.SagittariusReadyEvent;
import dev.samkist.lumae.sagittarius.listeners.JoinQuitListener;
import dev.samkist.lumae.sagittarius.test.Tests;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Orion extends JavaPlugin {

    private static SagittariusApi api = SagittariusApi.generateApi();
    private Tests tests;

    @Override
    public void onEnable() {
        tests = api.getTests();

        getServer().getPluginManager().registerEvents(new JoinQuitListener(tests), this);

        SagittariusReadyEvent readyEvent = new SagittariusReadyEvent(api);

        Bukkit.getServer().getPluginManager().callEvent(readyEvent);
    }

    @Override
    public void onDisable() {

    }
}
