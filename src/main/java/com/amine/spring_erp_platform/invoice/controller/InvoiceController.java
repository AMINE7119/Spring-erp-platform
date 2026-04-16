package com.***REMOVED***.spring_erp_platform.invoice.controller;

import com.***REMOVED***.spring_erp_platform.invoice.dto.InvoiceMapper;
import com.***REMOVED***.spring_erp_platform.invoice.dto.InvoiceRequest;
import com.***REMOVED***.spring_erp_platform.invoice.dto.InvoiceResponse;
import com.***REMOVED***.spring_erp_platform.invoice.entity.Invoice;
import com.***REMOVED***.spring_erp_platform.invoice.entity.InvoiceStatus;
import com.***REMOVED***.spring_erp_platform.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper mapper; // On injecte notre magicien

    // 1. Créer
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<InvoiceResponse> createInvoice(
            @PathVariable Long customerId,
            @RequestBody InvoiceRequest request) {

        Invoice invoiceToSave = mapper.toEntity(request);

        // On extrait le taxType de la requête pour l'envoyer au service
        Invoice savedInvoice = invoiceService.createInvoice(customerId, invoiceToSave, request.getTaxType());

        return new ResponseEntity<>(mapper.toDto(savedInvoice), HttpStatus.CREATED);
    }

    // 2. Récupérer toutes les factures d'un client
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<InvoiceResponse>> getInvoicesByCustomerId(@PathVariable Long customerId) {
        List<InvoiceResponse> responses = invoiceService.getInvoicesByCustomerId(customerId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}