/**
 * File created by Samuel Pizette on 14 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;

import java.util.List;

public class Server extends MilkyModel<Server> {
    private static final String scope = "servers";
    private String serverTitle;
    private Integer maxPlayers;
    private Integer currentPlayers;
    private String ip;
    private Integer port;
    private Integer ping;
    private Long startTime;
    private GameMode gameMode;
    private Double tps;
    private List<MilkyPlayer> onlineStaff;
    private List<MilkyPlayer> onlinePlayers;

    public Server(String uid,
                  String serverTitle,
                  Integer maxPlayers,
                  Integer currentPlayers,
                  String ip,
                  Integer port,
                  Integer ping,
                  Long startTime,
                  GameMode gameMode,
                  Double tps,
                  List<MilkyPlayer> onlineStaff,
                  List<MilkyPlayer> onlinePlayers
    ) {
        super(uid, scope);
        this.serverTitle = serverTitle;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = currentPlayers;
        this.ip = ip;
        this.port = port;
        this.ping = ping;
        this.startTime = startTime;
        this.gameMode = gameMode;
        this.tps = tps;
        this.onlineStaff = onlineStaff;
        this.onlinePlayers = onlinePlayers;
    }

    public Server() {

    }


 }
