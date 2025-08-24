package io.github.devandref.kafka_producer.service;

import io.github.devandref.kafka_producer.model.CreateProductBase;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class ProductCreatedEvent extends CreateProductBase {

    private String productId;

    public ProductCreatedEvent(String title, BigDecimal price, Integer quantity, String productId) {
        super(title, price, quantity);
        this.productId = productId;
    }

}
