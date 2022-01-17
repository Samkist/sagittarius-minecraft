package dev.samkist.lumae.sagittarius.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.command.Command;
import dev.samkist.lumae.sagittarius.data.gamemode.GameMode;
import dev.samkist.lumae.sagittarius.data.managers.NetworkManager;
import dev.samkist.lumae.sagittarius.storage.DataProvider;
import dev.samkist.lumae.sagittarius.storage.RESTProvider;
import dev.samkist.lumae.sagittarius.storage.redis.RedisProvider;
import dev.samkist.lumae.sagittarius.storage.redis.compute.ComputeServiceProvider;
import dev.samkist.lumae.sagittarius.test.Tests;

public class SagittariusApi {
    private static final SagittariusApi instance = new SagittariusApi();
    private final Sagittarius sagittarius = Sagittarius.getSagittarius();
    private final NetworkManager networkManager;
    private final RESTProvider restProvider;
    private final RedisProvider redisProvider;
    private final ComputeServiceProvider computeServiceProvider;
    private final DataProvider dataProvider;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private SagittariusApi() {
        sagittarius.enable();
        restProvider = sagittarius.restProvider();
        redisProvider = sagittarius.redisProvider();
        computeServiceProvider = sagittarius.serviceProvider();
        dataProvider = new DataProvider();
        networkManager = new NetworkManager(dataProvider, this);
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

    public RESTProvider restProvider() {
        return restProvider;
    }

    public RedisProvider redisProvider() {
        return redisProvider;
    }

    public ComputeServiceProvider computeServiceProvider() {
        return computeServiceProvider;
    }

    public DataProvider dataProvider() {
        return dataProvider;
    }

}
