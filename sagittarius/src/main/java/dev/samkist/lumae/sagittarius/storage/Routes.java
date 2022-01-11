package dev.samkist.lumae.sagittarius.storage;

import java.util.List;

import static dev.samkist.lumae.sagittarius.storage.RESTManager.*;
import static dev.samkist.lumae.sagittarius.storage.RequestType.*;

public enum Routes {

    PLAYERS_ALL(PLAYERS, List.of(), GET),
    PLAYERS_NEW(PLAYERS, List.of(), POST),
    PLAYERS_GET(PLAYERS + "/{uuid}", List.of("uuid"), GET),
    PLAYERS_DELETE(PLAYERS + "/{uuid}", List.of("uuid"), DEL),

    PLAYERS_HOMES(HOMES, List.of("uuid"), GET),
    PLAYERS_NEW_HOME(HOMES, List.of("uuid"), POST),
    PLAYERS_GET_HOME(HOMES + "/{name}", List.of("uuid", "name"), GET),
    PLAYERS_DEL_HOME(HOMES + "/{name}", List.of("uuid", "name"), DEL),

    FORMATS_CHAT_ALL(FORMATS_CHAT, List.of(), GET),
    FORMATS_NEW_CHAT(FORMATS_CHAT, List.of(), POST),
    FORMATS_GET_CHAT(FORMATS_CHAT + "/{id}", id, GET),
    FORMATS_DEL_CHAT(FORMATS_CHAT + "/{id}", id, DEL),

    FORMATS_JOIN_LEAVE_ALL(FORMATS_JL, List.of(), GET),
    FORMATS_NEW_JOIN_LEAVE(FORMATS_JL, List.of(), POST),
    FORMATS_GET_JOIN_LEAVE(FORMATS_JL + "/{id}", id, GET),
    FORMATS_DEL_JOIN_LEAVE(FORMATS_JL + "/{id}", id, DEL),

    WARPS_ALL(WARPS, List.of(), GET),
    WARPS_NEW(WARPS, List.of(), POST),
    WARPS_GET(WARPS + "/{id}", id, GET),
    WARPS_DEL(WARPS + "/{id}", id, DEL);

    private String route;
    private List<String> variables;
    private RequestType requestType;

    Routes(String route, List<String> variables, RequestType requestType) {
        this.route = route;
        this.variables = variables;
        this.requestType = requestType;
    }

    public String getRoute() {
        return route;
    }

    public List<String> getVariables() {
        return variables;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
