package com.amine.spring_erp_platform.invoice.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("B2C")
public class B2CTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(BigDecimal baseAmount) {
        // 20% de TVA
        return baseAmount.multiply(new BigDecimal("0.20"));
    }
}
