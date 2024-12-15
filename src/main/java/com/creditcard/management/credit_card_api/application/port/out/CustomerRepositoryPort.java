package com.creditcard.management.credit_card_api.application.port.out;

import com.creditcard.management.credit_card_api.core.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
    void deleteById(Long id);
}