package com.amine.spring_erp_platform.stock.service;

import com.amine.spring_erp_platform.stock.entity.MovementType;
import com.amine.spring_erp_platform.stock.entity.Product;
import com.amine.spring_erp_platform.stock.entity.StockMovement;
import com.amine.spring_erp_platform.stock.repository.ProductRepository;
import com.amine.spring_erp_platform.stock.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductRepository productRepository;
    private final StockMovementRepository movementRepository;

    // --- GESTION DES PRODUITS ---

    public Product createProduct(Product product) {
        // À la création, un produit a toujours 0 en stock
        product.setCurrentQuantity(0);
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'ID : " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // --- GESTION DES MOUVEMENTS DE STOCK ---

    @Transactional // 💡 Crucial : garantit que le produit ET le mouvement sont sauvegardés ensemble, ou aucun des deux.
    public StockMovement addMovement(Long productId, StockMovement movement) {
        // 1. On récupère le produit concerné
        Product product = getProductById(productId);

        // 2. On applique la logique selon le type de mouvement (IN ou OUT)
        if (movement.getMovementType() == MovementType.IN) {
            product.setCurrentQuantity(product.getCurrentQuantity() + movement.getQuantity());
        } else if (movement.getMovementType() == MovementType.OUT) {
            // Règle métier stricte : on ne peut pas avoir un stock négatif !
            if (product.getCurrentQuantity() < movement.getQuantity()) {
                throw new RuntimeException("Stock insuffisant ! Quantité actuelle : " + product.getCurrentQuantity());
            }
            product.setCurrentQuantity(product.getCurrentQuantity() - movement.getQuantity());
        }

        // 3. On finalise l'objet mouvement
        movement.setProduct(product);
        movement.setMovementDate(LocalDateTime.now());

        // 4. On sauvegarde les modifications (Spring Data JPA va mettre à jour le produit et insérer le mouvement)
        productRepository.save(product);
        return movementRepository.save(movement);
    }

    public List<StockMovement> getMovementsByProduct(Long productId) {
        // Vérifie que le produit existe d'abord
        getProductById(productId);
        return movementRepository.findByProductId(productId);
    }
}
