package dev.samkist.lumae.sagittarius;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.data.adapters.LocationAdapter;
import dev.samkist.lumae.sagittarius.data.models.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.ChatFormatBuilder;
import dev.samkist.lumae.sagittarius.events.SagittariusReadyEvent;
import dev.samkist.lumae.sagittarius.storage.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

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

        ChatFormatBuilder builder = new ChatFormatBuilder();

        HashMap<String, String> map = new HashMap<>();
        map.put("format", "nigs");

        ChatFormat format1 = builder.id("default")
                .permission("chatformat.default")
                .priority(1010)
                .formatStrings(map).build();
        ChatFormat format2 = builder.id("asss")
                .permission("pooop.default")
                .priority(1013)
                .formatStrings(map).build();
        HashMap<String, ChatFormat> map2 = new HashMap<>();
        HashMap<String, ChatFormat> map3 = new HashMap<>();
        map2.put(format1.id, format1);
        map2.put(format2.id, format2);
        fileManager.saveMap(gson, format1.scope, map2);
        fileManager.loadMap(gson, format1.scope, new TypeToken<HashMap<String, ChatFormat>>() {});
        map3.entrySet().stream().forEach(e -> getLogger().info(e.getKey()));
    }

    @Override
    public void onDisable() {

    }
}
