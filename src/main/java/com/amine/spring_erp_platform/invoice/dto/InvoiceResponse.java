package com.amine.spring_erp_platform.invoice.dto;

import com.amine.spring_erp_platform.invoice.entity.InvoiceStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceResponse {
    private Long id;
    private String invoiceNumber;
    private BigDecimal amount;
    private LocalDate issueDate;
    private InvoiceStatus status;
    // 💡 Astuce de pro : On ne renvoie que l'ID du client, pas tout l'objet Customer avec ses infos privées !
    private Long customerId;
}