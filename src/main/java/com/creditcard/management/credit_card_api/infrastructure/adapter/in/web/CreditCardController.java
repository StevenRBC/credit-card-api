package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;


import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.application.mapper.CreditCardMapper;
import com.creditcard.management.credit_card_api.application.service.CreditCardService;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for managing Credit Card operations.
 * This controller provides endpoints for CRUD operations on credit cards.
 */
@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    /**
     * Constructor to initialize the CreditCardController with the service layer.
     *
     * @param creditCardService The service layer for handling credit card business logic.
     */
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    /**
     * Retrieves all credit cards.
     *
     * @return A ResponseEntity containing a list of CreditCardDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        List<CreditCardDTO> creditCards = creditCardService.getAllCreditCards().stream()
                .map(CreditCardMapper::toDTO) // Convert domain objects to DTOs
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditCards);
    }

    /**
     * Retrieves a specific credit card by its ID.
     *
     * @param id The unique identifier of the credit card.
     * @return A ResponseEntity containing the CreditCardDTO if found, or 404 Not Found if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDTO> getCreditCardById(@PathVariable Long id) {
        return creditCardService.getCreditCardById(id)
                .map(CreditCardMapper::toDTO) // Convert domain object to DTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new credit card.
     *
     * @param creditCardDTO The CreditCardDTO object containing credit card details.
     * @return A ResponseEntity containing the created CreditCardDTO object.
     */
    @PostMapping
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        CreditCard creditCard = CreditCardMapper.toDomainFromDTO(creditCardDTO); // Convert DTO to domain
        CreditCard createdCreditCard = creditCardService.createCreditCard(creditCard);
        return ResponseEntity.ok(CreditCardMapper.toDTO(createdCreditCard));
    }

    /**
     * Updates an existing credit card by its ID.
     *
     * @param id The unique identifier of the credit card to update.
     * @param creditCardDTO The CreditCardDTO object containing updated details.
     * @return A ResponseEntity containing the updated CreditCardDTO if successful, or 404 Not Found if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDTO> updateCreditCard(@PathVariable Long id, @RequestBody CreditCardDTO creditCardDTO) {
        CreditCard updatedCreditCard = CreditCardMapper.toDomainFromDTO(creditCardDTO); // Convert DTO to domain
        return creditCardService.updateCreditCard(id, updatedCreditCard)
                .map(CreditCardMapper::toDTO) // Convert updated domain object to DTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a credit card by its ID.
     *
     * @param id The unique identifier of the credit card to delete.
     * @return A ResponseEntity with 204 No Content if successful, or 404 Not Found if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id) {
        if (creditCardService.deleteCreditCard(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
