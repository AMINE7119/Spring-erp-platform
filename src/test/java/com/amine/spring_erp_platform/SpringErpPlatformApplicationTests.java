package com.amine.spring_erp_platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Indique à Spring d'utiliser src/test/resources/application-test.yml
class SpringErpPlatformApplicationTests {

	@Test
	void contextLoads() {
		/* * Ce test vérifie maintenant que :
		 * 1. Le contexte Spring démarre sans erreur.
		 * 2. La base de données H2 (en mémoire) est bien configurée.
		 * 3. Toutes tes Beans de sécurité (JWT, AuthProvider) sont correctement injectées.
		 */
	}

}