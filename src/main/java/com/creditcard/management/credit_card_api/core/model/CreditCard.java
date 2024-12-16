package com.creditcard.management.credit_card_api.core.model;

/**
 * Domain model class representing a Credit Card.
 * This class encapsulates the core business logic attributes for a credit card,
 * decoupled from persistence and presentation layers.
 */
public class CreditCard {

    // Unique identifier for the credit card
    private Long id;

    // Credit card number (16 digits, typically masked for security purposes)
    private String cardNumber;

    // Expiration date of the credit card in YYYY-MM-DD format
    private String expirationDate;

    // Card Verification Value (CVV) - a 3-digit security code
    private String cvv;

    // Type of the card: e.g., "Credit" or "Debit"
    private String cardType;

    // Maximum credit limit for the card
    private Double creditLimit;

    // Current balance on the credit card
    private Double currentBalance;

    // Identifier of the customer who owns the credit card
    private Long customerId;

    /**
     * Default constructor for creating an empty CreditCard object.
     */
    public CreditCard() {}

    // Getters and Setters

    /**
     * Gets the unique identifier for the credit card.
     *
     * @return The credit card ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the credit card.
     *
     * @param id The credit card ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the credit card number.
     *
     * @return The credit card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the credit card number.
     *
     * @param cardNumber The credit card number to set.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the expiration date of the credit card.
     *
     * @return The expiration date in YYYY-MM-DD format.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the credit card.
     *
     * @param expirationDate The expiration date to set (format: YYYY-MM-DD).
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the Card Verification Value (CVV).
     *
     * @return The 3-digit CVV code.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the Card Verification Value (CVV).
     *
     * @param cvv The CVV code to set.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Gets the type of the credit card.
     *
     * @return The card type (e.g., "Credit" or "Debit").
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the type of the credit card.
     *
     * @param cardType The card type to set (e.g., "Credit" or "Debit").
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets the credit limit for the card.
     *
     * @return The credit limit.
     */
    public Double getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets the credit limit for the card.
     *
     * @param creditLimit The credit limit to set.
     */
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * Gets the current balance on the credit card.
     *
     * @return The current balance.
     */
    public Double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the current balance on the credit card.
     *
     * @param currentBalance The current balance to set.
     */
    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * Gets the customer ID associated with the credit card.
     *
     * @return The customer ID.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID associated with the credit card.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
