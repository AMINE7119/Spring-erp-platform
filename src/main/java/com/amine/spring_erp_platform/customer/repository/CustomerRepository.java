package com.amine.spring_erp_platform.customer.repository;

import com.amine.spring_erp_platform.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Spring Data JPA comprend tout seul qu'il doit générer une requête SQL
    // du type : SELECT * FROM customers WHERE email = ?
    Optional<Customer> findByEmail(String email);

}
