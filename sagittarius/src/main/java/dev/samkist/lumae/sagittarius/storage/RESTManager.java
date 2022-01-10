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

    public List<MilkyPlayer> getAllPlayers() {
        return Unirest.get(httpRequest(PLAYERS_ALL))
                .asObject(new GenericType<List<MilkyPlayer>>(){})
                .getBody();
    }

    public void saveMilkyPlayer(MilkyPlayer player) {
        Unirest.post(httpRequest(PLAYERS_NEW))
                .body(gson.toJsonTree(player))
                .asEmpty().ifFailure(failure ->
                        plugin.getLogger().warning(
                                String.format("Critical error saving Player: %s:%s", player.getLastUsername(), player.getUid())
                        ));
    }

    public Optional<MilkyPlayer> getMilkyPlayer(String uuid) {
        return Optional.ofNullable(
                Unirest.get(httpRequest(PLAYERS_GET, uuid))
                        .asObject(MilkyPlayer.class)
                        .getBody()
        );
    }

    public void deleteMilkyPlayer(String uuid) {
        Unirest.delete(httpRequest(PLAYERS_DELETE, uuid)).asEmpty().ifFailure(failure ->
                plugin.getLogger().warning(
                        String.format("Critical error deleting Player: %s", uuid)
                ));
    }

    public Optional<Homes> getPlayerHomes(String uuid) {
        return Optional.ofNullable(Unirest.get(httpRequest(PLAYERS_HOMES, uuid))
                .asObject(Homes.class)
                .getBody());
    }

    public Optional<Home> addPlayerHome(String uuid, Home home) {
        HttpResponse result = Unirest.post(httpRequest(PLAYERS_NEW_HOME, uuid))
                .body(gson.toJsonTree(home))
                .asEmpty();/*.ifFailure(failure ->
                        plugin.getLogger().warning(
                                String.format("Critical error saving Player Home: %s:%s", home.uid, uuid)
                        ));*/
        if(result.isSuccess()) {
            return Optional.ofNullable(home);
        } else {
            plugin.getLogger().warning(
                    String.format("Critical error saving Player Home: %s:%s", home.uid, uuid)
            );
            return Optional.empty();
        }
    }

    public Optional<Home> getPlayerHome(String uuid, String name) {
        return Optional.ofNullable(
                Unirest.get(httpRequest(PLAYERS_GET_HOME, uuid, name))
                        .asObject(Home.class)
                        .getBody()
        );
    }

    public void deletePlayerHome(String uuid, String name) {
        Unirest.delete(httpRequest(PLAYERS_DEL_HOME, uuid, name)).asEmpty().ifFailure(failure ->
                plugin.getLogger().warning(
                        String.format("Critical error deleting Player Home: %s:%s", name, uuid)
                ));
    }

    public List<ChatFormat> getChatFormats() {
        return Unirest.get(httpRequest(FORMATS_CHAT_ALL))
                .asObject(new GenericType<List<ChatFormat>>(){})
                .getBody();
    }

    public void saveChatFormat(ChatFormat format) {
        Unirest.post(httpRequest(FORMATS_NEW_CHAT))
                .body(gson.toJsonTree(format))
                .asEmpty().ifFailure(failure ->
                        plugin.getLogger().warning(
                                String.format("Critical error saving Format: %s", format.getUid())
                        ));
    }

    public Optional<ChatFormat> getChatFormat(String id) {
        return Optional.ofNullable(
                Unirest.get(httpRequest(FORMATS_GET_CHAT, id))
                        .asObject(ChatFormat.class)
                        .getBody()
        );
    }

    public void deleteChatFormat(String id) {
        Unirest.delete(httpRequest(FORMATS_DEL_CHAT, id))
                .asEmpty().ifFailure(failure ->
                        plugin.getLogger().warning(
                                String.format("Critical error deleting ChatFormat: %s", id)
                        ));
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
