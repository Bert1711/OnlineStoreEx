package com.zaroyan.onlinestoreex.repositories;

import com.zaroyan.onlinestoreex.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zaroyan
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
