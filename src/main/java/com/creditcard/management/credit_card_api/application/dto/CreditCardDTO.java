package com.creditcard.management.credit_card_api.application.dto;

/**
 * Data Transfer Object (DTO) for Credit Card information.
 * This class is used to transfer credit card data between different layers of the application,
 * such as controllers and services, without exposing the internal model or entity details.
 */
public class CreditCardDTO {
    // Unique identifier for the credit card
    private Long cardId;

    // Credit card number (16 digits, typically masked for security purposes)
    private String cardNumber;

    // Expiration date of the credit card in YYYY-MM-DD format
    private String expirationDate;

    // Card Verification Value (3-digit security code)
    private String cvv;

    // Type of the card: e.g., "Credit" or "Debit"
    private String cardType;

    // Credit limit for the card (maximum amount of credit allowed)
    private Double creditLimit;

    // Current balance on the credit card
    private Double currentBalance;

    // Identifier for the customer to whom the card belongs
    private Long customerId;

    // Getters and setters

    /**
     * Gets the unique identifier for the credit card.
     * @return the card ID
     */
    public Long getCardId() {
        return cardId;
    }

    /**
     * Sets the unique identifier for the credit card.
     * @param cardId the card ID to set
     */
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    /**
     * Gets the credit card number.
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the credit card number.
     * @param cardNumber the card number to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the expiration date of the credit card.
     * @return the expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the credit card.
     * @param expirationDate the expiration date to set (format: YYYY-MM-DD)
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the CVV (Card Verification Value) of the credit card.
     * @return the CVV
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the CVV (Card Verification Value) of the credit card.
     * @param cvv the CVV to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Gets the type of the credit card.
     * @return the card type
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the type of the credit card (e.g., "Credit" or "Debit").
     * @param cardType the card type to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets the credit limit for the credit card.
     * @return the credit limit
     */
    public Double getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets the credit limit for the credit card.
     * @param creditLimit the credit limit to set
     */
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * Gets the current balance on the credit card.
     * @return the current balance
     */
    public Double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the current balance on the credit card.
     * @param currentBalance the current balance to set
     */
    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * Gets the customer ID associated with this credit card.
     * @return the customer ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID associated with this credit card.
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
