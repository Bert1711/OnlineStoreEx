package com.zaroyan.onlinestoreex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zaroyan
 */


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be greater than to 0")
    private Double price;

    @NotNull(message = "Quantity in stock is mandatory")
    @Min(value = 0, message = "Quantity in stock must be greater than or equal to 0")
    private Integer quantityInStock;
}
