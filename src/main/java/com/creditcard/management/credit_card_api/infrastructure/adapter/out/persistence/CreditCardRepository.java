package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CreditCardEntity.
 * Extends JpaRepository to provide CRUD operations and database interaction for the CreditCardEntity.
 * This interface acts as the persistence layer for credit card data.
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
    // JpaRepository provides default implementations for common operations:
    // - findAll()
    // - findById(Long id)
    // - save(CreditCardEntity entity)
    // - deleteById(Long id)
    // - existsById(Long id)
}
