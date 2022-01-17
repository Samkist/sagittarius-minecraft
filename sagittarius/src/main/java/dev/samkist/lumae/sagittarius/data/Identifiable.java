package dev.samkist.lumae.sagittarius.data;

public interface Identifiable<T extends Identifiable> {
    String uid();

    T uid(String uid);
}
