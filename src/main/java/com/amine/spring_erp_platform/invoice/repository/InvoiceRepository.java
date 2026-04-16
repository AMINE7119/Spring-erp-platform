package com.***REMOVED***.spring_erp_platform.invoice.repository;

import com.***REMOVED***.spring_erp_platform.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Spring Data JPA va automatiquement générer le SQL :
    // SELECT * FROM invoices WHERE customer_id = ?
    List<Invoice> findByCustomerId(Long customerId);

    // Utile pour vérifier si un numéro de facture existe déjà avant de le créer
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
}