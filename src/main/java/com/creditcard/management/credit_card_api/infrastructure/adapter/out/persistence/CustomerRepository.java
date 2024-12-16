package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CustomerEntity.
 * Extends JpaRepository to provide CRUD operations and database interaction for the CustomerEntity.
 * This interface acts as the persistence layer for customer data.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // JpaRepository provides default implementations for common operations:
    // - findAll()
    // - findById(Long id)
    // - save(CustomerEntity entity)
    // - deleteById(Long id)
    // - existsById(Long id)
}
