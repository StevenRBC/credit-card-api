package com.creditcard.management.credit_card_api.application.service;

import com.creditcard.management.credit_card_api.application.port.out.CreditCardRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the CreditCardService class.
 * Utilizes Mockito for mocking dependencies and testing service methods.
 */
class CreditCardServiceTest {

    @Mock
    private CreditCardRepositoryPort creditCardRepositoryPort; // Mocked repository port

    @InjectMocks
    private CreditCardService creditCardService; // Service under test

    private CreditCard testCard; // Test credit card object

    /**
     * Sets up the test environment before each test.
     * Initializes Mockito annotations and prepares a test credit card object.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a test credit card object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date());

        testCard = new CreditCard();
        testCard.setCardNumber("1234567890123456");
        testCard.setExpirationDate(formattedDate);
        testCard.setCvv("123");
        testCard.setCreditLimit(5000.0);
        testCard.setCurrentBalance(1000.0);
    }

    /**
     * Tests the retrieval of all credit cards.
     * Verifies that the service interacts with the repository and returns the expected results.
     */
    @Test
    void testGetAllCreditCards() {
        // Arrange: Simulate repository behavior
        when(creditCardRepositoryPort.findAll()).thenReturn(Arrays.asList(testCard));

        // Act: Call the service method
        List<CreditCard> cards = creditCardService.getAllCreditCards();

        // Assert: Verify results
        assertFalse(cards.isEmpty());
        assertEquals(1, cards.size());
        assertEquals("1234567890123456", cards.get(0).getCardNumber());
        verify(creditCardRepositoryPort, times(1)).findAll();
    }

    /**
     * Tests the retrieval of a credit card by ID when it exists.
     */
    @Test
    void testGetCreditCardById_Found() {
        // Arrange: Simulate repository returning a credit card
        when(creditCardRepositoryPort.findById(1L)).thenReturn(Optional.of(testCard));

        // Act: Call the service method
        Optional<CreditCard> result = creditCardService.getCreditCardById(1L);

        // Assert: Verify results
        assertTrue(result.isPresent());
        assertEquals("1234567890123456", result.get().getCardNumber());
        verify(creditCardRepositoryPort, times(1)).findById(1L);
    }

    /**
     * Tests the retrieval of a credit card by ID when it does not exist.
     */
    @Test
    void testGetCreditCardById_NotFound() {
        // Arrange: Simulate repository returning empty
        when(creditCardRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<CreditCard> result = creditCardService.getCreditCardById(1L);

        // Assert
        assertTrue(result.isEmpty());
        verify(creditCardRepositoryPort, times(1)).findById(1L);
    }

    /**
     * Tests the creation of a new credit card.
     */
    @Test
    void testCreateCreditCard() {
        // Arrange: Simulate repository saving a credit card
        when(creditCardRepositoryPort.save(testCard)).thenReturn(testCard);

        // Act: Call the service method
        CreditCard createdCard = creditCardService.createCreditCard(testCard);

        // Assert: Verify the card was created correctly
        assertNotNull(createdCard);
        assertEquals("1234567890123456", createdCard.getCardNumber());
        verify(creditCardRepositoryPort, times(1)).save(testCard);
    }

    /**
     * Tests updating an existing credit card successfully.
     */
    @Test
    void testUpdateCreditCard_Success() {
        // Arrange: Simulate finding and updating a credit card
        when(creditCardRepositoryPort.findById(1L)).thenReturn(Optional.of(testCard));
        when(creditCardRepositoryPort.save(any(CreditCard.class))).thenReturn(testCard);

        CreditCard updatedCard = new CreditCard();
        updatedCard.setCardNumber("6543210987654321");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date());
        updatedCard.setExpirationDate(formattedDate);
        updatedCard.setCvv("456");
        updatedCard.setCreditLimit(10000.0);
        updatedCard.setCurrentBalance(2000.0);

        // Act: Call the service method
        Optional<CreditCard> result = creditCardService.updateCreditCard(1L, updatedCard);

        // Assert: Verify the card was updated correctly
        assertTrue(result.isPresent());
        assertEquals("6543210987654321", result.get().getCardNumber());
        verify(creditCardRepositoryPort, times(1)).findById(1L);
        verify(creditCardRepositoryPort, times(1)).save(any(CreditCard.class));
    }

    /**
     * Tests deleting an existing credit card successfully.
     */
    @Test
    void testDeleteCreditCard_Success() {
        // Arrange: Simulate repository confirming card existence
        when(creditCardRepositoryPort.existsById(1L)).thenReturn(true);
        doNothing().when(creditCardRepositoryPort).deleteById(1L);

        // Act: Call the service method
        boolean result = creditCardService.deleteCreditCard(1L);

        // Assert: Verify the card was deleted
        assertTrue(result);
        verify(creditCardRepositoryPort, times(1)).existsById(1L);
        verify(creditCardRepositoryPort, times(1)).deleteById(1L);
    }

    /**
     * Tests attempting to delete a non-existent credit card.
     */
    @Test
    void testDeleteCreditCard_NotFound() {
        // Arrange: Simulate repository confirming card does not exist
        when(creditCardRepositoryPort.existsById(1L)).thenReturn(false);

        // Act
        boolean result = creditCardService.deleteCreditCard(1L);

        // Assert
        assertFalse(result);
        verify(creditCardRepositoryPort, times(1)).existsById(1L);
        verify(creditCardRepositoryPort, never()).deleteById(1L);
    }
}
