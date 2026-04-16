package com.amine.spring_erp_platform.invoice.service;

import com.amine.spring_erp_platform.customer.entity.Customer;
import com.amine.spring_erp_platform.customer.service.CustomerService;
import com.amine.spring_erp_platform.invoice.entity.Invoice;
import com.amine.spring_erp_platform.invoice.entity.InvoiceStatus;
import com.amine.spring_erp_platform.invoice.repository.InvoiceRepository;
import com.amine.spring_erp_platform.invoice.strategy.TaxStrategy;
import com.amine.spring_erp_platform.invoice.strategy.TaxStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerService customerService;

    // 💡 2. La déclaration est bien là ! Spring va l'injecter automatiquement.
    private final TaxStrategyFactory taxStrategyFactory;

    // Règle métier 1 : Créer une facture pour un client spécifique
    public Invoice createInvoice(Long customerId, Invoice invoice, String taxType) {
        Customer customer = customerService.getCustomerById(customerId);
        invoice.setCustomer(customer);

        invoice.setStatus(InvoiceStatus.DRAFT);
        invoice.setIssueDate(LocalDate.now());
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        // --- DÉBUT DU PATTERN STRATEGY ---
        // 1. On demande à l'usine la bonne formule de calcul ("B2C", "B2B", etc.)
        TaxStrategy strategy = taxStrategyFactory.getStrategy(taxType);

        // 2. On calcule la taxe à partir du montant HT
        BigDecimal baseAmountHT = invoice.getAmount();
        BigDecimal taxAmount = strategy.calculateTax(baseAmountHT);

        // 3. On enregistre le prix final TTC (HT + Taxe)
        invoice.setAmount(baseAmountHT.add(taxAmount));
        // --- FIN DU PATTERN STRATEGY ---

        return invoiceRepository.save(invoice);
    }

    // Règle métier 2 : Retrouver toutes les factures d'un client
    public List<Invoice> getInvoicesByCustomerId(Long customerId) {
        // On vérifie d'abord que le client existe
        customerService.getCustomerById(customerId);
        return invoiceRepository.findByCustomerId(customerId);
    }

    // Règle métier 3 : Retrouver une facture par son ID
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture introuvable avec l'ID : " + id));
    }

    // Règle métier 4 : Changer le statut d'une facture (ex: passer de DRAFT à PAID)
    public Invoice updateInvoiceStatus(Long id, InvoiceStatus newStatus) {
        Invoice invoice = getInvoiceById(id);
        invoice.setStatus(newStatus);
        return invoiceRepository.save(invoice);
    }
}