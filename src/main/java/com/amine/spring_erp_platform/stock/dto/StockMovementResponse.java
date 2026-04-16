package com.amine.spring_erp_platform.stock.dto;

import com.amine.spring_erp_platform.stock.entity.MovementType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StockMovementResponse {
    private Long id;
    private Long productId; // On ne renvoie que l'ID du produit pour éviter une boucle infinie JSON
    private MovementType movementType;
    private Integer quantity;
    private LocalDateTime movementDate;
    private String reason;
}