package dev.samkist.lumae.sagittarius.data.models;

public class Announcement extends MilkyModel<Announcement> {
    private String message;
    public static final String scope = "announcer";

    public Announcement(String id, String message) {
        super(id, scope);
        this.message = message;
    }

    public Announcement() {

    }

    public String message() {
        return message;
    }

    public Announcement message(String message) {
        this.message = message;
        return this;
    }
}
