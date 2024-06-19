package com.zaroyan.onlinestoreex.repositories;

import com.zaroyan.onlinestoreex.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zaroyan
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
