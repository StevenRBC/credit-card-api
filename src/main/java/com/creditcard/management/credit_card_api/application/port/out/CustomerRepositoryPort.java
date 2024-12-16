package com.creditcard.management.credit_card_api.application.port.out;

import com.creditcard.management.credit_card_api.core.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Customer repository operations.
 * This interface defines the contract for interacting with the persistence layer
 * for customer-related data. It abstracts the underlying implementation (e.g., JPA, database)
 * to decouple the business logic from the persistence details.
 */
public interface CustomerRepositoryPort {

    /**
     * Retrieves all customers from the repository.
     *
     * @return A list of all Customer objects.
     */
    List<Customer> findAll();

    /**
     * Retrieves a specific customer by its unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return An Optional containing the Customer if found, or empty if not.
     */
    Optional<Customer> findById(Long id);

    /**
     * Saves a customer to the repository.
     * If the customer does not exist, it will be created.
     * If it already exists, it will be updated.
     *
     * @param customer The Customer object to save.
     * @return The saved Customer object.
     */
    Customer save(Customer customer);

    /**
     * Deletes a customer from the repository by its unique identifier.
     *
     * @param id The unique identifier of the customer to delete.
     */
    void deleteById(Long id);

    /**
     * Checks if a customer exists in the repository by its unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return True if the customer exists, false otherwise.
     */
    boolean existsById(Long id);
}
