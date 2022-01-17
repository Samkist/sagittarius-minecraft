package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Home;
import dev.samkist.lumae.sagittarius.data.models.gamemode.Warp;
import dev.samkist.lumae.sagittarius.data.models.global.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.global.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.samkist.lumae.sagittarius.storage.RESTProvider.*;
import static dev.samkist.lumae.sagittarius.storage.RequestType.*;

public enum Routes {

    //GLOBAL ROUTES

    PLAYERS_ALL(PLAYERS, GET),
    PLAYERS_SAVE(PLAYERS, POST),
    PLAYERS_GET(PLAYERS + "/{uuid}", GET, new TypeToken<MilkyPlayer>(){}),
    PLAYERS_DELETE(PLAYERS + "/{uuid}", DEL),

    FORMATS_CHAT_ALL(FORMATS_CHAT, GET),
    FORMATS_CHAT_SAVE(FORMATS_CHAT, POST),
    FORMATS_CHAT_GET(FORMATS_CHAT + "/{id}", GET, new TypeToken<ChatFormat>() {}),
    FORMATS_CHAT_DEL(FORMATS_CHAT + "/{id}", DEL),

    FORMATS_JL_ALL(FORMATS_JL, GET),
    FORMATS_JL_SAVE(FORMATS_JL, POST),
    FORMATS_JL_GET(FORMATS_JL + "/{id}", GET, new TypeToken<JoinLeaveFormat>() {}),
    FORMATS_JL_DEL(FORMATS_JL + "/{id}", DEL),

    // PER SERVER ROUTES

    HOMES_ALL(HOMES, GET),
    HOME_SAVE(HOMES, POST), //IMPORTANT: RETURNS HOMES NOT HOME
    HOME_GET(HOMES + "/{name}", GET, new TypeToken<Home>() {}),
    HOME_DEL(HOMES + "/{name}", DEL),

    WARPS_ALL(WARPS, GET),
    WARPS_SAVE(WARPS, POST),
    WARPS_GET(WARPS + "/{id}", GET, new TypeToken<Warp>() {}),
    WARPS_DEL(WARPS + "/{id}", DEL);

    private String route;
    private List<String> variables;
    private RequestType requestType;
    private TypeToken<?> type;

    Routes(String route, RequestType requestType) {
        this(route, requestType, null);
    }

    Routes(String route, RequestType requestType, TypeToken<?> type) {
        this.route = route;
        this.variables = generateVariables(route);
        this.requestType = requestType;
    }

    public String route() {
        return route;
    }

    public List<String> parameters() {
        return variables;
    }

    public RequestType requestType() {
        return requestType;
    }

    public TypeToken<?> getType() {
        return type;
    }

    private List<String> generateVariables(String route) {
        ArrayList<String> toAdd = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(route);
        int startingGroup = 1;
        while(matcher.find()) {
            toAdd.add(matcher.group(startingGroup++));
        }
        return toAdd;
    }

    public static <T> Routes getLoadRoutes(TypeToken<T> type) {
        return List.of(values()).stream().filter(r -> Objects.nonNull(r.type))
                .filter(r -> r.type.equals(type)).toList().get(0);
    }
}
