package io.github.devandref.kafka_producer.service;

import io.github.devandref.kafka_producer.model.CreateProductBase;

import java.util.concurrent.ExecutionException;

public interface ProductService {

    public String createProduct(CreateProductBase createProductBase) throws ExecutionException, InterruptedException;

}
