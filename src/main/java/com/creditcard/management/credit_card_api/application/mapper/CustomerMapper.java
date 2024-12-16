package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CustomerDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.core.model.Customer;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardEntity;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    // Convertir de CustomerEntity a Customer (persistencia → modelo de negocio)
    public static Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;

        Customer customer = new Customer();
        customer.setId(entity.getCustomerId());
        customer.setFirstName(entity.getFirstName());
        customer.setLastName(entity.getLastName());
        customer.setEmail(entity.getEmail());

        if (entity.getCreditCards() != null) {
            customer.setCreditCards(entity.getCreditCards().stream()
                    .map(CreditCardMapper::toDomain) // Llamada al mapper de CreditCard
                    .collect(Collectors.toList()));
        }

        return customer;
    }

    // Convertir de Customer a CustomerEntity (modelo de negocio → persistencia)
    public static CustomerEntity toEntity(Customer customer) {
        if (customer == null) return null;

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customer.getId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());

        if (customer.getCreditCards() != null) {
            List<CreditCardEntity> creditCards = customer.getCreditCards().stream()
                    .map(card -> {
                        CreditCardEntity cardEntity = CreditCardMapper.toEntity(card);
                        cardEntity.setCustomer(entity);
                        return cardEntity;
                    })
                    .collect(Collectors.toList());
            entity.setCreditCards(creditCards);
        }

        return entity;
    }

    // Convertir de Customer a CustomerDTO (modelo de negocio → DTO)
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;

        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCreditCards() != null ?
                        customer.getCreditCards().stream()
                                .map(CreditCardMapper::toDTO) // Llamada al mapper de CreditCard
                                .collect(Collectors.toList())
                        : null
        );
    }

    // Convertir de CustomerDTO a Customer (DTO → modelo de negocio)
    public static Customer toDomainFromDTO(CustomerDTO dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());

        if (dto.getCreditCards() != null) {
            customer.setCreditCards(dto.getCreditCards().stream()
                    .map(cardDTO -> {
                        CreditCard card = CreditCardMapper.toDomainFromDTO(cardDTO);
                        card.setCustomerId(customer.getId());
                        return card;
                    })
                    .collect(Collectors.toList()));
        }

        return customer;
    }
}