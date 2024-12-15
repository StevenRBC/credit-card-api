package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;


import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.application.mapper.CustomerMapper;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;

    public CreditCardController(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    // GET: Retrieve all credit cards
    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        List<CreditCard> creditCards = creditCardRepository.findAll();
        List<CreditCardDTO> creditCardDTOs = creditCards.stream()
                .map(CustomerMapper::toCreditCardDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditCardDTOs);
    }

    // POST: Create a new credit card
    @PostMapping
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        // Convert DTO to Entity
        CreditCard creditCard = CustomerMapper.toCreditCardEntity(creditCardDTO);

        // Save the credit card entity
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);

        // Convert back to DTO
        CreditCardDTO savedCreditCardDTO = CustomerMapper.toCreditCardDTO(savedCreditCard);

        return ResponseEntity.ok(savedCreditCardDTO);
    }

    // DELETE: Delete a credit card by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id) {
        if (creditCardRepository.existsById(id)) {
            creditCardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //UPDATE: Update a credit card by ID

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDTO> updateCreditCard(@PathVariable Long id, @RequestBody CreditCardDTO creditCardDTO) {
        return creditCardRepository.findById(id)
                .map(existingCreditCard -> {
                    existingCreditCard.setCardNumber(creditCardDTO.getCardNumber());
                    existingCreditCard.setExpirationDate(creditCardDTO.getExpirationDate());
                    existingCreditCard.setCvv(creditCardDTO.getCvv());
                    existingCreditCard.setCardType(creditCardDTO.getCardType());
                    existingCreditCard.setCreditLimit(creditCardDTO.getCreditLimit());
                    existingCreditCard.setCurrentBalance(creditCardDTO.getCurrentBalance());

                    CreditCard updatedCreditCard = creditCardRepository.save(existingCreditCard);
                    return ResponseEntity.ok(CustomerMapper.toCreditCardDTO(updatedCreditCard));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}