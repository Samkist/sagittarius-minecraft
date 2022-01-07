package dev.samkist.lumae.sagittarius.data.models;

public abstract class MilkyModel {
    public final String id;
    public final String scope;

    public MilkyModel(String id, String scope) {
        this.id = id;
        this.scope = scope;
    }

}
