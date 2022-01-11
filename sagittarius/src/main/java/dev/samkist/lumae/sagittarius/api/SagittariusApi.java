package dev.samkist.lumae.sagittarius.api;

import java.util.Objects;

public class SagittariusApi {
    private static SagittariusApi instance = null;

    private SagittariusApi() {

    }

    public static SagittariusApi generateApi() {
        if(Objects.isNull(instance)) {
            instance = new SagittariusApi();
            return generateApi();
        } else {
            return instance;
        }
    }
}
