package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import com.creditcard.management.credit_card_api.application.mapper.CustomerMapper;
import com.creditcard.management.credit_card_api.application.port.out.CustomerRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;

    public CustomerRepositoryAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        return CustomerMapper.toDomain(
                customerRepository.save(CustomerMapper.toEntity(customer))
        );
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }
}