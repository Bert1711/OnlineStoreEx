package com.zaroyan.onlinestoreex.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaroyan.onlinestoreex.model.Customer;
import com.zaroyan.onlinestoreex.model.Order;
import com.zaroyan.onlinestoreex.model.Product;
import com.zaroyan.onlinestoreex.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author Zaroyan
 */

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private Order order;

    @BeforeEach
    public void setUp() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Albert");
        customer.setLastName("Zaroyan");
        customer.setEmail("albert@zaroyan.com");
        customer.setContactNumber("+79776610012");

        Product product = new Product();
        product.setProductId(1L);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(100.0);
        product.setQuantityInStock(10);

        order = new Order();
        order.setOrderId(1L);
        order.setCustomer(customer);
        order.setProducts(Arrays.asList(product));
        order.setOrderDate(new Date());
        order.setShippingAddress("Moscow");
        order.setTotalPrice(100.0);
        order.setOrderStatus("Pending");
    }

    @Test
    public void testCreateOrder() throws Exception {
        given(orderService.createOrder(any(Order.class))).willReturn(order);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void testGetOrderById() throws Exception {
        given(orderService.getOrderById(anyLong())).willReturn(order);

        mockMvc.perform(get("/api/orders/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        given(orderService.getAllOrders()).willReturn(Arrays.asList(order));

        mockMvc.perform(get("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(order))));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("/api/orders/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

