package com.***REMOVED***.spring_erp_platform.invoice.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("B2B")
public class B2BTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(BigDecimal baseAmount) {
        // Les entreprises déclarent la TVA elles-mêmes
        return BigDecimal.ZERO;
    }
}