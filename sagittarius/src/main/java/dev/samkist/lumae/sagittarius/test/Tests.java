package dev.samkist.lumae.sagittarius.test;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Home;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Homes;
import dev.samkist.lumae.sagittarius.data.models.gamemode.SimpleLocation;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Warp;
import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.global.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.storage.RESTManager;
import kong.unirest.HttpResponse;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Tests {

    private final Sagittarius apiCore;
    private final RESTManager restManager;
    private final Gson gson;
    private final Logger logger = Logger.getGlobal();
    /**
     * Samkist
     */
    private final String testingUUID = "9a96c347-7424-40fe-9fcd-cff5b8f706e0";


    public Tests(Sagittarius apiCore, RESTManager restManager) {
        this.apiCore = apiCore;
        this.restManager = restManager;
        this.gson = apiCore.gson;
    }

    public void logApiStatus() {
        logger.info(restManager.status());
    }

    public void tryPrintApiPlayerResult(Player player) {
        HttpResponse<MilkyPlayer> mp = restManager.getMilkyPlayer(player.getUniqueId().toString());
        mp.ifSuccess(succ -> {
            logger.info("Successfully loaded " + player.name());
            logger.info(gson.toJson(succ.getBody()));
        }).ifFailure(fail -> logger.severe(String.format("Failure: %d : %s", fail.getStatus(), fail.getStatusText())));
    }

    public void printPlayerJson(Player player) {
        MilkyPlayer p = new MilkyPlayer(player);
        logger.info(gson.toJson(p));
    }

    public List<String> printAndReturnAllDefaults() {
        ArrayList<String> results = new ArrayList<>();
        results.add(printAndReturnChatFormat());
        results.add(printAndReturnJLFormat());
        results.add(printAndReturnHomes());
        results.add(printAndReturnHome());
        results.add(printAndReturnWarp());
        results.add(printAndReturnLocation());
        return results;
    }

    public String printAndReturnChatFormat() {
        return printAndReturnChatFormat(chatFormat());
    }

    public String printAndReturnChatFormat(ChatFormat format) {
        logger.info("Chat Format");
        String json = gson.toJson(format);
        logger.info(json);
        return json;
    }

    public ChatFormat loadChatFormat(String json) {
        return gson.fromJson(json, ChatFormat.class);
    }

    public String printAndReturnJLFormat() {
        return printAndReturnJLFormat(joinLeaveFormat());
    }

    public String printAndReturnJLFormat(JoinLeaveFormat format) {
        logger.info("JL Format");
        String json = gson.toJson(format);
        logger.info(json);
        return json;
    }

    public JoinLeaveFormat loadJLFormat(String json) {
        return gson.fromJson(json, JoinLeaveFormat.class);
    }

    public String printAndReturnHomes() {
        return printAndReturnHomes(homes());
    }

    public String printAndReturnHomes(Homes homes) {
        logger.info("Homes");
        String json = gson.toJson(homes);
        logger.info(json);
        return json;
    }

    public Homes loadHomes(String json) {
        return gson.fromJson(json, Homes.class);
    }

    public String printAndReturnHome() {
        return printAndReturnHome(home());
    }

    public String printAndReturnHome(Home home) {
        logger.info("Home");
        String json = gson.toJson(home);
        logger.info(json);
        return json;
    }

    public Home loadHome(String json) {
        return gson.fromJson(json, Home.class);
    }

    public String printAndReturnWarp() {
        return printAndReturnWarp(warp());
    }

    public String printAndReturnWarp(Warp warp) {
        logger.info("Warp");
        String json = gson.toJson(warp);
        logger.info(json);
        return json;
    }

    public Warp loadWarp(String json) {
        return gson.fromJson(json, Warp.class);
    }

    public String printAndReturnLocation() {
        return printAndReturnLocation(location());
    }

    public String printAndReturnLocation(SimpleLocation location) {
        logger.info("Location");
        String json = gson.toJson(location);
        logger.info(json);
        return json;
    }

    public SimpleLocation loadLocation(String json) {
        return gson.fromJson(json, SimpleLocation.class);
    }

    public ChatFormat chatFormat() {
        HashMap<String, String> formats = new HashMap<>();
        formats.put("default", "testing123");
        return new ChatFormat(
                "default",
                null,
                "chatformats.default",
                formats,
                1
        );
    }

    public JoinLeaveFormat joinLeaveFormat() {
        HashMap<String, String> formats = new HashMap<>();
        formats.put("default", "testing123");
        return new JoinLeaveFormat(
                "default",
                null,
                "chatformats.default",
                formats,
                1
        );
    }

    public Homes homes() {
        HashMap<String, SimpleLocation> homes = new HashMap<>();
        Home home = home();
        homes.put(home.uid, home.getLocation());
        return new Homes(testingUUID, homes);
    }

    public Home home() {
        return new Home("home", location());
    }

    public Warp warp() {
        return new Warp(
                "default-warp",
                "warps",
                location(),
                testingUUID,
                "warps.warp",
                true
        );
    }

    public SimpleLocation location() {
        return new SimpleLocation(
                "world",
                0.0,
                80.0,
                20.0,
                0.0f,
                0.0f
        );
    }

}
