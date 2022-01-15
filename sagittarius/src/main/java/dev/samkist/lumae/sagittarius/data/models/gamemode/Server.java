/**
 * File created by Samuel Pizette
 * <p>
 * on 14 January 2022
 **/

package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class Server extends MilkyModel<Server> {
    private String serverTitle;
    private Integer maxPlayers;
    private Integer currentPlayers;
    private Long startTime;
    private Double tps;
    private GameMode gameMode;

}
