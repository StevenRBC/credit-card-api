package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.core.model.Customer;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardEntity;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between different representations of Customer data.
 * This class facilitates transformations between:
 *  - Persistence layer (CustomerEntity)
 *  - Domain model (Customer)
 *  - Data Transfer Object (CustomerDTO)
 */
public class CustomerMapper {

    /**
     * Converts a CustomerEntity (persistence layer) to a Customer (domain model).
     *
     * @param entity The CustomerEntity to convert.
     * @return The corresponding Customer domain object, or null if the input is null.
     */
    public static Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;

        Customer customer = new Customer();
        customer.setId(entity.getCustomerId());
        customer.setFirstName(entity.getFirstName());
        customer.setLastName(entity.getLastName());
        customer.setEmail(entity.getEmail());

        // Map associated credit cards using CreditCardMapper
        if (entity.getCreditCards() != null) {
            customer.setCreditCards(entity.getCreditCards().stream()
                    .map(CreditCardMapper::toDomain) // Map each CreditCardEntity to CreditCard
                    .collect(Collectors.toList()));
        }

        return customer;
    }

    /**
     * Converts a Customer (domain model) to a CustomerEntity (persistence layer).
     *
     * @param customer The Customer domain object to convert.
     * @return The corresponding CustomerEntity, or null if the input is null.
     */
    public static CustomerEntity toEntity(Customer customer) {
        if (customer == null) return null;

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customer.getId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());

        // Map associated credit cards and set the relationship with the customer
        if (customer.getCreditCards() != null) {
            List<CreditCardEntity> creditCards = customer.getCreditCards().stream()
                    .map(card -> {
                        CreditCardEntity cardEntity = CreditCardMapper.toEntity(card);
                        cardEntity.setCustomer(entity); // Set the back-reference
                        return cardEntity;
                    })
                    .collect(Collectors.toList());
            entity.setCreditCards(creditCards);
        }

        return entity;
    }

    /**
     * Converts a Customer (domain model) to a CustomerDTO (Data Transfer Object).
     *
     * @param customer The Customer domain object to convert.
     * @return The corresponding CustomerDTO, or null if the input is null.
     */
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;

        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCreditCards() != null ?
                        customer.getCreditCards().stream()
                                .map(CreditCardMapper::toDTO) // Map each CreditCard to CreditCardDTO
                                .collect(Collectors.toList())
                        : null
        );
    }

    /**
     * Converts a CustomerDTO (Data Transfer Object) to a Customer (domain model).
     *
     * @param dto The CustomerDTO to convert.
     * @return The corresponding Customer domain object, or null if the input is null.
     */
    public static Customer toDomainFromDTO(CustomerDTO dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());

        // Map associated credit cards using CreditCardMapper
        if (dto.getCreditCards() != null) {
            customer.setCreditCards(dto.getCreditCards().stream()
                    .map(cardDTO -> {
                        CreditCard card = CreditCardMapper.toDomainFromDTO(cardDTO);
                        card.setCustomerId(customer.getId()); // Set the customer ID in the credit card
                        return card;
                    })
                    .collect(Collectors.toList()));
        }

        return customer;
    }
}
