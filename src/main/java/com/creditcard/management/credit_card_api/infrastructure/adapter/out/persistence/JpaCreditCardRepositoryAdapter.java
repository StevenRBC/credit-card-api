package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCreditCardRepositoryAdapter implements CreditCardRepositoryPort {

    private final CreditCardRepository repository;

    public JpaCreditCardRepositoryAdapter(CreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CreditCard> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CreditCard> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return repository.save(creditCard);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}