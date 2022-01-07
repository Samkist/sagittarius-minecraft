package dev.samkist.lumae.sagittarius.data.models;


import java.util.Map;

public class JoinLeaveFormat extends Format {

    public static final String scope = "join-leave-formats";

    JoinLeaveFormat(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, scope, permission, formatStrings, priority);
    }
}
