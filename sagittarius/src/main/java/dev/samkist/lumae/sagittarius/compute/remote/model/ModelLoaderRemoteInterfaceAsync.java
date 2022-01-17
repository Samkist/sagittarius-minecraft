package dev.samkist.lumae.sagittarius.compute.remote.model;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

import java.util.Arrays;
import java.util.List;

@RRemoteAsync(ModelLoaderRemoteInterface.class)
public interface ModelLoaderRemoteInterfaceAsync {
    default <T extends MilkyModel<T>> RFuture<T> loadModel(TypeToken<T> type, String... parameters) {
        return loadModel(type, Arrays.asList(parameters));
    }

    <T extends MilkyModel<T>> RFuture<T> loadModel(TypeToken<T> type, List<String> parameters);
}
