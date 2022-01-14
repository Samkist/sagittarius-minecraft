package dev.samkist.lumae.sagittarius.api;

import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.storage.DataManager;
import dev.samkist.lumae.sagittarius.test.Tests;

import java.util.Objects;

public class SagittariusApi {
    private static SagittariusApi instance = null;
    private Sagittarius sagittarius = Sagittarius.getSagittarius();
    private DataManager dataManager;

    private SagittariusApi() {
        initialize();
    }

    public static SagittariusApi generateApi() {
        if(Objects.isNull(instance)) {
            instance = new SagittariusApi();
            return generateApi();
        } else {
            return instance;
        }
    }

    public GameMode currentGameMode() {
        return GameMode.SURVIVAL;
    }

    private void initialize() {
        sagittarius.enable();
        dataManager = new DataManager(sagittarius.getRestManager());
    }

    public Tests getTests() {
        return sagittarius.getTests();
    }
}
