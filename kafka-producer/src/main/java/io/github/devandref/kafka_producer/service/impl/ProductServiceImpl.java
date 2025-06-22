package io.github.devandref.kafka_producer.service.impl;

import io.github.devandref.kafka_producer.model.CreateProductBase;
import io.github.devandref.kafka_producer.service.ProductCreatedEvent;
import io.github.devandref.kafka_producer.service.ProductService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductBase createProductBase) {

        String productId = UUID.randomUUID().toString();
        var productCreatedEvent = new ProductCreatedEvent(createProductBase.getTitle(), createProductBase.getPrice(),
                createProductBase.getQuantity(), productId);

        kafkaTemplate.send("product-created-events-topic", productCreatedEvent);
        return productId;
    }


}
