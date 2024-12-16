package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import com.creditcard.management.credit_card_api.application.mapper.CreditCardMapper;
import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditCardRepositoryAdapter implements CreditCardRepositoryPort {

    private final CreditCardRepository creditCardRepository;

    public CreditCardRepositoryAdapter(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll().stream()
                .map(CreditCardMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CreditCard> findById(Long id) {
        return creditCardRepository.findById(id)
                .map(CreditCardMapper::toDomain);
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return CreditCardMapper.toDomain(
                creditCardRepository.save(CreditCardMapper.toEntity(creditCard))
        );
    }

    @Override
    public void deleteById(Long id) {
        creditCardRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return creditCardRepository.existsById(id);
    }
}