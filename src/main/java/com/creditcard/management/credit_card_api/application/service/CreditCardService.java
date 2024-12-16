package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    private final CreditCardRepositoryPort creditCardRepositoryPort;

    public CreditCardService(CreditCardRepositoryPort creditCardRepositoryPort) {
        this.creditCardRepositoryPort = creditCardRepositoryPort;
    }

    // Retrieve all credit cards
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepositoryPort.findAll();
    }

    // Retrieve a specific credit card by ID
    public Optional<CreditCard> getCreditCardById(Long id) {
        return creditCardRepositoryPort.findById(id);
    }

    // Create a new credit card
    public CreditCard createCreditCard(CreditCard creditCard) {
        return creditCardRepositoryPort.save(creditCard);
    }

    // Update an existing credit card
    public Optional<CreditCard> updateCreditCard(Long id, CreditCard updatedCreditCard) {
        return creditCardRepositoryPort.findById(id).map(existingCreditCard -> {
            // Update fields
            existingCreditCard.setCardNumber(updatedCreditCard.getCardNumber());
            existingCreditCard.setExpirationDate(updatedCreditCard.getExpirationDate());
            existingCreditCard.setCvv(updatedCreditCard.getCvv());
            existingCreditCard.setCreditLimit(updatedCreditCard.getCreditLimit());
            existingCreditCard.setCurrentBalance(updatedCreditCard.getCurrentBalance());
            // Save and return the updated credit card
            return creditCardRepositoryPort.save(existingCreditCard);
        });
    }

    // Delete a credit card by ID
    public boolean deleteCreditCard(Long id) {
        if (creditCardRepositoryPort.existsById(id)) {
            creditCardRepositoryPort.deleteById(id);
            return true;
        }
        return false;
    }
}
