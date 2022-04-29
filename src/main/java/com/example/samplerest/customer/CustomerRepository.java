package com.example.samplerest.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, BigDecimal> {

    Optional<Customer> findCustomerByEmail(String email);
    Customer findCustomerById(BigDecimal id);

}
