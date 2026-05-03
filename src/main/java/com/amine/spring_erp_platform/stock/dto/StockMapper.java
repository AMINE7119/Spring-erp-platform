package com.amine.spring_erp_platform.stock.dto;

import com.amine.spring_erp_platform.stock.entity.Product;
import com.amine.spring_erp_platform.stock.entity.StockMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Product toEntity(ProductRequest request);
    ProductResponse toDto(Product product);

    StockMovement toEntity(StockMovementRequest request);

    @Mapping(source = "product.id", target = "productId")
    StockMovementResponse toDto(StockMovement movement);
}
