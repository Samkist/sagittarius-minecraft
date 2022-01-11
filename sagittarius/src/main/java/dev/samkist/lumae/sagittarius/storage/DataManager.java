package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class DataManager {

    private Gson gson;
    private HashMap<UUID, MilkyPlayer> players = new HashMap<>();
    private HashMap<String, ChatFormat> chatFormats = new HashMap<>();
    private HashMap<String, JoinLeaveFormat> joinLeaveFormats = new HashMap<>();


}
