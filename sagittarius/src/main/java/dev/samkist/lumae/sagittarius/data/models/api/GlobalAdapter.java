package dev.samkist.lumae.sagittarius.data.models.api;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

import java.util.Map;

public abstract class GlobalAdapter<K, V extends MilkyModel> extends MilkyModel {
    private Map<K, V> modelMap;

    public GlobalAdapter() {
        super();
    }

    public GlobalAdapter(String uid, String scope, Map<K, V> modelMap) {
        super(uid, scope);
        this.modelMap = modelMap;
    }

    public V getModelByKey(K key) {
        return modelMap.get(key);
    }

    public Map<K, V> getModelMap() {
        return modelMap;
    }

    public void setModelMap(Map<K, V> modelMap) {
        this.modelMap = modelMap;
    }

    public void saveModel(K key, V model) {
        modelMap.put(key, model);
    }

    public V removeModel(K key) {
        return modelMap.remove(key);
    }

    public boolean hasModel(K key) {
        return modelMap.containsKey(key);
    }
}
