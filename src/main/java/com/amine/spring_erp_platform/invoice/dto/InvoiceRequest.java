package com.***REMOVED***.spring_erp_platform.invoice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class InvoiceRequest {
    // Le client a juste besoin de nous envoyer le montant pour créer la facture
    private BigDecimal amount;
    // Nouveau champ : Le client API devra préciser "B2C", "B2B" ou "EXEMPT"
    private String taxType;
}