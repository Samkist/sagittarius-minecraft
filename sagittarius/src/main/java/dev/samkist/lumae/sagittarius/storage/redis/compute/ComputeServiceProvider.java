/**
 * File created by Samuel Pizette on 16 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.storage.redis.compute;

import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import dev.samkist.lumae.sagittarius.compute.model.ModelLoader;
import dev.samkist.lumae.sagittarius.compute.remote.model.ModelLoaderRemoteInterface;
import dev.samkist.lumae.sagittarius.compute.remote.model.RemoteModelLoaderImpl;
import dev.samkist.lumae.sagittarius.storage.RESTProvider;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComputeServiceProvider {

    private final SagittariusApi api;
    private final RedissonClient client;
    private final RESTProvider rest;
    private final ModelLoader modelLoader;

    public ComputeServiceProvider(RedissonClient client, RESTProvider rest) {
        this.api = SagittariusApi.instance();
        this.client = client;
        this.rest = rest;
        this.modelLoader = api.dataProvider().modelLoader();
        registerModelLoaderService();
    }

    private void registerModelLoaderService() {
        RRemoteService remoteService = client.getRemoteService();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        ModelLoaderRemoteInterface modelImpl = new RemoteModelLoaderImpl(modelLoader);
        remoteService.register(ModelLoaderRemoteInterface.class, modelImpl, 5, executor);
    }
}
