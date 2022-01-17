/**
 * File created by Samuel Pizette on 16 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.compute.remote.model;

import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import dev.samkist.lumae.sagittarius.compute.model.ModelLoader;

import java.util.List;

public class RemoteModelLoaderImpl implements ModelLoaderRemoteInterface {

    private final ModelLoader modelLoader;

    public RemoteModelLoaderImpl(ModelLoader modelLoader) {
        this.modelLoader = modelLoader;
    }

    @Override
    public <T extends MilkyModel<T>> T loadModel(TypeToken<T> type, List<String> parameters) {
        return modelLoader.load(type, parameters);
    }
}
