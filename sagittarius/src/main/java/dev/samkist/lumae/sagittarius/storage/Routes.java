package dev.samkist.lumae.sagittarius.storage;

import kong.unirest.Unirest;

import java.util.List;

import static dev.samkist.lumae.sagittarius.storage.Requests.*;

public enum Routes {

    PLAYERS_ALL("/players", List.of(), GET),
    PLAYERS_NEW("/players", List.of(), POST),
    PLAYERS_GET("/players/{uuid}", List.of("uuid"), GET),
    PLAYERS_DELETE("/players/{uuid}", List.of("uuid"), DELETE);

    private String route;

    Routes(String route, List<String> variables, Requests request) {
        this.route = route;
    }

}
