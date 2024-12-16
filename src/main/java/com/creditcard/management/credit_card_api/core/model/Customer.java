package com.creditcard.management.credit_card_api.core.model;

import java.util.List;

/**
 * Domain model class representing a Customer.
 * This class encapsulates the core business logic attributes for a customer,
 * decoupled from persistence and presentation layers.
 */
public class Customer {

    // Unique identifier for the customer
    private Long id;

    // Customer's first name
    private String firstName;

    // Customer's last name
    private String lastName;

    // Customer's email address
    private String email;

    // List of credit cards associated with the customer
    private List<CreditCard> creditCards;

    /**
     * Default constructor for creating an empty Customer object.
     */
    public Customer() {}

    /**
     * Gets the unique identifier for the customer.
     *
     * @return The customer's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param id The customer ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return The customer's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return The customer's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of credit cards associated with the customer.
     *
     * @return A list of the customer's CreditCard objects.
     */
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    /**
     * Sets the list of credit cards associated with the customer.
     *
     * @param creditCards The list of CreditCard objects to associate with the customer.
     */
    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
