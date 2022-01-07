package dev.samkist.lumae.sagittarius.data.models;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;

public abstract class MilkyModel {

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
