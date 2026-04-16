Spring ERP Platform: Cloud-Native Micro-Architecture
====================================================

A robust Enterprise Resource Planning (ERP) backend developed with **Spring Boot 3**, designed for scalability, data integrity, and automated deployment. This project serves as a technical demonstration of modern software engineering practices, moving from a monolithic core to a containerized, production-ready environment.

### Core Technical Pillars

#### 1\. Software Architecture & Design Patterns

The system is built upon a modular monolith architecture with strict separation of concerns, ensuring high maintainability:

*   **Strategy & Factory Patterns**: Implemented in the TaxCalculation module to allow dynamic tax rule switching (B2B, B2C, International) without modifying core service logic.

*   **Domain-Driven Design (DDD) Principles**: Organized by bounded contexts (Customer, Invoice, Stock) to minimize coupling.

*   **Validation & Global Exception Handling**: Centralized error management to ensure 100% consistent API responses and data integrity.


#### 2\. Security Framework

Secured via **Spring Security 6** and **Stateless JWT Authentication**:

*   **Role-Based Access Control (RBAC)**: Differentiated permissions for ADMIN, MANAGER, and USER.

*   **Stateless Security**: All sessions are managed via signed tokens, enabling horizontal scaling and microservice readiness.


#### 3\. Reliability & Testing (TDD Approach)

The project maintains high reliability through a comprehensive testing suite:

*   **Unit Testing**: Isolated logic validation using **JUnit 5** and **Mockito**.

*   **Integration Testing**: Full-context API validation using **MockMvc** and **H2** in-memory database for rapid CI/CD cycles.

*   **CI/CD Pipeline**: Integrated with **GitHub Actions** for automated build verification and test execution on every push.


#### 4\. DevOps & Containerization

Designed for the "it works everywhere" philosophy:

*   **Multi-Stage Docker Build**: Optimized images (Eclipse Temurin JRE Alpine) for minimal footprint and maximum security.

*   **Docker Compose Orchestration**: One-command deployment involving the Spring Boot application and a **PostgreSQL 15** persistence layer.


### Technology Stack

*   **Language**: Java 17

*   **Framework**: Spring Boot 3.2.5

*   **Persistence**: Spring Data JPA / Hibernate

*   **Database**: PostgreSQL (Production) / H2 (Testing)

*   **Security**: Spring Security & JWT

*   **DevOps**: Docker, Docker Compose, GitHub Actions


### Engineering Decisions & Problem Solving

**ChallengeSolutionImpactCode Smells in Tax Logic**Replaced conditional blocks with **Strategy Pattern**.Added support for new tax regions in minutes without touching stable code.**Inconsistent API Errors**Implemented a global @RestControllerAdvice.Standardized JSON error objects across the entire platform for easier frontend integration.**Environmental Drift**Implemented **Multi-Stage Dockerfile**.Reduced final image size by 70% and eliminated environment-specific runtime bugs.**Race Conditions in Stock**Enforced database-level constraints and atomic service updates.Guaranteed accurate stock levels during concurrent transactions.

### Getting Started

#### Prerequisites

*   Docker & Docker Compose


#### Deployment

1.  Bashgit clone https://github.com/AMINE7119/Spring-erp-platform.git

2.  Bashdocker-compose up --build


The application will be available at http://localhost:8080.

### Future Roadmap

*   Migration to Microservices using Spring Cloud Gateway.

*   Implementation of an Event-Driven architecture for asynchronous invoice-to-stock synchronization.

*   Frontend integration using React/TypeScript.


### Amine YANI

*   **Status**: Software Engineering Student (M1/M2)

*   **Focus**: Backend Engineering, DevOps, Cloud Architecture