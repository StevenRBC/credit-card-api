package com.creditcard.management.credit_card_api.integration;

import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardEntity;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardRepository;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerEntity;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the CreditCard API.
 * These tests verify the behavior of the CreditCard API endpoints
 * and their interaction with the database.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Use a single test instance for all tests in the class
class CreditCardIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc for simulating HTTP requests

    @Autowired
    private CreditCardRepository creditCardRepository; // Repository for CreditCardEntity

    @Autowired
    private CustomerRepository customerRepository; // Repository for CustomerEntity

    /**
     * Cleans up the database before each test.
     */
    @BeforeEach
    void setUp() {
        creditCardRepository.deleteAll(); // Clear credit card records
        customerRepository.deleteAll();  // Clear customer records
    }

    /**
     * Tests creating a new credit card and retrieving it.
     */
    @Test
    void testCreateAndGetCreditCard() throws Exception {
        // Arrange: Create a customer in the database
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        CustomerEntity savedCustomer = customerRepository.save(customer);

        // JSON representation of the new credit card
        String creditCardJson = """
            {
                "cardId": null,
                "cardNumber": "1234567890123456",
                "expirationDate": "2025-12-31",
                "cvv": "123",
                "cardType": "Credit",
                "creditLimit": 5000.0,
                "currentBalance": 1000.0,
                "customerId": %d
            }
        """.formatted(savedCustomer.getCustomerId());

        // Act: Send a POST request to create the credit card
        mockMvc.perform(post("/api/credit-cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(creditCardJson))
                .andExpect(status().isOk()) // Verify HTTP status is 200 OK
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456")); // Verify the response contains the card number

        // Assert: Retrieve the credit card and verify its details
        mockMvc.perform(get("/api/credit-cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cardNumber").value("1234567890123456"))
                .andExpect(jsonPath("$[0].creditLimit").value(5000.0));
    }

    /**
     * Tests deleting a credit card by its ID.
     */
    @Test
    void testDeleteCreditCard() throws Exception {
        // Arrange: Create a customer and associate a credit card
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        CustomerEntity savedCustomer = customerRepository.save(customer);

        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setCardNumber("1234567890123456");
        creditCard.setExpirationDate(new Date());
        creditCard.setCvv("123");
        creditCard.setCardType("Credit");
        creditCard.setCreditLimit(5000.0);
        creditCard.setCurrentBalance(1000.0);
        creditCard.setCustomer(savedCustomer);
        CreditCardEntity savedCard = creditCardRepository.save(creditCard);

        // Act: Send a DELETE request to remove the credit card
        mockMvc.perform(delete("/api/credit-cards/" + savedCard.getCardId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()); // Verify HTTP status is 204 No Content

        // Assert: Verify the credit card no longer exists
        mockMvc.perform(get("/api/credit-cards/" + savedCard.getCardId()))
                .andExpect(status().isNotFound()); // Verify HTTP status is 404 Not Found
    }

    /**
     * Tests updating an existing credit card.
     */
    @Test
    void testUpdateCreditCard() throws Exception {
        // Arrange: Create a customer and associate a credit card
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        CustomerEntity savedCustomer = customerRepository.save(customer);

        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setCardNumber("1234567890123456");
        creditCard.setExpirationDate(new Date());
        creditCard.setCvv("123");
        creditCard.setCardType("Credit");
        creditCard.setCreditLimit(5000.0);
        creditCard.setCurrentBalance(1000.0);
        creditCard.setCustomer(savedCustomer);
        CreditCardEntity savedCard = creditCardRepository.save(creditCard);

        // JSON representation of the updated credit card
        String updatedCardJson = """
            {
                "cardId": %d,
                "cardNumber": "6543210987654321",
                "expirationDate": "2026-01-01",
                "cvv": "456",
                "cardType": "Debit",
                "creditLimit": 10000.0,
                "currentBalance": 2000.0,
                "customerId": %d
            }
        """.formatted(savedCard.getCardId(), savedCustomer.getCustomerId());

        // Act: Send a PUT request to update the credit card
        mockMvc.perform(put("/api/credit-cards/" + savedCard.getCardId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCardJson))
                .andExpect(status().isOk()) // Verify HTTP status is 200 OK
                .andExpect(jsonPath("$.cardNumber").value("6543210987654321")); // Verify updated card number

        // Assert: Retrieve the updated card and verify its details
        mockMvc.perform(get("/api/credit-cards/" + savedCard.getCardId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("6543210987654321"))
                .andExpect(jsonPath("$.cardType").value("Debit"))
                .andExpect(jsonPath("$.creditLimit").value(10000.0));
    }
}
