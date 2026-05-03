package com.amine.spring_erp_platform.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
// CORRECTION ICI : On laisse SecurityConfig gérer la sécurité pour éviter les conflits
public class AdminController {

    @GetMapping
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Accès autorisé : Bonjour Administrateur (GET)");
    }

    @PostMapping
    public ResponseEntity<String> postAdminData() {
        return ResponseEntity.ok("Accès autorisé : Vous pouvez créer des ressources (POST)");
    }
}
