package com.creditcard.management.credit_card_api.application.port.in;

import com.creditcard.management.credit_card_api.core.model.Customer;

public interface CreateCustomerUseCase {
    Customer createCustomer(Customer customer);
}