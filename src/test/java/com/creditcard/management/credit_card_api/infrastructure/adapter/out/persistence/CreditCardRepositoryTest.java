package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for CreditCardRepository.
 * These tests verify repository operations like saving, finding, updating, and deleting entities.
 */
@DataJpaTest
class CreditCardRepositoryTest {

    @Autowired
    private CreditCardRepository creditCardRepository; // Repository for CreditCardEntity

    @Autowired
    private CustomerRepository customerRepository; // Repository for CustomerEntity

    /**
     * Tests saving a CreditCardEntity and retrieving it by its ID.
     * Verifies the relationship between customer and credit card is correctly persisted.
     */
    @Test
    void testSaveAndFindById() {
        // Arrange: Create and save a CustomerEntity first
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        CustomerEntity savedCustomer = customerRepository.save(customer); // Save the customer

        // Create a CreditCardEntity associated with the saved customer
        CreditCardEntity card = new CreditCardEntity();
        card.setCardNumber("1234567890123456");
        card.setExpirationDate(new Date());
        card.setCvv("123");
        card.setCardType("Credit");
        card.setCreditLimit(5000.0);
        card.setCurrentBalance(1000.0);
        card.setCustomer(savedCustomer); // Associate with the saved customer

        // Act: Save the credit card in the database
        CreditCardEntity savedCard = creditCardRepository.save(card);

        // Assert: Verify the card was saved and retrieved successfully
        Optional<CreditCardEntity> foundCard = creditCardRepository.findById(savedCard.getCardId());
        assertTrue(foundCard.isPresent());
        assertEquals("1234567890123456", foundCard.get().getCardNumber());
        assertEquals(savedCustomer.getCustomerId(), foundCard.get().getCustomer().getCustomerId());
    }

    /**
     * Tests deleting a CreditCardEntity by its ID.
     * Verifies the card is removed from the database and cannot be found.
     */
    @Test
    void testDeleteById() {
        // Arrange: Create and save a CustomerEntity
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.com");

        CustomerEntity savedCustomer = customerRepository.save(customer);

        // Create and save a CreditCardEntity associated with the customer
        CreditCardEntity card = new CreditCardEntity();
        card.setCardNumber("9876543210987654");
        card.setExpirationDate(new Date());
        card.setCvv("456");
        card.setCardType("Debit");
        card.setCreditLimit(3000.0);
        card.setCurrentBalance(1500.0);
        card.setCustomer(savedCustomer);

        CreditCardEntity savedCard = creditCardRepository.save(card);

        // Act: Delete the credit card by ID
        creditCardRepository.deleteById(savedCard.getCardId());

        // Assert: Verify the card no longer exists in the database
        Optional<CreditCardEntity> foundCard = creditCardRepository.findById(savedCard.getCardId());
        assertFalse(foundCard.isPresent());
    }

    /**
     * Tests updating the current balance of a CreditCardEntity.
     * Verifies the updated balance is persisted in the database.
     */
    @Test
    void testUpdateCardBalance() {
        // Arrange: Create and save a CustomerEntity
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("Mark");
        customer.setLastName("Smith");
        customer.setEmail("mark.smith@example.com");

        CustomerEntity savedCustomer = customerRepository.save(customer);

        // Create and save a CreditCardEntity associated with the customer
        CreditCardEntity card = new CreditCardEntity();
        card.setCardNumber("1234123412341234");
        card.setExpirationDate(new Date());
        card.setCvv("789");
        card.setCardType("Credit");
        card.setCreditLimit(10000.0);
        card.setCurrentBalance(2000.0);
        card.setCustomer(savedCustomer);

        CreditCardEntity savedCard = creditCardRepository.save(card);

        // Act: Update the current balance of the card
        savedCard.setCurrentBalance(5000.0);
        CreditCardEntity updatedCard = creditCardRepository.save(savedCard);

        // Assert: Verify the balance was updated successfully
        assertEquals(5000.0, updatedCard.getCurrentBalance());
    }
}
