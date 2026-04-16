package com.***REMOVED***.spring_erp_platform.invoice.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("EXEMPT")
public class ExemptTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(BigDecimal baseAmount) {
        // Aucune taxe
        return BigDecimal.ZERO;
    }
}