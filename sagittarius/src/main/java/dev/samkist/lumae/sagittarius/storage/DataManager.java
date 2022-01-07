package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private static final Sagittarius plugin = JavaPlugin.getPlugin(Sagittarius.class);
    private FileManager fileManager;
    private Gson gson;
    private HashMap<UUID, MilkyPlayer> players = new HashMap<>();


}
