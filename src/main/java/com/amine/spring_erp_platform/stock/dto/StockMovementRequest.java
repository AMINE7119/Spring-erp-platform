package com.***REMOVED***.spring_erp_platform.stock.dto;

import com.***REMOVED***.spring_erp_platform.stock.entity.MovementType;
import lombok.Data;

@Data
public class StockMovementRequest {
    private MovementType movementType; // "IN" ou "OUT"
    private Integer quantity;
    private String reason;
}