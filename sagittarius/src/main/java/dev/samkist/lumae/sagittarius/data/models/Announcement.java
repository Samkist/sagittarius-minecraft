package dev.samkist.lumae.sagittarius.data.models;

public class Announcement extends MilkyModel {
    private String message;
    public static final String scope = "announcer";

    public Announcement(String id, String message) {
        super(id, scope);
        this.message = message;
    }

    public Announcement() {

    }

    public String getMessage() {
        return message;
    }

    public void setLocation(String message) {
        this.message = message;
    }
}
