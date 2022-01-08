package dev.samkist.lumae.sagittarius.storage;

public class RESTManager {

    private String apiHost;
    public static final String PLAYERS = "/players";
    public static final String HOMES = "/players/{uuid}/homes";
    public static final String FORMATS_CHAT = "/formats/chat";
    public static final String FORMATS_JL = "/formats/join-leave";
    public static final String ECONOMY = "/economy";

    public RESTManager(String apiHost) {
        this.apiHost = apiHost;
    }
}
