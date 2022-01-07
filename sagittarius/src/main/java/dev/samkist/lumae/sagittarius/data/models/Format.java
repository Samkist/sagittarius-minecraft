package dev.samkist.lumae.sagittarius.data.models;

import java.util.Map;

public abstract class Format extends MilkyModel {
    final String permission;
    final Map<String, String> formatStrings;
    final Integer priority;

    protected Format(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, scope);
        this.permission = permission;
        this.formatStrings = formatStrings;
        this.priority = priority;
    }
}
