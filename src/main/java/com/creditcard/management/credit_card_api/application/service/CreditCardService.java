package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing credit card operations.
 * This class contains the business logic related to credit cards
 * and interacts with the persistence layer through the CreditCardRepositoryPort.
 */
@Service
public class CreditCardService {

    private final CreditCardRepositoryPort creditCardRepositoryPort;

    /**
     * Constructor to initialize the service with the repository port.
     *
     * @param creditCardRepositoryPort The port interface for credit card repository operations.
     */
    public CreditCardService(CreditCardRepositoryPort creditCardRepositoryPort) {
        this.creditCardRepositoryPort = creditCardRepositoryPort;
    }

    /**
     * Retrieves all credit cards from the repository.
     *
     * @return A list of all CreditCard objects.
     */
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepositoryPort.findAll();
    }

    /**
     * Retrieves a specific credit card by its unique identifier.
     *
     * @param id The unique identifier of the credit card.
     * @return An Optional containing the CreditCard if found, or empty if not.
     */
    public Optional<CreditCard> getCreditCardById(Long id) {
        return creditCardRepositoryPort.findById(id);
    }

    /**
     * Creates a new credit card in the repository.
     *
     * @param creditCard The CreditCard object to create.
     * @return The saved CreditCard object.
     */
    public CreditCard createCreditCard(CreditCard creditCard) {
        return creditCardRepositoryPort.save(creditCard);
    }

    /**
     * Updates an existing credit card in the repository.
     * If the credit card exists, its fields are updated and it is saved.
     *
     * @param id The unique identifier of the credit card to update.
     * @param updatedCreditCard The updated CreditCard object containing new values.
     * @return An Optional containing the updated CreditCard if the ID exists, or empty if not.
     */
    public Optional<CreditCard> updateCreditCard(Long id, CreditCard updatedCreditCard) {
        return creditCardRepositoryPort.findById(id).map(existingCreditCard -> {
            // Update fields with the new values
            existingCreditCard.setCardNumber(updatedCreditCard.getCardNumber());
            existingCreditCard.setExpirationDate(updatedCreditCard.getExpirationDate());
            existingCreditCard.setCvv(updatedCreditCard.getCvv());
            existingCreditCard.setCardType(updatedCreditCard.getCardType());
            existingCreditCard.setCreditLimit(updatedCreditCard.getCreditLimit());
            existingCreditCard.setCurrentBalance(updatedCreditCard.getCurrentBalance());
            // Save and return the updated credit card
            return creditCardRepositoryPort.save(existingCreditCard);
        });
    }

    /**
     * Deletes a credit card from the repository by its unique identifier.
     *
     * @param id The unique identifier of the credit card to delete.
     * @return True if the credit card was successfully deleted, false if it does not exist.
     */
    public boolean deleteCreditCard(Long id) {
        if (creditCardRepositoryPort.existsById(id)) {
            creditCardRepositoryPort.deleteById(id);
            return true;
        }
        return false;
    }
}
