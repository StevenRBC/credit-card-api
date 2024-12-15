package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.core.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class CustomerMapper {

    // Convert from Entity to DTO
    public static CustomerDTO toCustomerDTO(Customer customer) {
        List<CreditCardDTO> creditCards = customer.getCreditCards() != null
                ? customer.getCreditCards().stream()
                .map(CustomerMapper::toCreditCardDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                creditCards
        );
    }

    public static CreditCardDTO toCreditCardDTO(CreditCard creditCard) {
        return new CreditCardDTO(
                creditCard.getCardId(),
                creditCard.getCustomer().getCustomerId(),
                creditCard.getCardNumber(),
                creditCard.getExpirationDate(),
                creditCard.getCvv(),
                creditCard.getCardType(),
                creditCard.getCreditLimit(),
                creditCard.getCurrentBalance()
        );
    }

    // Convert from DTO to Entity
    public static Customer toCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());

        if (customerDTO.getCreditCards() != null) {
            List<CreditCard> creditCards = customerDTO.getCreditCards().stream()
                    .map(CustomerMapper::toCreditCardEntity)
                    .peek(creditCard -> creditCard.setCustomer(customer))
                    .collect(Collectors.toList());
            customer.setCreditCards(creditCards);
        }

        return customer;
    }

    public static CreditCard toCreditCardEntity(CreditCardDTO creditCardDTO) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardId(creditCardDTO.getCardId());
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
        creditCard.setCvv(creditCardDTO.getCvv());
        creditCard.setCardType(creditCardDTO.getCardType());
        creditCard.setCreditLimit(creditCardDTO.getCreditLimit());
        creditCard.setCurrentBalance(creditCardDTO.getCurrentBalance());

        if (creditCardDTO.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setCustomerId(creditCardDTO.getCustomerId());
            creditCard.setCustomer(customer);
        }

        return creditCard;
    }
}