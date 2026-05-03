package com.amine.spring_erp_platform.stock.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String reference;
    private String name;
    private BigDecimal price;
    private Integer currentQuantity; // Sécurisé : mis à jour uniquement par le système
}
