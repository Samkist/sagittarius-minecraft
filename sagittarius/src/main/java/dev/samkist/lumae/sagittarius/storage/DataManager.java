package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.global.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;

import java.util.HashMap;
import java.util.UUID;

public class DataManager {

    private Gson gson;
    private HashMap<UUID, MilkyPlayer> players = new HashMap<>();
    private HashMap<String, ChatFormat> chatFormats = new HashMap<>();
    private HashMap<String, JoinLeaveFormat> joinLeaveFormats = new HashMap<>();


}
