package com.***REMOVED***.spring_erp_platform.invoice.dto;

import com.***REMOVED***.spring_erp_platform.invoice.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// componentModel = "spring" permet d'injecter ce mapper avec @RequiredArgsConstructor
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    // On dit à MapStruct : "Prends l'ID du customer à l'intérieur de l'Invoice, et mets-le dans customerId"
    @Mapping(source = "customer.id", target = "customerId")
    InvoiceResponse toDto(Invoice invoice);

    Invoice toEntity(InvoiceRequest request);
}