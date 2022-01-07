package dev.samkist.lumae.sagittarius.data.models;

import java.util.Map;

public class ChatFormat extends Format{
    protected ChatFormat(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, "chatFormats", permission, formatStrings, priority);
    }
}
