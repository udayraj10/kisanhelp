# KisanHelp Backend API

KisanHelp is a web-based agricultural advisory system designed to assist Indian farmers with crop selection, fertilizer planning, and water scheduling. The backend provides secure, RESTful endpoints that match crop requirements with soil types, seasons, and field parameters to generate field-specific recommendations.

---

## Architecture Overview

The system uses a layered backend architecture built on Spring Boot 3.5.11, following domain-driven patterns to decouple data persistence, business logic, and API endpoints.

```
 Client (React) 
       │
       ▼
 ┌───────────┐
 │ API Layer │  Spring MVC Controllers (REST API, Request Validation)
 └─────┬─────┘
       │
       ▼
 ┌───────────┐
 │ Security  │  Spring Security + Custom JwtAuthFilter (Stateless Auth)
 └─────┬─────┘
       │
       ▼
 ┌───────────┐
 │  Service  │  Business Logic (Advisory Engine, Filtering, Rule Checks)
 └─────┬─────┘
       │
       ▼
 ┌───────────-┐
 │ Persistence│ Spring Data JPA / Hibernate (Data Access)
 └─────┬────-─┘
       │
       ▼
   MySQL DB
```

### Architectural Highlights
- **Stateless Authentication:** Stateless session management using JWT avoids backend server session state, making the service ready for horizontal scaling.
- **Strict Data Validation:** Schema updates are set to `validate` in production, using structured JPA mappings to manage relations safely.
- **Layered Decoupling:** Data Transfer Objects (DTOs) decouple internal database entities from external API interfaces to prevent unintended data exposure.

---

## Technical Implementation

### Core Advisory Engine
The core service processes contextual farming data to calculate recommendations:
1. **Compatibility Processing:** Filters crops based on relational database checks linking soil types, agro-ecological seasons, and crop categories.
2. **Resource Allocation:** Aggregates fertilizer application schedules based on input field acreage (`acre`) and calculates irrigation intervals relative to available `waterSource` type and initial planting month (`monthToStart`).
3. **Fallback Logic:** If the user specifies `waterSource: "none"`, the engine runs alternative lookup logic to return rain-fed crop choices suited for dryland farming.

### Security Implementation
Security is managed via Spring Security using a custom filter chain:
- **Authentication Flow:** User submits credentials at `/api/auth/login` $\rightarrow$ System verifies via `BCryptPasswordEncoder` $\rightarrow$ Generates signed JWT payload.
- **Request Lifecycle:** `JwtAuthFilter` intercepts incoming HTTP requests, extracts the `Bearer` token from the `Authorization` header, verifies the signature, and populates the `SecurityContextHolder`.

---

## Tech Stack

| Domain | Technology |
|---|---|
| **Language & Framework** | Java 17, Spring Boot 3.5.11 |
| **Security** | Spring Security, JJWT (`io.jsonwebtoken`) |
| **Data & Persistence** | Spring Data JPA, Hibernate, MySQL Connector/J |
| **Utilities & Build** | Lombok, Maven |

---

## Core API Reference

### Authentication & User Management

#### Register User
```http
POST /api/auth/register
```
```json
{
  "userName": "RameshKumar",
  "email": "ramesh@example.com",
  "state": "Andhra Pradesh",
  "city": "Vijayawada",
  "landArea": 5.5,
  "password": "SecurePassword123!"
}
```

#### Authenticate User
```http
POST /api/auth/login
```
```json
{
  "email": "ramesh@example.com",
  "password": "SecurePassword123!"
}
```
*Returns:* `{ "token": "<jwt-token>" }`

#### Get User Profile
```http
GET /api/profile
Authorization: Bearer <jwt-token>
```

---

### Crop & Advisory Services

#### Filter Suitable Crops
```http
GET /api/crop/filter?soilType=Black&season=Kharif&category=Pulses
Authorization: Bearer <jwt-token>
```
*Returns:* Array of matching crops with soil suitability details.

#### Generate Crop Advisory
```http
POST /api/recommendation
Authorization: Bearer <jwt-token>
```
```json
{
  "cropName": "Cotton",
  "soilType": "Black",
  "acre": 4.0,
  "waterSource": "Borewell",
  "monthToStart": "June"
}
```
*Response Payload Structure:*
```json
{
  "cropName": "Cotton",
  "recommendationText": "Suitable for planting.",
  "fertilizerPlan": {
    "basalDose": "NPK 20:20:0",
    "topDressing": "Urea at 30 days"
  },
  "waterSchedule": {
    "frequencyDays": 7,
    "criticalStages": ["Flowering", "Boll formation"]
  },
  "alternativeCrops": []
}
```

---

## Quickstart

### Prerequisites
- JDK 17
- MySQL Instance running with an initialized `kisanhelp` database schema.

### Execution

```bash
# Set environment configuration
export DB_URL="jdbc:mysql://localhost:3306/kisanhelp"
export DB_USERNAME="root"
export DB_PASSWORD="yourpassword"

# Build and Run
./mvnw clean package -DskipTests
./mvnw spring-boot:run
```