package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;


import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.application.mapper.CreditCardMapper;
import com.creditcard.management.credit_card_api.application.service.CreditCardService;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    // GET: Retrieve all credit cards
    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        List<CreditCardDTO> creditCards = creditCardService.getAllCreditCards().stream()
                .map(CreditCardMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(creditCards);
    }

    // GET: Retrieve a specific credit card by ID
    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDTO> getCreditCardById(@PathVariable Long id) {
        return creditCardService.getCreditCardById(id)
                .map(CreditCardMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new credit card
    @PostMapping
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        CreditCard creditCard = CreditCardMapper.toDomainFromDTO(creditCardDTO);
        CreditCard createdCreditCard = creditCardService.createCreditCard(creditCard);
        return ResponseEntity.ok(CreditCardMapper.toDTO(createdCreditCard));
    }

    // PUT: Update a credit card by ID
    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDTO> updateCreditCard(@PathVariable Long id, @RequestBody CreditCardDTO creditCardDTO) {
        CreditCard updatedCreditCard = CreditCardMapper.toDomainFromDTO(creditCardDTO);
        return creditCardService.updateCreditCard(id, updatedCreditCard)
                .map(CreditCardMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Delete a credit card by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id) {
        if (creditCardService.deleteCreditCard(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}