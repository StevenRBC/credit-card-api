package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.core.model.Customer;

public class CreditCardMapper {

    public static CreditCardDTO toCreditCardDTO(CreditCard creditCard) {
        return new CreditCardDTO(
                creditCard.getCardId(),
                creditCard.getCustomer() != null ? creditCard.getCustomer().getCustomerId() : null,
                creditCard.getCardNumber(),
                creditCard.getExpirationDate(),
                creditCard.getCvv(),
                creditCard.getCardType(),
                creditCard.getCreditLimit(),
                creditCard.getCurrentBalance()
        );
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