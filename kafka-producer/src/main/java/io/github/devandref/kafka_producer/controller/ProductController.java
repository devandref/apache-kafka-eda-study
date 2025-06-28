package io.github.devandref.kafka_producer.controller;

import io.github.devandref.kafka_producer.dto.ErrorMessageDTO;
import io.github.devandref.kafka_producer.model.CreateProductBase;
import io.github.devandref.kafka_producer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LogManager.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductBase createProductBase) {
        String createdProductId = null;
        try {
            createdProductId = productService.createProduct(createProductBase);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            var errorMessage = new ErrorMessageDTO(e.getMessage());
            return ResponseEntity.ok(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductId);
    }
}