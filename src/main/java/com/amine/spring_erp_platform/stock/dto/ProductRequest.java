package com.amine.spring_erp_platform.stock.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String reference;
    private String name;
    private BigDecimal price;
}