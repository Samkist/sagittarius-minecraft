package dev.samkist.lumae.sagittarius.data.models;

import org.springframework.data.annotation.Id;

public class Transaction {
    @Id
    public String id;

    public String email;
    public float amount;
    public String description;

    public Transaction() {}

    public Transaction(String email, float amount, String description) {
        this.email = email;
        this.amount = amount;
        this.description = description;
    }

    public String id() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s, EMAIL: %s, AMOUNT: %f, DESC: %s",
                id,
                email,
                amount,
                description
        );
    }
}
