package com.creditcard.management.credit_card_api.infrastructure.adapter.out.persistence;

import com.creditcard.management.credit_card_api.application.port.out.CustomerRepositoryPort;
import com.creditcard.management.credit_card_api.core.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryPort {

}