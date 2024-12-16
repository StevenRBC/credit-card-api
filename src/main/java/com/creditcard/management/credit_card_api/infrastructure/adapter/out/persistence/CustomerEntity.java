package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing the Customer table in the database.
 * This class maps to the "customer" table and provides fields for customer details and their relationships.
 */
@Entity
@Table(name = "customer")
public class CustomerEntity {

    // Unique identifier for the customer (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    // Customer's first name (cannot be null)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    // Customer's last name (cannot be null)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    // Customer's email address (must be unique and not null)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // One-to-Many relationship with CreditCardEntity
    // Each customer can have multiple credit cards
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCardEntity> creditCards = new ArrayList<>();

    // Getters and Setters

    /**
     * Gets the unique identifier for the customer.
     *
     * @return The customer's ID.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
     * @return A list of CreditCardEntity objects linked to this customer.
     */
    public List<CreditCardEntity> getCreditCards() {
        return creditCards;
    }

    /**
     * Sets the list of credit cards associated with the customer.
     *
     * @param creditCards The list of CreditCardEntity objects to associate with the customer.
     */
    public void setCreditCards(List<CreditCardEntity> creditCards) {
        this.creditCards = creditCards;
    }
}
