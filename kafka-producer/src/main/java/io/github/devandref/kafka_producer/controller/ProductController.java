package io.github.devandref.kafka_producer.controller;

import io.github.devandref.kafka_producer.model.CreateProductBase;
import io.github.devandref.kafka_producer.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductBase createProductBase) {
        String createdProduct = productService.createProduct(createProductBase);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

}
