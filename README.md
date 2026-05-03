# Spring ERP Platform
### Cloud-Native Modular Backend

A production-ready **Enterprise Resource Planning (ERP)** engine built with **Spring Boot 3.2.5**, designed to handle complex business logic through robust architectural patterns and a cloud-native deployment strategy.

This project demonstrates the transition from a monolithic core to a containerized, highly maintainable system suitable for modern enterprise requirements.

---

## Architecture & Engineering Patterns

Industry-standard design patterns are implemented throughout to solve common enterprise challenges and ensure code quality.

| Pattern | Module | Description |
|---|---|---|
| **Strategy + Factory** | `TaxCalculation` | Runtime resolution of tax logic (B2B, B2C, International) — strictly adheres to the Open/Closed Principle |
| **Domain-Driven Design (DDD)** | Global | Logic partitioned into isolated bounded contexts: `Customer`, `Invoice`, `Stock` |
| **AOP Error Handling** | Global | `@RestControllerAdvice` transforms internal failures into standardized JSON error schemas |

---

## Enterprise Security Framework

Security is implemented as a fundamental middleware layer, not an afterthought.

- **Stateless Authentication** — Full JWT (JSON Web Token) implementation for session management
- **Granular RBAC** — Strict method-level security and URL filtering for `ADMIN`, `MANAGER`, and `USER` roles via Spring Security 6
- **Request Validation** — Deep JSR-303 Bean Validation integration to enforce data integrity before reaching the persistence layer

---

## Reliability & CI/CD Integration

Platform stability is guaranteed through a multi-layered testing and automation strategy.

- **TDD** — Business logic validated with JUnit 5 and Mockito for full unit isolation
- **Integration Testing** — API endpoints verified via MockMvc against an H2 in-memory database on a dedicated test profile
- **Automated Pipeline** — GitHub Actions automate build, test, and reporting on every push; only verified code is merged into `develop`

---

## DevOps & Containerization Strategy

A **Multi-Stage Docker Architecture** optimizes both build reproducibility and runtime efficiency.

```
┌─────────────────────────────────────────────┐
│             Build Stage                      │
│  Maven 3.9 — Compiles & packages the JAR    │
│  Reproducible build, independent of host    │
└───────────────────┬─────────────────────────┘
                    │
                    ▼
┌─────────────────────────────────────────────┐
│             Runtime Stage                    │
│  Eclipse Temurin JRE Alpine                 │
│  ~70% smaller image · Minimal attack surface│
└─────────────────────────────────────────────┘
```

**Docker Compose** manages the full application lifecycle: service startup, internal networking, and PostgreSQL 15 dependency configuration.

---

## Technical Stack

| Layer | Technology |
|---|---|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.2.5, Spring Data JPA, Spring Security 6 |
| **Database** | PostgreSQL 15 (Production) · H2 (CI/CD & Integration Tests) |
| **Infrastructure** | Docker · Docker Compose · GitHub Actions |

---

## Engineering Decisions & Problem Solving

| Challenge | Engineering Solution | Business Impact |
|---|---|---|
| Complex Tax Logic | Strategy Pattern with Factory resolution | New tax regions added without modifying stable existing code |
| API Inconsistency | Global `@RestControllerAdvice` with standard Error DTOs | Simplified frontend integration and improved debugging efficiency |
| Environmental Drift | Multi-Stage Dockerfiles for build and runtime | Eliminated "works on my machine" bugs · Optimized cloud resource usage |
| Stock Consistency | Atomic service transactions + database constraints | Zero-error stock tracking guaranteed during high-concurrency operations |

---

## Getting Started

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/) installed and running

### Deployment

**1. Clone the repository**
```bash
git clone https://github.com/AMINE7119/Spring-erp-platform.git
cd Spring-erp-platform
```

**2. Launch via Docker Compose**
```bash
docker-compose up --build
```

**3. Access the API**

The server will be reachable at `http://localhost:8080`

```
POST /api/v1/auth/register      → Create an account
POST /api/v1/auth/authenticate  → Obtain a JWT token
```

---

## Roadmap

- [ ] Migration to a **Microservices** architecture using Spring Cloud Gateway
- [ ] **Event-Driven** architecture for asynchronous synchronization between `Invoice` and `Stock` modules
- [ ] Integration of **Prometheus & Grafana** for real-time system monitoring and observability

---

## Author

**Amine YANI** — Software Engineering Student( M1/M2 ) 
*Focus: Cloud-Native Backend Development & DevOps Automation*
