package dev.samkist.lumae.sagittarius.data.models;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class FormatBuilder<T extends Format, TBuilder extends ModelBuilder> extends ModelBuilder<T, TBuilder> {

    private String permission;
    private Map<String, String> formatStrings;
    private Integer priority;

    public FormatBuilder(Format format) {
        super(format);
    }

    public FormatBuilder() {
        super();
    }

    public TBuilder permission(@Nonnull String permission) {
        this.permission = permission;
        return (TBuilder) this;
    }

    public String permission() {
        return permission;
    }

    public TBuilder formatStrings(@Nonnull Map<String, String> formatStrings) {
        this.formatStrings = formatStrings;
        return (TBuilder) this;
    }

    public Map<String, String> formatStrings() {
        return formatStrings;
    }

    public TBuilder priority(@Nonnull Integer priority) {
        this.priority = priority;
        return (TBuilder) this;
    }

    public Integer priority() {
        return priority;
    }

}
