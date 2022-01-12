package dev.samkist.lumae.sagittarius.api;

import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.test.Tests;

import java.util.Objects;

public class SagittariusApi {
    private static SagittariusApi instance = null;
    private Sagittarius sagittarius = Sagittarius.getSagittarius();

    private SagittariusApi() {

    }

    public static SagittariusApi generateApi() {
        if(Objects.isNull(instance)) {
            instance = new SagittariusApi();
            instance.initialize();
            return generateApi();
        } else {
            return instance;
        }
    }

    private void initialize() {
        sagittarius.enable();
    }

    public Tests getTests() {
        return sagittarius.getTests();
    }
}
