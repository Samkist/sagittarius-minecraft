package dev.samkist.lumae.sagittarius;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.events.SagittariusReadyEvent;
import dev.samkist.lumae.sagittarius.listeners.JoinQuitListener;
import dev.samkist.lumae.sagittarius.storage.RESTManager;
import dev.samkist.lumae.sagittarius.test.Tests;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Sagittarius extends JavaPlugin {

    public final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private SagittariusApi sagittariusApi;
    private RESTManager restManager;
    private Tests tests;
    private static final String apiRoute = "http://198.55.58.21";
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

        restManager = new RESTManager(this, gson, apiRoute);

        tests = new Tests(this, restManager);

        getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);

        SagittariusReadyEvent readyEvent = new SagittariusReadyEvent(sagittariusApi);

        Bukkit.getPluginManager().callEvent(readyEvent);
    }

    @Override
    public void onDisable() {

    }

    public RESTManager getRestManager() {
        return restManager;
    }

    public Tests getTests() {
        return tests;
    }
}
