package com.creditcard.management.credit_card_api.application.port.out;

import com.creditcard.management.credit_card_api.core.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepositoryPort {
    List<CreditCard> findAll();
    Optional<CreditCard> findById(Long id);
    CreditCard save(CreditCard creditCard);
    void deleteById(Long id);
    boolean existsById(Long id);
}