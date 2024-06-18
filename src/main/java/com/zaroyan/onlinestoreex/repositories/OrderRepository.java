package com.zaroyan.onlinestoreex.repositories;

import com.zaroyan.onlinestoreex.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zaroyan
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
