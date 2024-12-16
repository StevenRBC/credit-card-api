package com.creditcard.management.credit_card_api.infrastructure.adapter.in.web;

import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.application.mapper.CreditCardMapper;
import com.creditcard.management.credit_card_api.application.service.CreditCardService;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.infrastructure.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for CreditCardController.
 * Focuses on testing the controller layer independently using MockMvc.
 * Mock dependencies like CreditCardService to simulate service behavior.
 */
@WebMvcTest(CreditCardController.class)
@Import(SecurityConfig.class) // Include security configuration to handle CORS and CSRF
class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests and assertions

    @MockBean
    private CreditCardService creditCardService; // Mocked service to simulate business logic

    private CreditCardDTO testCardDTO; // Test DTO object for the tests

    /**
     * Sets up the test environment before each test.
     * Initializes a sample CreditCardDTO object for use in the tests.
     */
    @BeforeEach
    void setUp() {
        testCardDTO = new CreditCardDTO();
        testCardDTO.setCardId(1L);
        testCardDTO.setCardNumber("1234567890123456");
        testCardDTO.setExpirationDate("2025-12-31");
        testCardDTO.setCvv("123");
        testCardDTO.setCardType("Credit");
        testCardDTO.setCreditLimit(5000.0);
        testCardDTO.setCurrentBalance(1000.0);
        testCardDTO.setCustomerId(1L);
    }

    /**
     * Tests retrieving all credit cards.
     * Ensures the controller returns the expected list of credit cards in JSON format.
     */
    @Test
    void testGetAllCreditCards() throws Exception {
        // Arrange: Simulate service behavior
        when(creditCardService.getAllCreditCards()).thenReturn(Arrays.asList(CreditCardMapper.toDomainFromDTO(testCardDTO)));

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/api/credit-cards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cardId").value(1))
                .andExpect(jsonPath("$[0].cardNumber").value("1234567890123456"))
                .andExpect(jsonPath("$[0].creditLimit").value(5000.0));
    }

    /**
     * Tests retrieving a specific credit card by ID when it exists.
     */
    @Test
    void testGetCreditCardById_Found() throws Exception {
        // Arrange: Simulate finding a credit card by ID
        when(creditCardService.getCreditCardById(1L))
                .thenReturn(Optional.of(CreditCardMapper.toDomainFromDTO(testCardDTO)));

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/api/credit-cards/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(1))
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"))
                .andExpect(jsonPath("$.creditLimit").value(5000.0));
    }

    /**
     * Tests retrieving a specific credit card by ID when it does not exist.
     */
    @Test
    void testGetCreditCardById_NotFound() throws Exception {
        // Arrange: Simulate credit card not found
        when(creditCardService.getCreditCardById(1L)).thenReturn(Optional.empty());

        // Act & Assert: Perform GET request and expect 404 status
        mockMvc.perform(get("/api/credit-cards/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests creating a new credit card.
     */
    @Test
    void testCreateCreditCard() throws Exception {
        // Arrange: Simulate service creating a credit card
        when(creditCardService.createCreditCard(any(CreditCard.class)))
                .thenReturn(CreditCardMapper.toDomainFromDTO(testCardDTO));

        // Act & Assert: Perform POST request and verify response
        mockMvc.perform(post("/api/credit-cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "cardId": 1,
                    "cardNumber": "1234567890123456",
                    "expirationDate": "2025-12-31",
                    "cvv": "123",
                    "cardType": "Credit",
                    "creditLimit": 5000.0,
                    "currentBalance": 1000.0,
                    "customerId": 1
                }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(1))
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"));
    }

    /**
     * Tests updating an existing credit card.
     */
    @Test
    void testUpdateCreditCard() throws Exception {
        // Arrange: Simulate service updating a credit card
        when(creditCardService.updateCreditCard(eq(1L), any(CreditCard.class)))
                .thenReturn(Optional.of(CreditCardMapper.toDomainFromDTO(testCardDTO)));

        // Act & Assert: Perform PUT request and verify response
        mockMvc.perform(put("/api/credit-cards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "cardId": 1,
                    "cardNumber": "6543210987654321",
                    "expirationDate": "2026-01-01",
                    "cvv": "456",
                    "cardType": "Credit",
                    "creditLimit": 10000.0,
                    "currentBalance": 2000.0,
                    "customerId": 1
                }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(1))
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"));
    }

    /**
     * Tests deleting a credit card when it exists.
     */
    @Test
    void testDeleteCreditCard_Success() throws Exception {
        // Arrange: Simulate service confirming deletion
        when(creditCardService.deleteCreditCard(1L)).thenReturn(true);

        // Act & Assert: Perform DELETE request and expect 204 status
        mockMvc.perform(delete("/api/credit-cards/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    /**
     * Tests deleting a credit card when it does not exist.
     */
    @Test
    void testDeleteCreditCard_NotFound() throws Exception {
        // Arrange: Simulate credit card not found
        when(creditCardService.deleteCreditCard(1L)).thenReturn(false);

        // Act & Assert: Perform DELETE request and expect 404 status
        mockMvc.perform(delete("/api/credit-cards/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
