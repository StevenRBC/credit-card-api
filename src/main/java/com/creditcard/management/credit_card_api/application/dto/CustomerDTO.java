package com.creditcard.management.credit_card_api.application.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for Customer information.
 * This class is used to transfer customer-related data between the client and server layers,
 * avoiding direct exposure of internal models or entities.
 */
public class CustomerDTO {

    // Unique identifier for the customer
    private Long customerId;

    // Customer's first name
    private String firstName;

    // Customer's last name
    private String lastName;

    // Customer's email address
    private String email;

    // List of associated credit cards for the customer
    private List<CreditCardDTO> creditCards;

    /**
     * Constructor to initialize a CustomerDTO with all its fields.
     * @param customerId   The unique identifier of the customer.
     * @param firstName    The first name of the customer.
     * @param lastName     The last name of the customer.
     * @param email        The email address of the customer.
     * @param creditCards  A list of associated credit card DTOs.
     */
    public CustomerDTO(Long customerId, String firstName, String lastName, String email, List<CreditCardDTO> creditCards) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creditCards = creditCards;
    }

    /**
     * Gets the unique identifier for the customer.
     * @return The customer ID.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier for the customer.
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the first name of the customer.
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the customer.
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the customer.
     * @return The email address of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of credit cards associated with the customer.
     * @return A list of CreditCardDTOs.
     */
    public List<CreditCardDTO> getCreditCards() {
        return creditCards;
    }

    /**
     * Sets the list of credit cards associated with the customer.
     * @param creditCards A list of CreditCardDTOs to set.
     */
    public void setCreditCards(List<CreditCardDTO> creditCards) {
        this.creditCards = creditCards;
    }
}
