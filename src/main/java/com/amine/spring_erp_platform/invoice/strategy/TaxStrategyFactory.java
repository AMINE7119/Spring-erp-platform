package com.***REMOVED***.spring_erp_platform.invoice.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaxStrategyFactory {

    // Spring est magique : il va automatiquement injecter nos 3 stratégies ici !
    // La clé de la Map sera le nom donné dans le @Component ("B2C", "B2B", "EXEMPT")
    private final Map<String, TaxStrategy> strategies;

    public TaxStrategy getStrategy(String customerType) {
        TaxStrategy strategy = strategies.get(customerType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Type de client inconnu pour le calcul des taxes : " + customerType);
        }
        return strategy;
    }
}