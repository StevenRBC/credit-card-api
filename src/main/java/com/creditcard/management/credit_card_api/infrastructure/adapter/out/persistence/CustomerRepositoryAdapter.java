package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import com.creditcard.management.credit_card_api.application.mapper.CustomerMapper;
import com.creditcard.management.credit_card_api.application.port.out.CustomerRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter class for the CustomerRepository.
 * Implements the CustomerRepositoryPort interface to provide a bridge
 * between the domain layer and the persistence layer.
 *
 * This adapter is responsible for:
 * - Converting between domain models and persistence entities.
 * - Interacting with the underlying JPA repository.
 */
@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;

    /**
     * Constructor to initialize the adapter with the JPA repository.
     *
     * @param customerRepository The JPA repository for CustomerEntity.
     */
    public CustomerRepositoryAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves all customers from the database and converts them to domain models.
     *
     * @return A list of Customer domain objects.
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDomain) // Convert persistence entities to domain models
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific customer by their ID and converts it to a domain model.
     *
     * @param id The unique identifier of the customer.
     * @return An Optional containing the Customer domain object if found, or empty if not.
     */
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDomain); // Convert persistence entity to domain model
    }

    /**
     * Saves a customer in the database.
     * Converts the domain model to a persistence entity before saving,
     * and then converts the saved entity back to a domain model.
     *
     * @param customer The Customer domain object to save.
     * @return The saved Customer domain object.
     */
    @Override
    public Customer save(Customer customer) {
        return CustomerMapper.toDomain(
                customerRepository.save(CustomerMapper.toEntity(customer)) // Save and convert
        );
    }

    /**
     * Deletes a customer from the database by their ID.
     *
     * @param id The unique identifier of the customer to delete.
     */
    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * Checks if a customer exists in the database by their ID.
     *
     * @param id The unique identifier of the customer.
     * @return True if the customer exists, false otherwise.
     */
    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }
}
