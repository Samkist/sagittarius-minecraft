package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Warp;
import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Home;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Homes;
import dev.samkist.lumae.sagittarius.data.models.global.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import kong.unirest.*;

import java.util.List;

import static dev.samkist.lumae.sagittarius.storage.Routes.*;

@SuppressWarnings("unchecked")
public class RESTProvider {

    private final Sagittarius plugin;
    private final String apiHost;
    private final HttpResponseGenerator generator;
    public static final String SERVERS = "/servers/{server}";

    //GLOBAL

    public static final String PLAYERS = "/players";
    public static final String FORMATS_CHAT = "/formats/chat";
    public static final String FORMATS_JL = "/formats/join-leave";

    // PER SERVER

    public static final String HOMES = SERVERS + "/players/{uuid}/homes";
    public static final String ECONOMY = SERVERS + "/economy";
    public static final String WARPS = SERVERS + "/warps";
    public static final List<String> id = List.of("id");

    public RESTProvider(Sagittarius plugin, Gson gson, String apiHost) {
        this.plugin = plugin;
        this.apiHost = apiHost;
        this.generator = new HttpResponseGenerator(gson, apiHost);
    }

    public String status() {
        return Unirest.get(apiHost)
                .asString().getBody();
    }

    /*
    GLOBAL ROUTES
     */

    //MILKY PLAYER

    public HttpResponse<List<MilkyPlayer>> getAllPlayers() {
        return generator.request(PLAYERS_ALL, new GenericType<>(){});
    }

    public HttpResponse<MilkyPlayer> saveMilkyPlayer(MilkyPlayer player) {
        return generator.request(PLAYERS_SAVE, player, MilkyPlayer.class);
    }

    public HttpResponse<MilkyPlayer> getMilkyPlayer(String uuid) {
        return generator.request(PLAYERS_GET, MilkyPlayer.class, uuid);
    }

    public HttpResponse deleteMilkyPlayer(String uuid) {
        return generator.request(PLAYERS_DELETE, uuid);
    }

    //CHAT FORMAT

    public HttpResponse<List<ChatFormat>> getChatFormats() {
        return generator.request(FORMATS_CHAT_ALL, new GenericType<>() {
        });
    }

    public HttpResponse<ChatFormat> saveChatFormat(ChatFormat format) {
        return generator.request(FORMATS_CHAT_SAVE, format, ChatFormat.class);
    }

    public HttpResponse<ChatFormat> getChatFormat(String id) {
        return generator.request(FORMATS_CHAT_GET, ChatFormat.class, id);
    }

    public HttpResponse deleteChatFormat(String id) {
        return generator.request(FORMATS_CHAT_DEL, id);
    }

    //JOIN LEAVE

    public HttpResponse<List<JoinLeaveFormat>> getJoinLeaveFormats() {
        return generator.request(FORMATS_JL_ALL, new GenericType<>() {
        });
    }

    public HttpResponse<JoinLeaveFormat> saveJoinLeaveFormat(JoinLeaveFormat format) {
        return generator.request(FORMATS_JL_SAVE, format, JoinLeaveFormat.class);
    }

    public HttpResponse<JoinLeaveFormat> getJoinLeaveFormat(String id) {
        return generator.request(FORMATS_JL_GET, JoinLeaveFormat.class, id);
    }

    public HttpResponse deleteJoinLeaveFormat(String id) {
        return generator.request(FORMATS_JL_DEL, id);
    }

    /*
    PER SERVER ROUTES
     */


    //HOMES

    public HttpResponse<Homes> getPlayerHomes(GameMode gameMode, String uuid) {
        return generator.request(HOMES_ALL, Homes.class, gameMode.getName(), uuid);
    }

    public HttpResponse<Homes> savePlayerHome(GameMode gameMode, String uuid, Home home) {
        return generator.request(HOME_SAVE, home, Homes.class, gameMode.getName(), uuid);
    }

    public HttpResponse<Home> getPlayerHome(GameMode gameMode, String uuid, String name) {
        return generator.request(HOME_GET, Home.class, gameMode.getName(), uuid, name);
    }

    public HttpResponse deletePlayerHome(GameMode gameMode, String uuid, String name) {
        return generator.request(HOME_DEL, gameMode.getName(), uuid, name);
    }

    //WARPS

    public HttpResponse<List<Warp>> getWarps(GameMode gameMode, String id) {
        return generator.request(WARPS_ALL, new GenericType<>() {
        }, gameMode.getName(), id);
    }

    public HttpResponse<Warp> saveWarp(GameMode gameMode, Warp warp) {
        return generator.request(WARPS_SAVE, warp, Warp.class, gameMode.getName());
    }

    public HttpResponse<Warp> getWarp(GameMode gameMode, String id) {
        return generator.request(WARPS_GET, Warp.class, gameMode.getName(), id);
    }

    public HttpResponse deleteWarp(GameMode gameMode, String id) {
        return generator.request(WARPS_DEL, gameMode.getName(), id);
    }

    public HttpResponseGenerator getGenerator() {
        return generator;
    }
}
