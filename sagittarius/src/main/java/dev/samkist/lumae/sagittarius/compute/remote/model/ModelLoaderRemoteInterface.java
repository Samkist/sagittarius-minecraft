package dev.samkist.lumae.sagittarius.compute.remote.model;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

import java.util.Arrays;
import java.util.List;

public interface ModelLoaderRemoteInterface {
    default <T extends MilkyModel<T>> T loadModel(TypeToken<T> type, String... parameters) {
        return loadModel(type, Arrays.asList(parameters));
    }

    <T extends MilkyModel<T>> T loadModel(TypeToken<T> type, List<String> parameters);
}
