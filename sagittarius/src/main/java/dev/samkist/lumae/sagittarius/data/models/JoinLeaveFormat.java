package dev.samkist.lumae.sagittarius.data.models;


import java.util.Map;

public class JoinLeaveFormat extends Format {
    JoinLeaveFormat(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, "joinLeaveFormats", permission, formatStrings, priority);
    }
}
