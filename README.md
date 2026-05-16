# 🛒 E-Commerce Microservices Application

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-green?style=for-the-badge&logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2023.0.0-green?style=for-the-badge&logo=spring)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=for-the-badge&logo=docker)
![Kafka](https://img.shields.io/badge/Apache_Kafka-black?style=for-the-badge&logo=apachekafka)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb)

A full-stack **E-Commerce application** built with a microservices architecture using **Spring Boot 3**, **Spring Cloud**, and **Keycloak**. This project demonstrates real-world microservices patterns including service discovery, centralized configuration, API gateway, asynchronous messaging, and distributed tracing.

---

##  Architecture Overview

```
                        ┌─────────────────────────────────────────────────────┐
                        │                  Private Network                     │
  ┌──────────┐          │  ┌──────────┐   ┌──────────┐   ┌──────────────────┐│
  │          │/customers│  │ Customer │──▶│ MongoDB  │   │    Payment       ││
  │  Angular │─────────▶│  │ Service  │   └──────────┘   │    Service       ││
  │  Client  │/products │  └──────────┘                   └────────┬─────────┘│
  │          │─────────▶│  ┌──────────┐   ┌──────────┐            │Kafka     │
  └──────────┘/orders   │  │ Product  │   │PostgreSQL│   ┌─────────▼─────────┐│
       │                │  │ Service  │──▶│          │   │  Notification     ││
       ▼                │  └──────────┘   └──────────┘   │  Service          ││
  ┌──────────┐          │  ┌──────────┐   ┌──────────┐   └────────┬──────────┘│
  │  API     │          │  │  Order   │──▶│PostgreSQL│            │MongoDB    │
  │ Gateway  │          │  │ Service  │   └──────────┘   ┌────────▼──────────┐│
  └──────────┘          │  └──────────┘                   │     Zipkin        ││
                        │                                 │ Distributed       ││
                        │  ┌──────────────┐ ┌──────────┐ │    Tracing        ││
                        │  │ Eureka Server│ │  Config  │ └───────────────────┘│
                        │  │  (Discovery) │ │  Server  │                      │
                        │  └──────────────┘ └──────────┘                      │
                        └─────────────────────────────────────────────────────┘
```

---

##  Microservices

| Service | Description | Port | Database |
|---|---|---|---|
| **Config Server** | Centralized configuration management | `8888` | — |
| **Discovery Server** | Eureka service registry | `8761` | — |
| **API Gateway** | Single entry point for all clients | `8222` | — |
| **Customer Service** | Manages customers and addresses | `8090` | MongoDB |
| **Product Service** | Manages products and categories | `8050` | PostgreSQL |
| **Order Service** | Handles order creation and management | `8070` | PostgreSQL |
| **Payment Service** | Processes payments | `8060` | PostgreSQL |
| **Notification Service** | Sends email notifications via Kafka | `8040` | MongoDB |

---

##  Tech Stack

### Backend
- **Java 21**
- **Spring Boot 3.2.2**
- **Spring Cloud 2023.0.0**
  - Spring Cloud Config (centralized config)
  - Spring Cloud Netflix Eureka (service discovery)
  - Spring Cloud Gateway (API gateway)
  - OpenFeign (synchronous inter-service communication)

### Messaging
- **Apache Kafka** — asynchronous communication between Order/Payment → Notification

### Databases
- **PostgreSQL** — Order, Product, Payment services
- **MongoDB** — Customer, Notification services

### Security
- **Keycloak** — OAuth2 / OpenID Connect authentication & authorization

### Observability
- **Zipkin** — Distributed tracing across all services

### DevOps
- **Docker & Docker Compose** — containerized infrastructure
- **pgAdmin** — PostgreSQL management UI
- **mongo-express** — MongoDB management UI
- **MailDev** — Local email testing

---


##  Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- Docker & Docker Compose

### 1. Clone the repository

```bash
git clone https://github.com/your-username/Microservices-app.git
cd Microservices-app
```

### 2. Configure environment variables

Create a `.env` file at the root of the project:

```env
# PostgreSQL
POSTGRES_USER=your_user
POSTGRES_PASSWORD=your_password

# pgAdmin
PGADMIN_DEFAULT_EMAIL=your_email@gmail.com
PGADMIN_DEFAULT_PASSWORD=your_password

# MongoDB
MONGO_INITDB_ROOT_USERNAME=your_user
MONGO_INITDB_ROOT_PASSWORD=your_password
```

### 3. Start the infrastructure

```bash
docker compose up -d
```

### 4. Start the services (in order)

```bash
# 1. Config Server (always first)
cd config-server && mvn spring-boot:run

# 2. Discovery Server
cd discovery-server && mvn spring-boot:run

# 3. API Gateway
cd api-gateway && mvn spring-boot:run

# 4. Business Services (any order)
cd customer-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd payment-service && mvn spring-boot:run
cd notification-service && mvn spring-boot:run
```

---



### Asynchronous — Apache Kafka
```
Order Service   ──(Kafka)──▶ Notification Service
Payment Service ──(Kafka)──▶ Notification Service
```

---

##  Key Concepts Applied

- **Domain-Driven Design (DDD)** — each service owns its bounded context
- **Database per Service** — full data isolation between services
- **API Gateway Pattern** — single entry point with routing and security
- **Event-Driven Architecture** — decoupled communication via Kafka
- **Distributed Tracing** — end-to-end request tracking with Zipkin
- **Centralized Configuration** — all configs managed by Config Server
- **Service Discovery** — dynamic service registration with Eureka

---



---

⭐ **If you found this project helpful, please give it a star!**
