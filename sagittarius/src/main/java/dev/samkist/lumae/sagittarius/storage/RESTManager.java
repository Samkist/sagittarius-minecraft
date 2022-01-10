package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.Home;
import dev.samkist.lumae.sagittarius.data.models.Homes;
import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static dev.samkist.lumae.sagittarius.storage.Routes.*;

@SuppressWarnings("unchecked")
public class RESTManager {

    private final Sagittarius plugin;
    private final Gson gson;
    private final String apiHost;
    public static final String PLAYERS = "/players";
    public static final String HOMES = "/players/{uuid}/homes";
    public static final String FORMATS_CHAT = "/formats/chat";
    public static final String FORMATS_JL = "/formats/join-leave";
    public static final String ECONOMY = "/economy";
    public static final String WARPS = "/warps";
    public static final List<String> id = List.of("id");

    public RESTManager(Sagittarius plugin, Gson gson, String apiHost) {
        this.plugin = plugin;
        this.gson = gson;
        this.apiHost = apiHost;
    }

    public String status() {
        return Unirest.get(apiHost)
                .asString().getBody();
    }

    public HttpResponse<List<MilkyPlayer>> getAllPlayers() {
        return Unirest.get(httpRequest(PLAYERS_ALL))
                .asObject(new GenericType<>() {
                });
    }

    public HttpResponse<MilkyPlayer> saveMilkyPlayer(MilkyPlayer player) {
        return Unirest.post(httpRequest(PLAYERS_NEW))
                .body(gson.toJsonTree(player))
                .asObject(MilkyPlayer.class);/*.ifFailure(failure ->
                        plugin.getLogger().warning(
                                String.format("Critical error saving Player: %s:%s", player.getLastUsername(), player.getUid())
                        ));*/
    }

    public HttpResponse<MilkyPlayer> getMilkyPlayer(String uuid) {
        return Unirest.get(httpRequest(PLAYERS_GET))
                .routeParam("uuid", uuid)
                .asObject(MilkyPlayer.class);
    }

    public HttpResponse deleteMilkyPlayer(String uuid) {
        return Unirest.delete(httpRequest(PLAYERS_DELETE))
                .routeParam("uuid", uuid)
                .asEmpty();
    }

    public HttpResponse<Homes> getPlayerHomes(String uuid) {
        return Unirest.get(httpRequest(PLAYERS_HOMES, uuid))
                .routeParam("uuid", uuid)
                .asObject(Homes.class);
    }

    public HttpResponse<Homes> addPlayerHome(String uuid, Home home) {
        return Unirest.post(httpRequest(PLAYERS_NEW_HOME, uuid))
                .routeParam("uuid", uuid)
                .body(gson.toJsonTree(home))
                .asObject(Homes.class);/*.ifFailure(failure ->
                        plugin.getLogger().warning(
                                String.format("Critical error saving Player Home: %s:%s", home.uid, uuid)
                        ));*/
    }

    public HttpResponse<Home> getPlayerHome(String uuid, String name) {
        return Unirest.get(httpRequest(PLAYERS_GET_HOME, uuid, name))
                .routeParam("uuid", uuid)
                .routeParam("name", name)
                .asObject(Home.class);
    }

    public HttpResponse deletePlayerHome(String uuid, String name) {
        return Unirest.delete(httpRequest(PLAYERS_DEL_HOME, uuid, name))
                .routeParam("uuid", uuid)
                .routeParam("name", name)
                .asEmpty();
    }

    public HttpResponse<List<ChatFormat>> getChatFormats() {
        return Unirest.get(httpRequest(FORMATS_CHAT_ALL))
                .asObject(new GenericType<List<ChatFormat>>(){});
    }

    public HttpResponse<ChatFormat> saveChatFormat(ChatFormat format) {
        return Unirest.post(httpRequest(FORMATS_NEW_CHAT))
                .body(gson.toJsonTree(format))
                .asObject(ChatFormat.class);
    }

    public HttpResponse<ChatFormat> getChatFormat(String id) {
        return Unirest.get(httpRequest(FORMATS_GET_CHAT, id))
                        .routeParam("id", id)
                        .asObject(ChatFormat.class);
    }

    public HttpResponse deleteChatFormat(String id) {
        return Unirest.delete(httpRequest(FORMATS_DEL_CHAT, id))
                .routeParam("id", id)
                .asEmpty();
    }



    public String httpRequest(String route) {
        return apiHost + route;
    }

    public String httpRequest(Routes route) {
        return apiHost + route.getRoute();
    }

    public String httpRequest(Routes route, List<String> values) {
        return apiHost + route.getPopulatedRoute(values);
    }

    public String httpRequest(Routes route, String... values) {
        return httpRequest(route, Arrays.asList(values));
    }
}
