package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.in.CreateCreditCardUseCase;
import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class CreateCreditCardService implements CreateCreditCardUseCase {

    private final CreditCardRepositoryPort creditCardRepositoryPort;

    public CreateCreditCardService(CreditCardRepositoryPort creditCardRepositoryPort) {
        this.creditCardRepositoryPort = creditCardRepositoryPort;
    }

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        return creditCardRepositoryPort.save(creditCard);
    }
}