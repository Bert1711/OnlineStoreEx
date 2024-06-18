package com.zaroyan.onlinestoreex.repositories;

import com.zaroyan.onlinestoreex.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zaroyan
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
