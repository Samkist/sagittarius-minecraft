package dev.samkist.lumae.sagittarius;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.storage.RESTProvider;
import dev.samkist.lumae.sagittarius.storage.redis.RedisProvider;
import dev.samkist.lumae.sagittarius.storage.redis.compute.ComputeServiceProvider;
import dev.samkist.lumae.sagittarius.test.Tests;


public class Sagittarius {

    public final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private SagittariusApi sagittariusApi;
    private RESTProvider restProvider;
    private RedisProvider redisProvider;
    private ComputeServiceProvider computeServiceProvider;
    private Tests tests;
    private static final String apiRoute = "http://198.55.58.21";
    public static final long LAST_START_TIME = System.currentTimeMillis();
    private static final Sagittarius instance = new Sagittarius();
    private static boolean ran = false;

    private Sagittarius() {

    }

    public static Sagittarius getSagittarius() {
        if(ran) {
            return null;
        } else {
            ran = true;
        }
        return instance;
    }

    public void enable() {
        restProvider = new RESTProvider(this, gson, apiRoute);
        redisProvider = new RedisProvider("redis://10.0.0.3", "9Tdn-+2mpgD=s9QZ");

        tests = new Tests(this, restProvider);
    }

    public void disable() {

    }

    public RESTProvider restProvider() {
        return restProvider;
    }

    public RedisProvider redisProvider() {
        return redisProvider;
    }

    public ComputeServiceProvider serviceProvider() {
        return computeServiceProvider;
    }

    public Tests getTests() {
        return tests;
    }
}
