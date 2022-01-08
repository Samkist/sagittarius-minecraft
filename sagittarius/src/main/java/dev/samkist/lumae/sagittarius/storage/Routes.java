package dev.samkist.lumae.sagittarius.storage;

import java.util.List;

import static dev.samkist.lumae.sagittarius.storage.RESTManager.*;

public enum Routes {

    PLAYERS_ALL(PLAYERS, List.of()),
    PLAYERS_NEW(PLAYERS, List.of()),
    PLAYERS_GET(PLAYERS + "/{uuid}", List.of("uuid")),
    PLAYERS_DELETE(PLAYERS + "/{uuid}", List.of("uuid")),

    PLAYERS_HOMES(HOMES, List.of("uuid")),
    PLAYERS_NEW_HOME(HOMES, List.of("uuid")),
    PLAYERS_GET_HOME(HOMES + "/{name}", List.of("uuid", "name")),
    PLAYERS_DEL_HOME(HOMES + "/{name}", List.of("uuid", "name")),

    FORMATS_CHAT_ALL(FORMATS_CHAT, List.of()),
    FORMATS_NEW_CHAT(FORMATS_CHAT, List.of()),
    FORMATS_GET_CHAT(FORMATS_CHAT + "/{id}", id),
    FORMATS_DEL_CHAT(FORMATS_CHAT + "/{id}", id),

    FORMATS_JOIN_LEAVE_ALL(FORMATS_JL, List.of()),
    FORMATS_NEW_JOIN_LEAVE(FORMATS_JL, List.of()),
    FORMATS_GET_JOIN_LEAVE(FORMATS_JL + "/{id}", id),
    FORMATS_DEL_JOIN_LEAVE(FORMATS_JL + "/{id}", id),

    WARPS_ALL(WARPS, List.of()),
    WARPS_NEW(WARPS, List.of()),
    WARPS_GET(WARPS + "/{id}", id),
    WARPS_DEL(WARPS + "/{id}", id);

    private String route;
    private List<String> variables;

    Routes(String route, List<String> variables) {
        this.route = route;
        this.variables = variables;
    }

    public String getPopulatedRoute(List<String> values) {
        String route = getRoute();
        for(int i = 0; i < variables.size(); ++i) {
            route.replace(String.format("{%s}", variables.get(i)), values.get(i));
        }
        return route;
    }

    public String getRoute() {
        return route;
    }

    public List<String> getVariables() {
        return variables;
    }
}
