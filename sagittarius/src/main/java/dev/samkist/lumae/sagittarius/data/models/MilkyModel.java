package dev.samkist.lumae.sagittarius.data.models;

import org.springframework.data.annotation.Id;

public abstract class MilkyModel {

    @Id
    public String id;
    public String scope;

    public MilkyModel(String id, String scope) {
        this.id = id;
        this.scope = scope;
    }

}
