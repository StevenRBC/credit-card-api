package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.out.CustomerRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {
        return customerRepositoryPort.findAll();
    }

    // Retrieve a specific customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepositoryPort.findById(id);
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepositoryPort.save(customer);
    }

    // Update an existing customer
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepositoryPort.findById(id).map(existingCustomer -> {
            // Update fields of the existing customer
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setEmail(updatedCustomer.getEmail());

            existingCustomer.setCreditCards(existingCustomer.getCreditCards());
            // Save and return updated customer
            return customerRepositoryPort.save(existingCustomer);
        });
    }

    // Delete a customer by ID
    public boolean deleteCustomer(Long id) {
        if (customerRepositoryPort.existsById(id)) { // Check if the customer exists
            customerRepositoryPort.deleteById(id); // Perform the deletion
            return true; // Return true if the customer was successfully deleted
        }
        return false; // Return false if the customer was not found
    }
}
