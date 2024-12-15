package com.creditcard.management.credit_card_api.application.port.in;

import com.creditcard.management.credit_card_api.core.model.CreditCard;

public interface CreateCreditCardUseCase {
    CreditCard createCreditCard(CreditCard creditCard);
}
