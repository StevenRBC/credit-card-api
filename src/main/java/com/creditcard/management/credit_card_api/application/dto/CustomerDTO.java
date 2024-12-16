package com.creditcard.management.credit_card_api.application.dto;

import java.util.List;

public class CustomerDTO {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private List<CreditCardDTO> creditCards;

    public CustomerDTO(Long customerId, String firstName, String lastName, String email, List<CreditCardDTO> creditCards) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creditCards = creditCards;
    }

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CreditCardDTO> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardDTO> creditCards) {
        this.creditCards = creditCards;
    }
}