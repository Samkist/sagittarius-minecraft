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
    PLAYERS_NEW(PLAYERS, POST),
    PLAYERS_GET(PLAYERS + "/{uuid}", GET),
    PLAYERS_DELETE(PLAYERS + "/{uuid}", DEL),

    FORMATS_CHAT_ALL(FORMATS_CHAT, GET),
    FORMATS_NEW_CHAT(FORMATS_CHAT, POST),
    FORMATS_GET_CHAT(FORMATS_CHAT + "/{id}", GET),
    FORMATS_DEL_CHAT(FORMATS_CHAT + "/{id}", DEL),

    FORMATS_JOIN_LEAVE_ALL(FORMATS_JL, GET),
    FORMATS_NEW_JOIN_LEAVE(FORMATS_JL, POST),
    FORMATS_GET_JOIN_LEAVE(FORMATS_JL + "/{id}", GET),
    FORMATS_DEL_JOIN_LEAVE(FORMATS_JL + "/{id}", DEL),

    // PER SERVER ROUTES

    PLAYERS_HOMES(HOMES, GET),
    PLAYERS_NEW_HOME(HOMES, POST),
    PLAYERS_GET_HOME(HOMES + "/{name}", GET),
    PLAYERS_DEL_HOME(HOMES + "/{name}", DEL),

    WARPS_ALL(WARPS, GET),
    WARPS_NEW(WARPS, POST),
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
