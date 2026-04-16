package com.amine.spring_erp_platform.stock.controller;

import com.amine.spring_erp_platform.stock.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 💡 Démarre tout le contexte Spring Boot (Base de données H2, Services, etc.)
@AutoConfigureMockMvc // 💡 Configure l'outil qui va simuler nos requêtes HTTP (Postman en code)
@ActiveProfiles("test") // 💡 Ajoute cette ligne pour utiliser application-test.yml
class StockControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Pour transformer nos objets Java en texte JSON

    @Test
    @WithMockUser(username = "admin@test.com", roles = {"ADMIN"}) // 💡 Magie : On simule un utilisateur connecté avec un Token valide !
    void createProduct_ShouldReturn201AndProductResponse_WhenRequestIsValid() throws Exception {
        // ==========================================
        // 1. ARRANGE (Préparation de la requête)
        // ==========================================
        ProductRequest request = new ProductRequest();
        request.setReference("TEST-INT-001");
        request.setName("Produit d'Intégration");
        request.setPrice(new BigDecimal("99.99"));

        // ==========================================
        // 2 & 3. ACT & ASSERT (Exécution HTTP et Vérifications)
        // ==========================================
        mockMvc.perform(post("/api/v1/stock/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // On envoie le JSON

                // Assertions sur la réponse HTTP
                .andExpect(status().isCreated()) // On attend un code 201 Created
                .andExpect(jsonPath("$.reference").value("TEST-INT-001")) // Le JSON de retour doit avoir la bonne référence
                .andExpect(jsonPath("$.currentQuantity").value(0)) // La règle métier de stock initial à 0 a bien fonctionné !
                .andExpect(jsonPath("$.id").exists()); // La base de données a bien généré un ID
    }
}