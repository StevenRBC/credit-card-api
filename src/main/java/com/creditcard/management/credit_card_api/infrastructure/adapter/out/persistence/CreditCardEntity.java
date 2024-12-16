package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Entity class representing the CreditCard table in the database.
 * This class maps to the "creditcard" table and provides fields
 * for credit card details and their relationships.
 */
@Entity
@Table(name = "creditcard")
public class CreditCardEntity {

    // Unique identifier for the credit card (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    // Credit card number (must be unique and not null)
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    // Expiration date of the credit card
    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    // CVV (Card Verification Value) - a 3-digit security code
    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    // Type of the card (e.g., "Credit" or "Debit")
    @Column(name = "card_type", nullable = false)
    private String cardType;

    // Maximum credit limit allowed on the card
    @Column(name = "credit_limit", nullable = false)
    private Double creditLimit;

    // Current balance on the card
    @Column(name = "current_balance", nullable = false)
    private Double currentBalance;

    // Many-to-One relationship with the Customer entity
    // Each credit card is linked to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    /**
     * Default constructor for creating an empty CreditCardEntity object.
     */
    public CreditCardEntity() {}

    // Getters and Setters

    /**
     * Gets the unique identifier of the credit card.
     *
     * @return The card ID.
     */
    public Long getCardId() {
        return cardId;
    }

    /**
     * Sets the unique identifier of the credit card.
     *
     * @param cardId The card ID to set.
     */
    public void setCardId(Long cardId) {
        this.cardId = cardId;
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
     * @return The expiration date.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the credit card.
     *
     * @param expirationDate The expiration date to set.
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the CVV (Card Verification Value) of the credit card.
     *
     * @return The CVV code.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the CVV (Card Verification Value) of the credit card.
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
     * Gets the credit limit of the card.
     *
     * @return The credit limit.
     */
    public Double getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets the credit limit of the card.
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
     * Gets the customer associated with the credit card.
     *
     * @return The CustomerEntity linked to this credit card.
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * Sets the customer associated with the credit card.
     *
     * @param customer The CustomerEntity to link to this credit card.
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
