package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.core.model.Customer;

import java.util.List;
import java.util.stream.Collectors;
public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer) {
        List<CreditCardDTO> creditCards = customer.getCreditCards() != null
                ? customer.getCreditCards().stream()
                .map(CreditCardMapper::toCreditCardDTO)
                .collect(Collectors.toList())
                : List.of();

        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                creditCards
        );
    }

    public static Customer toCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());

        if (customerDTO.getCreditCards() != null) {
            List<CreditCard> creditCards = customerDTO.getCreditCards().stream()
                    .map(CreditCardMapper::toCreditCardEntity)
                    .peek(creditCard -> creditCard.setCustomer(customer))
                    .collect(Collectors.toList());
            customer.setCreditCards(creditCards);
        }

        return customer;
    }
}