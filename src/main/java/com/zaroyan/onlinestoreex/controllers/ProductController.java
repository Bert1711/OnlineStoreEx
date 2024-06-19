package com.zaroyan.onlinestoreex.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaroyan.onlinestoreex.model.Product;
import com.zaroyan.onlinestoreex.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zaroyan
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;


    @Autowired
    public ProductController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException {
        List<Product> products = productService.getAllProducts();
        String jsonResult = objectMapper.writeValueAsString(products);
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) throws JsonProcessingException {
        Product product = productService.getProductById(id);
        String jsonResult = objectMapper.writeValueAsString(product);
        return ResponseEntity.ok(jsonResult);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody String productJson) throws JsonProcessingException {
        Product product = productService.createProduct(objectMapper.readValue(productJson, Product.class));
        String jsonResult = objectMapper.writeValueAsString(product);
        return ResponseEntity.ok(jsonResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @Valid @RequestBody String productJson) throws JsonProcessingException {
        Product productDetails = objectMapper.readValue(productJson, Product.class);
        Product product = productService.updateProduct(id, productDetails);
        String jsonResult = objectMapper.writeValueAsString(product);
        return ResponseEntity.ok(jsonResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}