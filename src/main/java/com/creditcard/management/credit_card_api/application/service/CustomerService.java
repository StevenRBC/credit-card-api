package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.out.CustomerRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing customer operations.
 * This class contains the business logic related to customers
 * and interacts with the persistence layer through the CustomerRepositoryPort.
 */
@Service
public class CustomerService {

    private final CustomerRepositoryPort customerRepositoryPort;

    /**
     * Constructor to initialize the service with the repository port.
     *
     * @param customerRepositoryPort The port interface for customer repository operations.
     */
    public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    /**
     * Retrieves all customers from the repository.
     *
     * @return A list of all Customer objects.
     */
    public List<Customer> getAllCustomers() {
        return customerRepositoryPort.findAll();
    }

    /**
     * Retrieves a specific customer by their unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return An Optional containing the Customer if found, or empty if not.
     */
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepositoryPort.findById(id);
    }

    /**
     * Creates a new customer in the repository.
     *
     * @param customer The Customer object to create.
     * @return The saved Customer object.
     */
    public Customer createCustomer(Customer customer) {
        return customerRepositoryPort.save(customer);
    }

    /**
     * Updates an existing customer in the repository.
     * If the customer exists, their fields are updated and saved.
     *
     * @param id The unique identifier of the customer to update.
     * @param updatedCustomer The Customer object containing updated values.
     * @return An Optional containing the updated Customer if the ID exists, or empty if not.
     */
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepositoryPort.findById(id).map(existingCustomer -> {
            // Update fields of the existing customer
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setEmail(updatedCustomer.getEmail());

            // Ensure existing credit cards remain unchanged
            existingCustomer.setCreditCards(existingCustomer.getCreditCards());

            // Save and return the updated customer
            return customerRepositoryPort.save(existingCustomer);
        });
    }

    /**
     * Deletes a customer from the repository by their unique identifier.
     *
     * @param id The unique identifier of the customer to delete.
     * @return True if the customer was successfully deleted, false if they do not exist.
     */
    public boolean deleteCustomer(Long id) {
        if (customerRepositoryPort.existsById(id)) {
            customerRepositoryPort.deleteById(id);
            return true;
        }
        return false;
    }
}
