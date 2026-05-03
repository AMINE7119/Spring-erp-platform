package com.amine.spring_erp_platform.invoice.strategy;

import java.math.BigDecimal;

public interface TaxStrategy {
    // Calcule le montant de la taxe à partir du montant hors taxe (HT)
    BigDecimal calculateTax(BigDecimal baseAmount);
}
