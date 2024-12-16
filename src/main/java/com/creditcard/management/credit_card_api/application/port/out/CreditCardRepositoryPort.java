package com.creditcard.management.credit_card_api.application.port.out;

import com.creditcard.management.credit_card_api.core.model.CreditCard;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Credit Card repository operations.
 * This interface defines the contract for interacting with the persistence layer
 * without exposing specific implementation details (e.g., JPA or database-specific code).
 * It allows for decoupling the business logic from the persistence implementation.
 */
public interface CreditCardRepositoryPort {

    /**
     * Retrieves all credit cards from the repository.
     *
     * @return A list of all CreditCard objects.
     */
    List<CreditCard> findAll();

    /**
     * Retrieves a specific credit card by its unique identifier.
     *
     * @param id The unique identifier of the credit card.
     * @return An Optional containing the CreditCard if found, or empty if not.
     */
    Optional<CreditCard> findById(Long id);

    /**
     * Saves a credit card to the repository.
     * If the credit card does not exist, it will be created.
     * If it already exists, it will be updated.
     *
     * @param creditCard The CreditCard object to save.
     * @return The saved CreditCard object.
     */
    CreditCard save(CreditCard creditCard);

    /**
     * Deletes a credit card from the repository by its unique identifier.
     *
     * @param id The unique identifier of the credit card to delete.
     */
    void deleteById(Long id);

    /**
     * Checks if a credit card exists in the repository by its unique identifier.
     *
     * @param id The unique identifier of the credit card.
     * @return True if the credit card exists, false otherwise.
     */
    boolean existsById(Long id);
}
