package dev.samkist.lumae.sagittarius.data.models;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;

public abstract class MilkyModel<T extends MilkyModel> {

    @Id
    public String uid;
    @JsonIgnore
    public String scope;

    public MilkyModel(String uid, String scope) {
        this.uid = uid;
        this.scope = scope;
    }

    public MilkyModel() {

    }

    public String uid() {
        return uid;
    }

    public T uid(String uid) {
        this.uid = uid;
        return (T) this;
    }

    public String scope() {
        return scope;
    }

    public T scope(String scope) {
        this.scope = scope;
        return (T) this;
    }
}
