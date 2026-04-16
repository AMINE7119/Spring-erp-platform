# --- STAGE 1 : Build ---
# On utilise une image Maven complète pour compiler le projet
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# On copie le pom.xml et les sources
COPY pom.xml .
COPY src ./src

# On compile le JAR en sautant les tests (car GitHub Actions les a déjà validés)
RUN mvn clean package -DskipTests

# --- STAGE 2 : Runtime ---
# On utilise une image JRE Alpine ultra-légère pour l'exécution
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# On ne récupère que le JAR généré au Stage 1
COPY --from=build /app/target/*.jar app.jar

# Port exposé par ton application Spring Boot
EXPOSE 8080

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]