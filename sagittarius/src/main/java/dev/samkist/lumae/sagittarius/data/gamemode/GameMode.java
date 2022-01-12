package dev.samkist.lumae.sagittarius.data.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

import java.util.Arrays;

public enum GameMode {
    GLOBAL("global"),
    SURVIVAL("survival");

    private String name;

    GameMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GameMode getByName(String name) {
        return Arrays.asList(values()).stream().filter(g -> g.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public static String getMilkyColl(GameMode gamemode, MilkyModel model) {
        if(gamemode.equals(GLOBAL)) {
            return model.getScope();
        } else {
            return String.format("%s_%s", gamemode.getName(), model.getScope());
        }
    }

    public static String getMilkyColl(String gamemode, String model) {
        if(gamemode.equalsIgnoreCase("global")) {
            return model;
        } else {
            return String.format("%s_%s", gamemode, model);
        }
    }
}
