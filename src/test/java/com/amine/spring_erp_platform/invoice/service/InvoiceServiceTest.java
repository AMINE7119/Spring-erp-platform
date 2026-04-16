package com.amine.spring_erp_platform.invoice.service;

import com.amine.spring_erp_platform.customer.entity.Customer;
import com.amine.spring_erp_platform.customer.service.CustomerService;
import com.amine.spring_erp_platform.invoice.entity.Invoice;
import com.amine.spring_erp_platform.invoice.entity.InvoiceStatus;
import com.amine.spring_erp_platform.invoice.repository.InvoiceRepository;
import com.amine.spring_erp_platform.invoice.strategy.TaxStrategy;
import com.amine.spring_erp_platform.invoice.strategy.TaxStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Obligatoire : Active Mockito pour ce fichier
class InvoiceServiceTest {

    // --- NOS FAUX COMPOSANTS (MOCKS) ---
    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private TaxStrategyFactory taxStrategyFactory;

    @Mock
    private TaxStrategy taxStrategy;

    // --- LE VRAI SERVICE À TESTER ---
    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void createInvoice_ShouldApplyTaxAndSave_WhenCustomerAndStrategyExist() {
        // ==========================================
        // 1. ARRANGE (Préparation)
        // ==========================================
        Long customerId = 1L;
        String taxType = "B2C";

        Customer mockCustomer = new Customer();
        mockCustomer.setId(customerId);

        Invoice inputInvoice = new Invoice();
        inputInvoice.setAmount(new BigDecimal("100.00")); // Montant HT

        // On dicte le comportement de nos Mocks
        when(customerService.getCustomerById(customerId)).thenReturn(mockCustomer);
        when(taxStrategyFactory.getStrategy(taxType)).thenReturn(taxStrategy);
        when(taxStrategy.calculateTax(new BigDecimal("100.00"))).thenReturn(new BigDecimal("20.00")); // Simule une TVA de 20%

        // Simule la base de données : elle renvoie simplement l'objet sauvegardé
        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ==========================================
        // 2. ACT (Exécution)
        // ==========================================
        Invoice result = invoiceService.createInvoice(customerId, inputInvoice, taxType);

        // ==========================================
        // 3. ASSERT (Vérifications)
        // ==========================================
        assertNotNull(result, "La facture ne doit pas être nulle");
        assertEquals(InvoiceStatus.DRAFT, result.getStatus(), "Le statut initial doit être DRAFT");
        assertEquals(new BigDecimal("120.00"), result.getAmount(), "Le montant TTC doit être exactement 120.00");
        assertNotNull(result.getInvoiceNumber(), "Le numéro de facture INV-xxx doit être généré");

        // Vérification ultime : S'assurer que la méthode save() a bien été appelée une seule fois
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }
}