package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardEntity;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerEntity;

import java.sql.Date;

public class CreditCardMapper {

    // Convertir de CreditCardEntity a CreditCard (persistencia → modelo de negocio)
    public static CreditCard toDomain(CreditCardEntity entity) {
        if (entity == null) return null;

        CreditCard creditCard = new CreditCard();
        creditCard.setId(entity.getCardId());
        creditCard.setCardNumber(entity.getCardNumber());
        creditCard.setExpirationDate(entity.getExpirationDate() != null
                ? entity.getExpirationDate().toString()
                : null);
        creditCard.setCvv(entity.getCvv());
        creditCard.setCardType(entity.getCardType());
        creditCard.setCreditLimit(entity.getCreditLimit());
        creditCard.setCurrentBalance(entity.getCurrentBalance());
        creditCard.setCustomerId(entity.getCustomer() != null ? entity.getCustomer().getCustomerId() : null);

        return creditCard;
    }

    // Convertir de CreditCard a CreditCardEntity (modelo de negocio → persistencia)
    public static CreditCardEntity toEntity(CreditCard creditCard) {
        if (creditCard == null) return null;

        CreditCardEntity entity = new CreditCardEntity();
        entity.setCardId(creditCard.getId());
        entity.setCardNumber(creditCard.getCardNumber());
        entity.setExpirationDate(creditCard.getExpirationDate() != null
                ? Date.valueOf(creditCard.getExpirationDate())
                : null);
        entity.setCvv(creditCard.getCvv());
        entity.setCardType(creditCard.getCardType());
        entity.setCreditLimit(creditCard.getCreditLimit());
        entity.setCurrentBalance(creditCard.getCurrentBalance());

        if (creditCard.getCustomerId() != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerId(creditCard.getCustomerId());
            entity.setCustomer(customerEntity);
        }

        return entity;
    }

    // Convertir de CreditCard a CreditCardDTO (modelo de negocio → DTO)
    public static CreditCardDTO toDTO(CreditCard creditCard) {
        if (creditCard == null) return null;

        CreditCardDTO dto = new CreditCardDTO();
        dto.setCardId(creditCard.getId());
        dto.setCardNumber(creditCard.getCardNumber());
        dto.setExpirationDate(creditCard.getExpirationDate());
        dto.setCvv(creditCard.getCvv());
        dto.setCardType(creditCard.getCardType());
        dto.setCreditLimit(creditCard.getCreditLimit());
        dto.setCurrentBalance(creditCard.getCurrentBalance());
        dto.setCustomerId(creditCard.getCustomerId());

        return dto;
    }

    // Convertir de CreditCardDTO a CreditCard (DTO → modelo de negocio)
    public static CreditCard toDomainFromDTO(CreditCardDTO dto) {
        if (dto == null) return null;

        CreditCard creditCard = new CreditCard();
        creditCard.setId(dto.getCardId());
        creditCard.setCardNumber(dto.getCardNumber());
        creditCard.setExpirationDate(dto.getExpirationDate());
        creditCard.setCvv(dto.getCvv());
        creditCard.setCardType(dto.getCardType());
        creditCard.setCreditLimit(dto.getCreditLimit());
        creditCard.setCurrentBalance(dto.getCurrentBalance());
        creditCard.setCustomerId(dto.getCustomerId());

        return creditCard;
    }
}