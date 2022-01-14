package dev.samkist.lumae.sagittarius.data.models.global;


import java.util.Map;

public class JoinLeaveFormat extends Format<JoinLeaveFormat> {

    public static final String scope = "join-leave-formats";

    public JoinLeaveFormat(String id, String scope, String permission, Map<String, String> formatStrings, Integer priority) {
        super(id, JoinLeaveFormat.scope, permission, formatStrings, priority);
    }

    public JoinLeaveFormat() {
        super();
    }
}
