package dev.samkist.lumae.sagittarius.exceptions;

public class PlayerNotFoundRuntimeException extends APIRuntimeException {

    private String uuid;

    public PlayerNotFoundRuntimeException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
