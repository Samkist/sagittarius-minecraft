package dev.samkist.lumae.sagittarius.data.models.global;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

import java.util.Map;

public abstract class Format<T extends Format> extends MilkyModel<T> {
    private String permission;
    private Map<String, String> formatStrings;
    private Integer priority;

    public Format(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, scope);
        this.permission = permission;
        this.formatStrings = formatStrings;
        this.priority = priority;
    }

    public Format() {
        super();
    }

    public String permission() {
        return permission;
    }

    public T permission(String permission) {
        this.permission = permission;
        return (T) this;
    }

    public Map<String, String> formatStrings() {
        return formatStrings;
    }

    public T formatStrings(Map<String, String> formatStrings) {
        this.formatStrings = formatStrings;
        return (T) this;
    }

    public Integer priority() {
        return priority;
    }

    public T priority(Integer priority) {
        this.priority = priority;
        return (T) this;
    }
}
