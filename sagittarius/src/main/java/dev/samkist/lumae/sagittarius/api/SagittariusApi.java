package dev.samkist.lumae.sagittarius.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.managers.NetworkManager;
import dev.samkist.lumae.sagittarius.data.managers.PlayerManager;
import dev.samkist.lumae.sagittarius.data.models.global.MilkyPlayer;
import dev.samkist.lumae.sagittarius.storage.DataManager;
import dev.samkist.lumae.sagittarius.test.Tests;

import java.util.Objects;

public class SagittariusApi {
    private static final SagittariusApi instance = new SagittariusApi();
    private final Sagittarius sagittarius = Sagittarius.getSagittarius();
    private final DataManager dataManager;
    private final NetworkManager networkManager;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private SagittariusApi() {
        sagittarius.enable();
        dataManager = new DataManager(sagittarius.getRestManager());
        networkManager = new NetworkManager(dataManager, this);
    }

    public static SagittariusApi instance() {
        return instance;
    }

    public GameMode currentGameMode() {
        return GameMode.SURVIVAL;
    }


    public Tests getTests() {
        return sagittarius.getTests();
    }

    public Gson gson() {
        return gson;
    }
}
