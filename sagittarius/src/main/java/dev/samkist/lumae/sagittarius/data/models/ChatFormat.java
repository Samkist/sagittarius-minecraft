package dev.samkist.lumae.sagittarius.data.models;

import java.util.Map;

public class ChatFormat extends Format{

    public static final String scope = "chat-formats";

    public ChatFormat() {
        super();
    }

    public ChatFormat(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, ChatFormat.scope, permission, formatStrings, priority);
    }
}
