package com.amine.spring_erp_platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// Cette ligne dit à Spring : "Démarre pour le test, mais ne cherche pas de base de données"
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, FlywayAutoConfiguration.class})
class SpringErpPlatformApplicationTests {

	@Test
	void contextLoads() {
		// Ce test vérifie simplement que l'application peut démarrer sans erreur de syntaxe
	}

}