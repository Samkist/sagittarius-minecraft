package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Home;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Homes;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import kong.unirest.*;

import java.util.List;

import static dev.samkist.lumae.sagittarius.storage.Routes.*;

@SuppressWarnings("unchecked")
public class RESTManager {

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

    public RESTManager(Sagittarius plugin, Gson gson, String apiHost) {
        this.plugin = plugin;
        this.apiHost = apiHost;
        this.generator = new HttpResponseGenerator(gson, apiHost);
    }

    public String status() {
        return Unirest.get(apiHost)
                .asString().getBody();
    }

    public HttpResponse<List<MilkyPlayer>> getAllPlayers() {
        return generator.request(PLAYERS_ALL, new GenericType<>(){});
    }

    public HttpResponse<MilkyPlayer> saveMilkyPlayer(MilkyPlayer player) {
        return generator.request(PLAYERS_NEW, player, MilkyPlayer.class);
    }

    public HttpResponse<MilkyPlayer> getMilkyPlayer(String uuid) {
        return generator.request(PLAYERS_GET, MilkyPlayer.class, uuid);
    }

    public HttpResponse deleteMilkyPlayer(String uuid) {
        return generator.request(PLAYERS_DELETE, uuid);
    }

    public HttpResponse<Homes> getPlayerHomes(String uuid) {
        return null;
    }

    public HttpResponse<Homes> addPlayerHome(String uuid, Home home) {
        return null;
    }

    public HttpResponse<Home> getPlayerHome(String uuid, String name) {
        /*return Unirest.get(httpRequest(PLAYERS_GET_HOME, uuid, name))
                .routeParam("uuid", uuid)
                .routeParam("name", name)
                .asObject(Home.class);*/
        return null;
    }

    public HttpResponse deletePlayerHome(String uuid, String name) {
        return null;
    }

    public HttpResponse<List<ChatFormat>> getChatFormats() {
        return null;
    }

    public HttpResponse<ChatFormat> saveChatFormat(ChatFormat format) {
        return null;
    }

    public HttpResponse<ChatFormat> getChatFormat(String id) {
        return null;
    }

    public HttpResponse deleteChatFormat(String id) {
        return null;
    }
}
