package io.github.devandref.kafka_producer.service.impl;

import io.github.devandref.kafka_producer.model.CreateProductBase;
import io.github.devandref.kafka_producer.service.ProductCreatedEvent;
import io.github.devandref.kafka_producer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductBase createProductBase) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();
        var productCreatedEvent = new ProductCreatedEvent(createProductBase.getTitle(), createProductBase.getPrice(),
                createProductBase.getQuantity(), productId);

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic", productCreatedEvent).get();

        log.info("--- KAFKA INFORMATION ---");
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Offset: {}", result.getRecordMetadata().offset());
        log.info("--- KAFKA INFORMATION ---");

        log.info("--- RETURNING PRODUCT ID {} ---", productId);
        return productId;
    }


}
