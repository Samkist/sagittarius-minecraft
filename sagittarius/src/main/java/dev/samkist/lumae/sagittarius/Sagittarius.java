package dev.samkist.lumae.sagittarius;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.data.adapters.LocationAdapter;
import dev.samkist.lumae.sagittarius.events.SagittariusReadyEvent;
import dev.samkist.lumae.sagittarius.storage.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;


public class Sagittarius extends JavaPlugin {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Location.class, new LocationAdapter())
            .setPrettyPrinting()
            .create();
    private SagittariusApi sagittariusApi;
    private FileManager fileManager = new FileManager(this);
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

        fileManager.load();

        SagittariusReadyEvent readyEvent = new SagittariusReadyEvent(sagittariusApi);

        Bukkit.getPluginManager().callEvent(readyEvent);
    }

    @Override
    public void onDisable() {

    }
}
