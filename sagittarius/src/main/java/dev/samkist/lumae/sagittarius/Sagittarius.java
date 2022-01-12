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


public class Sagittarius {

    public final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private SagittariusApi sagittariusApi;
    private RESTManager restManager;
    private Tests tests;
    private static final String apiRoute = "http://198.55.58.21";
    public static final long LAST_START_TIME = System.currentTimeMillis();
    private static final Sagittarius instance = new Sagittarius();
    private static boolean ran = false;

    private Sagittarius() {

    }

    public static Sagittarius getSagittarius() {
        if(ran) {
            return null;
        } else {
            ran = true;
        }
        return instance;
    }

    public void enable() {
        restManager = new RESTManager(this, gson, apiRoute);

        tests = new Tests(this, restManager);
    }

    public void disable() {

    }

    public RESTManager getRestManager() {
        return restManager;
    }

    public Tests getTests() {
        return tests;
    }
}
