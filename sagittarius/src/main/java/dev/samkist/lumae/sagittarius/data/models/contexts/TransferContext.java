package dev.samkist.lumae.sagittarius.data.models.contexts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record TransferContext(String to, String from, Long amount) {

}
