package dev.samkist.lumae.sagittarius.data.models;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;

public abstract class MilkyModel {

    @Id
    public String id;
    @JsonIgnore
    public String scope;

    public MilkyModel(String id, String scope) {
        this.id = id;
        this.scope = scope;
    }

    public MilkyModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
