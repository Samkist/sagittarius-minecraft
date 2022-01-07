package dev.samkist.lumae.sagittarius.data.models;

import javax.annotation.Nonnull;

public abstract class ModelBuilder<T extends MilkyModel, TBuilder extends ModelBuilder> {
    private String scope;
    private String id;

    public abstract T build();

    public ModelBuilder() {

    }

    public ModelBuilder(@Nonnull MilkyModel model) {
        this.scope = model.scope;
        this.id = model.id;
    }

    public TBuilder scope(@Nonnull String scope) {
        this.scope = scope;
        return (TBuilder) this;
    }

    public String scope() {
        return this.scope;
    }

    public TBuilder id(@Nonnull String id) {
        this.id = id;
        return (TBuilder) this;
    }

    public String id() {
        return id;
    }


}
