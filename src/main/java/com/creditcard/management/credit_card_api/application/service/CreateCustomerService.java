package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.in.CreateCustomerUseCase;
import com.creditcard.management.credit_card_api.application.port.out.CustomerRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateCustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customer.getCreditCards() == null) {
            customer.setCreditCards(new ArrayList<>());
        }
        return customerRepositoryPort.save(customer);
    }
}