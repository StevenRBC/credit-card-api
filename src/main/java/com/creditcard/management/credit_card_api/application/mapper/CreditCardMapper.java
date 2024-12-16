package com.creditcard.management.credit_card_api.application.mapper;

import com.creditcard.management.credit_card_api.application.dto.CreditCardDTO;
import com.creditcard.management.credit_card_api.core.model.CreditCard;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CreditCardEntity;
import com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence.CustomerEntity;

import java.sql.Date;

/**
 * Mapper class for converting between different representations of CreditCard data.
 * This class facilitates transformations between:
 *  - Persistence layer (CreditCardEntity)
 *  - Domain model (CreditCard)
 *  - Data Transfer Object (CreditCardDTO)
 */
public class CreditCardMapper {

    /**
     * Converts a CreditCardEntity (persistence layer) to a CreditCard (domain model).
     *
     * @param entity The CreditCardEntity to convert.
     * @return The corresponding CreditCard domain object, or null if the input is null.
     */
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

    /**
     * Converts a CreditCard (domain model) to a CreditCardEntity (persistence layer).
     *
     * @param creditCard The CreditCard domain object to convert.
     * @return The corresponding CreditCardEntity, or null if the input is null.
     */
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

    /**
     * Converts a CreditCard (domain model) to a CreditCardDTO (Data Transfer Object).
     *
     * @param creditCard The CreditCard domain object to convert.
     * @return The corresponding CreditCardDTO, or null if the input is null.
     */
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

    /**
     * Converts a CreditCardDTO (Data Transfer Object) to a CreditCard (domain model).
     *
     * @param dto The CreditCardDTO to convert.
     * @return The corresponding CreditCard domain object, or null if the input is null.
     */
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
