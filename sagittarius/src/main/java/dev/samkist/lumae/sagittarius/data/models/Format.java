package dev.samkist.lumae.sagittarius.data.models;

import java.util.Map;

public abstract class Format extends MilkyModel {
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Map<String, String> getFormatStrings() {
        return formatStrings;
    }

    public void setFormatStrings(Map<String, String> formatStrings) {
        this.formatStrings = formatStrings;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
