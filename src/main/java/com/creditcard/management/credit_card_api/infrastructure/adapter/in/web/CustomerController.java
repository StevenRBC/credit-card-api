package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;

import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.application.mapper.CustomerMapper;
import com.creditcard.management.credit_card_api.application.service.CustomerService;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for managing Customer operations.
 * This controller provides endpoints for CRUD operations on customers.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor to initialize the CustomerController with the service layer.
     *
     * @param customerService The service layer for handling customer business logic.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves all customers.
     *
     * @return A ResponseEntity containing a list of CustomerDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers().stream()
                .map(CustomerMapper::toDTO) // Convert domain objects to DTOs
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    /**
     * Retrieves a specific customer by their ID.
     *
     * @param id The unique identifier of the customer.
     * @return A ResponseEntity containing the CustomerDTO if found, or 404 Not Found if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(CustomerMapper::toDTO) // Convert domain object to DTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new customer.
     *
     * @param customerDTO The CustomerDTO object containing customer details.
     * @return A ResponseEntity containing the created CustomerDTO object.
     */
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toDomainFromDTO(customerDTO); // Convert DTO to domain
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(CustomerMapper.toDTO(createdCustomer));
    }

    /**
     * Updates an existing customer by their ID.
     * Only updates the fields: firstName, lastName, and email. Associated credit cards remain unchanged.
     *
     * @param id The unique identifier of the customer to update.
     * @param customerDTO The CustomerDTO object containing updated details.
     * @return A ResponseEntity containing the updated CustomerDTO if successful, or 404 Not Found if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName(customerDTO.getFirstName());
        updatedCustomer.setLastName(customerDTO.getLastName());
        updatedCustomer.setEmail(customerDTO.getEmail());

        return customerService.updateCustomer(id, updatedCustomer)
                .map(CustomerMapper::toDTO) // Convert updated domain object to DTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id The unique identifier of the customer to delete.
     * @return A ResponseEntity with 204 No Content if successful, or 404 Not Found if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
