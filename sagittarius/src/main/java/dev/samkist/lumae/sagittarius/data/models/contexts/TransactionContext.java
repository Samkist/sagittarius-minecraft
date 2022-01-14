package dev.samkist.lumae.sagittarius.data.models.contexts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record TransactionContext(String uuid, Double amount) {
}
