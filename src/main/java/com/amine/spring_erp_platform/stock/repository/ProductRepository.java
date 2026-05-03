package com.amine.spring_erp_platform.stock.repository;

import com.amine.spring_erp_platform.stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Utile pour chercher un produit par sa référence (ex: "REF-123")
    Optional<Product> findByReference(String reference);
}
