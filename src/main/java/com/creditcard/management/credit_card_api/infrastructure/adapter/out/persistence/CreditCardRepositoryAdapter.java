package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import com.creditcard.management.credit_card_api.application.mapper.CreditCardMapper;
import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter class for the CreditCardRepository.
 * Implements the CreditCardRepositoryPort interface to provide a bridge
 * between the domain layer and the persistence layer.
 *
 * This adapter is responsible for:
 * - Converting between domain models and persistence entities.
 * - Interacting with the underlying JPA repository.
 */
@Component
public class CreditCardRepositoryAdapter implements CreditCardRepositoryPort {

    private final CreditCardRepository creditCardRepository;

    /**
     * Constructor to initialize the adapter with the JPA repository.
     *
     * @param creditCardRepository The JPA repository for CreditCardEntity.
     */
    public CreditCardRepositoryAdapter(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    /**
     * Retrieves all credit cards from the database and converts them to domain models.
     *
     * @return A list of CreditCard domain objects.
     */
    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll().stream()
                .map(CreditCardMapper::toDomain) // Convert persistence entities to domain models
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific credit card by its ID and converts it to a domain model.
     *
     * @param id The unique identifier of the credit card.
     * @return An Optional containing the CreditCard domain object if found, or empty if not.
     */
    @Override
    public Optional<CreditCard> findById(Long id) {
        return creditCardRepository.findById(id)
                .map(CreditCardMapper::toDomain); // Convert persistence entity to domain model
    }

    /**
     * Saves a credit card in the database.
     * Converts the domain model to a persistence entity before saving,
     * and then converts the saved entity back to a domain model.
     *
     * @param creditCard The CreditCard domain object to save.
     * @return The saved CreditCard domain object.
     */
    @Override
    public CreditCard save(CreditCard creditCard) {
        return CreditCardMapper.toDomain(
                creditCardRepository.save(CreditCardMapper.toEntity(creditCard)) // Save and convert
        );
    }

    /**
     * Deletes a credit card from the database by its ID.
     *
     * @param id The unique identifier of the credit card to delete.
     */
    @Override
    public void deleteById(Long id) {
        creditCardRepository.deleteById(id);
    }

    /**
     * Checks if a credit card exists in the database by its ID.
     *
     * @param id The unique identifier of the credit card.
     * @return True if the credit card exists, false otherwise.
     */
    @Override
    public boolean existsById(Long id) {
        return creditCardRepository.existsById(id);
    }
}
