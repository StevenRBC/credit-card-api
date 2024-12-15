package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;


import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.application.mapper.CustomerMapper;
import com.creditcard.management.credit_card_api.core.model.Customer;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // GET: Retrieve all customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(CustomerMapper::toCustomerDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }

    // GET: Retrieve a specific customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toCustomerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomerEntity(customerDTO);

        if (customer.getCreditCards() != null) {
            customer.getCreditCards().forEach(card -> card.setCustomer(customer)); // Establecer la relación
        }

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO savedCustomerDTO = CustomerMapper.toCustomerDTO(savedCustomer);

        return ResponseEntity.ok(savedCustomerDTO);
    }

    // DELETE: Delete a customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}