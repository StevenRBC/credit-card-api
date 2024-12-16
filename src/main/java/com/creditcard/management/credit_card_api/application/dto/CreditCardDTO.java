package com.creditcard.management.credit_card_api.application.dto;


import java.math.BigDecimal;
import java.sql.Date;

public class CreditCardDTO {

    private Long cardId; // Primary Key
    private Long customerId; // Foreign Key referencing Customer
    private String cardNumber;
    private Date expirationDate;
    private String cvv;
    private String cardType; // Example: 'Credit' or 'Debit'
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;

    public CreditCardDTO(Long cardId, Long customerId, String cardNumber, Date expirationDate, String cvv,
                         String cardType, BigDecimal creditLimit, BigDecimal currentBalance) {
        this.cardId = cardId;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardType = cardType;
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    // Getters and Setters
    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }
}