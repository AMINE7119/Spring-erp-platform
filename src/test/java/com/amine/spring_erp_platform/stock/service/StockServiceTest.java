package com.***REMOVED***.spring_erp_platform.stock.service;

import com.***REMOVED***.spring_erp_platform.stock.entity.MovementType;
import com.***REMOVED***.spring_erp_platform.stock.entity.Product;
import com.***REMOVED***.spring_erp_platform.stock.entity.StockMovement;
import com.***REMOVED***.spring_erp_platform.stock.repository.ProductRepository;
import com.***REMOVED***.spring_erp_platform.stock.repository.StockMovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockMovementRepository movementRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void addMovement_Out_ShouldThrowException_WhenStockIsInsufficient() {
        // ==========================================
        // 1. ARRANGE (Préparation)
        // ==========================================
        Long productId = 1L;

        // On crée un faux produit qui n'a que 5 unités en stock
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setCurrentQuantity(5);

        // On essaie de faire une vente de 10 unités (ça devrait exploser !)
        StockMovement outMovement = new StockMovement();
        outMovement.setMovementType(MovementType.OUT);
        outMovement.setQuantity(10);

        // On dit au mock : quand le service cherche le produit, donne-lui notre faux produit
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // ==========================================
        // 2 & 3. ACT & ASSERT (Exécution et Vérification de l'Exception)
        // ==========================================
        // On utilise assertThrows pour vérifier que l'appel déclenche bien une RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            stockService.addMovement(productId, outMovement);
        });

        // On peut même vérifier que le message d'erreur est exactement le bon !
        assertTrue(exception.getMessage().contains("Stock insuffisant"));

        // On vérifie que la méthode save n'a JAMAIS été appelée (puisque ça a planté avant)
        verify(movementRepository, never()).save(any());
        verify(productRepository, never()).save(any());
    }
}