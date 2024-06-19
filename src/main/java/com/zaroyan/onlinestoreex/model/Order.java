package com.zaroyan.onlinestoreex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Zaroyan
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is mandatory")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @NotEmpty(message = "Products are mandatory")
    private List<Product> products;

    @NotNull(message = "Order date is mandatory")
    private Date orderDate;

    @NotBlank(message = "Shipping address is mandatory")
    private String shippingAddress;

    @NotNull(message = "Total price is mandatory")
    @Min(value = 0, message = "Total price must be greater than or equal to 0")
    private Double totalPrice;

    @NotBlank(message = "Order status is mandatory")
    private String orderStatus;
}
