package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;

import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.application.mapper.CustomerMapper;
import com.creditcard.management.credit_card_api.application.service.CustomerService;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET: Retrieve all customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    // GET: Retrieve a specific customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(CustomerMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toDomainFromDTO(customerDTO);
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(CustomerMapper.toDTO(createdCustomer));
    }

    // PUT: Update a customer by ID
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName(customerDTO.getFirstName());
        updatedCustomer.setLastName(customerDTO.getLastName());
        updatedCustomer.setEmail(customerDTO.getEmail());

        return customerService.updateCustomer(id, updatedCustomer)
                .map(CustomerMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Delete a customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}