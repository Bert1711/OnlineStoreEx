package com.zaroyan.onlinestoreex.repositories;

import com.zaroyan.onlinestoreex.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zaroyan
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
