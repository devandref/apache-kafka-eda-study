package io.github.devandref.kafka_producer.service.impl;

import io.github.devandref.kafka_producer.model.CreateProductBase;
import io.github.devandref.kafka_producer.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public String createProduct(CreateProductBase createProductBase) {
        return "";
    }


}
