# Policy Pulse API

Spring Boot REST API for the **Policy Pulse** application. Provides backend endpoints consumed by the Policy Pulse UI (Vite + React). Includes a basic `/api/health` endpoint used to validate end-to-end connectivity.

## Tech Stack
- Java
- Spring Boot
- Maven
- Embedded Tomcat
- (Planned) PostgreSQL

## Features
- **Health endpoint** for UI + monitoring checks:
  - `GET /api/health` → `{ "status": "UP" }`

## Prerequisites
- Java 17+ (recommended)
- Maven 3.8+
- (Optional) PostgreSQL + pgAdmin (for future DB integration)

## Run Locally

### Option 1: IntelliJ
Run `PolicyPulseApiApplication.java`

### Option 2: Terminal
```bash
mvn clean spring-boot:run
