# KisanHelp Backend

KisanHelp is a web-based crop advisory system designed to assist Indian farmers in making informed agricultural decisions. The system provides personalized recommendations for fertilizer application and water scheduling based on the farmer's selected crop, soil type, and growing season. By centralizing crop suitability data, soil-crop compatibility, fertilizer plans, and water schedules in a structured database, the platform eliminates the need for farmers to rely on informal or outdated advice. The application is built using a full-stack architecture with a Java Spring Boot backend and a React-based frontend, offering a clean and accessible interface for farmers across different districts.

The system follows a three-tier client-server architecture. The React frontend handles user interaction and API communication using Axios with JWT token headers. The Spring Boot backend exposes secured REST API endpoints and contains all business logic including crop suitability checks, fertilizer plan retrieval, and water schedule lookups. User authentication is handled via Spring Security with BCrypt password encoding and stateless JWT token validation.

## Key Features

- JWT-based authentication and authorization
- User registration and login via `/api/auth/register` and `/api/auth/login`
- Protected user profile endpoint `/api/profile`
- Crop filtering by soil type, season, and category
- Intelligent crop recommendations based on crop name, soil type, field size, water source, and planting month
- Fertilizer and water schedule guidance included in recommendation responses
- CORS configured for `http://localhost:5173`

## Technology Stack

- Java 17
- Spring Boot 3.5.11
- Spring Security
- Spring Data JPA
- MySQL Connector/J
- JWT (`jjwt`)
- Lombok
- Maven wrapper (`mvnw`)

## Getting Started

### Prerequisites

- Java 17 JDK
- MySQL database
- Maven (optional if using the wrapper)

### Configuration

Create or update `src/main/resources/application.properties` with your database details:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

Set environment variables before running the application:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/kisanhelp"
$env:DB_USERNAME = "your-db-user"
$env:DB_PASSWORD = "your-db-password"
```

### Build

```powershell
./mvnw clean package
```

### Run

```powershell
./mvnw spring-boot:run
```

## API Endpoints

### Authentication

- `POST /api/auth/register`
  - Request: `RegisterRequest`
  - Fields: `userName`, `email`, `state`, `city`, `landArea`, `password`
  - Returns: JWT token

- `POST /api/auth/login`
  - Request: `LoginRequest`
  - Fields: `email`, `password`
  - Returns: JWT token

### User Profile

- `GET /api/profile`
  - Requires Authorization header: `Bearer <token>`
  - Returns: user details including username, email, state, city, and land area

### Crop Filter

- `GET /api/crop/filter`
  - Query parameters:
    - `soilType` (optional)
    - `season` (optional)
    - `category` (optional)
  - Requires authorization
  - Returns a list of matching crops and soil suitability details

### Crop Recommendation

- `POST /api/recommendation`
  - Request: `CropRequest`
  - Fields: `cropName`, `soilType`, `acre`, `waterSource`, `monthToStart`
  - Requires authorization
  - Returns: recommendation details, fertilizer plan, water plan, and recommended crops when water source is `none`

## Security

- Stateless JWT authentication with `JwtAuthFilter`
- All `/api/auth/**` routes are public
- All other routes require a valid JWT
- Passwords are encoded with `BCryptPasswordEncoder`

## CORS

Configured for the origin:

- `http://localhost:5173`

Allowed methods:

- `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`

## Useful Commands

- Run unit tests:

```powershell
./mvnw test
```

- Validate project build:

```powershell
./mvnw clean package -DskipTests
```

## Notes

- The application expects the database schema to already exist because `spring.jpa.hibernate.ddl-auto=validate`.
- Update `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` with your environment-specific values.

---

For more details, inspect the controller and service classes under `src/main/java/dev/kisanhelp/project_kh/`.
