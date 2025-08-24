package io.github.devandref.kafka_consumer_email_notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductCreatedEventDto {

    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

}
