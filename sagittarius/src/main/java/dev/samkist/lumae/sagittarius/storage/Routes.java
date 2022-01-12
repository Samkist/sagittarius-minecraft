package dev.samkist.lumae.sagittarius.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.samkist.lumae.sagittarius.storage.RESTManager.*;
import static dev.samkist.lumae.sagittarius.storage.RequestType.*;

public enum Routes {

    //GLOBAL ROUTES

    PLAYERS_ALL(PLAYERS, GET),
    PLAYERS_SAVE(PLAYERS, POST),
    PLAYERS_GET(PLAYERS + "/{uuid}", GET),
    PLAYERS_DELETE(PLAYERS + "/{uuid}", DEL),

    FORMATS_CHAT_ALL(FORMATS_CHAT, GET),
    FORMATS_CHAT_SAVE(FORMATS_CHAT, POST),
    FORMATS_CHAT_GET(FORMATS_CHAT + "/{id}", GET),
    FORMATS_CHAT_DEL(FORMATS_CHAT + "/{id}", DEL),

    FORMATS_JL_ALL(FORMATS_JL, GET),
    FORMATS_JL_SAVE(FORMATS_JL, POST),
    FORMATS_JL_GET(FORMATS_JL + "/{id}", GET),
    FORMATS_JL_DEL(FORMATS_JL + "/{id}", DEL),

    // PER SERVER ROUTES

    HOMES_ALL(HOMES, GET),
    HOME_SAVE(HOMES, POST), //IMPORTANT: RETURNS HOMES NOT HOME
    HOME_GET(HOMES + "/{name}", GET),
    HOME_DEL(HOMES + "/{name}", DEL),

    WARPS_ALL(WARPS, GET),
    WARPS_SAVE(WARPS, POST),
    WARPS_GET(WARPS + "/{id}", GET),
    WARPS_DEL(WARPS + "/{id}", DEL);

    private String route;
    private List<String> variables;
    private RequestType requestType;

    Routes(String route, RequestType requestType) {
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        this.route = route;
        this.variables = generateVariables(route);
        this.requestType = requestType;
    }

    public String route() {
        return route;
    }

    public List<String> variables() {
        return variables;
    }

    public RequestType requestType() {
        return requestType;
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
}
