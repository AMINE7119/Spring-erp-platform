package com.***REMOVED***.spring_erp_platform.stock.controller;

import com.***REMOVED***.spring_erp_platform.stock.dto.*;
import com.***REMOVED***.spring_erp_platform.stock.entity.Product;
import com.***REMOVED***.spring_erp_platform.stock.entity.StockMovement;
import com.***REMOVED***.spring_erp_platform.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockMapper mapper;

    // --- PRODUITS ---

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Product savedProduct = stockService.createProduct(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(savedProduct), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = stockService.getAllProducts()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // --- MOUVEMENTS DE STOCK ---

    @PostMapping("/products/{productId}/movements")
    public ResponseEntity<StockMovementResponse> addMovement(
            @PathVariable Long productId,
            @RequestBody StockMovementRequest request) {

        StockMovement savedMovement = stockService.addMovement(productId, mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(savedMovement), HttpStatus.CREATED);
    }
}