package io.github.devandref.kafka_producer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ErrorMessageDTO {

    private LocalDate timestamp;
    private String message;

    public ErrorMessageDTO(String message) {
        this.timestamp = LocalDate.now();
        this.message = message;
    }
}
