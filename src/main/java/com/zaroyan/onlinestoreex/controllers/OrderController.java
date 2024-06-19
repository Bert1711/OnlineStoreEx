package com.zaroyan.onlinestoreex.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaroyan.onlinestoreex.model.Order;
import com.zaroyan.onlinestoreex.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zaroyan
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderController(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody String orderJson) throws JsonProcessingException {
        Order order = orderService.createOrder(objectMapper.readValue(orderJson, Order.class));
        String jsonResult = objectMapper.writeValueAsString(order);
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderById(@PathVariable Long id) throws JsonProcessingException {
        Order order = orderService.getOrderById(id);
        String jsonResult = objectMapper.writeValueAsString(order);
        return ResponseEntity.ok(jsonResult);

    }

    @GetMapping
    public ResponseEntity<String> getAllOrders() throws JsonProcessingException {
        List<Order> orders = orderService.getAllOrders();
        String jsonResult = objectMapper.writeValueAsString(orders);
        return ResponseEntity.ok(jsonResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
