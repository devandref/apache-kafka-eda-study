package io.github.devandref.kafka_consumer_email_notification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devandref.kafka_consumer_email_notification.dto.ProductCreatedEventDto;
import io.github.devandref.kafka_consumer_email_notification.exception.NoRetryableException;
import io.github.devandref.kafka_consumer_email_notification.exception.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "product-created-events-topic")
public class ProductCreatedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(String productCreatedEvent) throws JsonProcessingException {
        LOGGER.info("Received a new event: {}", productCreatedEvent);
        ProductCreatedEventDto productCreatedEventDto = convertJsonToObject(productCreatedEvent);
        LOGGER.info("Object convertido {}", productCreatedEventDto);
    }


    private ProductCreatedEventDto convertJsonToObject(String productCreatedEvent) {
        try {
            return new ObjectMapper().readValue(productCreatedEvent, ProductCreatedEventDto.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Erro ao converter para objeto {}", productCreatedEvent);
            throw new RuntimeException(e);
        }
    }

}
