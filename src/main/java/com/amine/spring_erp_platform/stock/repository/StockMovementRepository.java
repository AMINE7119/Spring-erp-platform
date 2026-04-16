package com.***REMOVED***.spring_erp_platform.stock.repository;

import com.***REMOVED***.spring_erp_platform.stock.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    // Utile pour voir l'historique d'un produit spécifique
    List<StockMovement> findByProductId(Long productId);
}