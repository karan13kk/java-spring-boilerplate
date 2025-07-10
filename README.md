# Spring Boot Boilerplate

A production-ready Spring Boot boilerplate application with comprehensive logging, error handling, health checks, and database integration.

## 🚀 Features

- **Spring Boot 3.5.3** with Java 21
- **H2 In-Memory Database** for development (PostgreSQL ready for production)
- **Log4j2** with structured JSON logging and trace/request ID support
- **Spring Boot Actuator** for health checks and monitoring
- **Custom Error Handling** with business logic error codes and standardized responses
- **Global Exception Handler** with comprehensive logging and secure client responses
- **JPA/Hibernate** with automatic schema generation
- **MapStruct** for object mapping with DTOs
- **Lombok** for reducing boilerplate code
- **Maven** build system

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- Git

## 🛠️ Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.5.3 | Application framework |
| Java | 21 | Programming language |
| H2 Database | Latest | In-memory database |
| PostgreSQL | Latest | Production database |
| Log4j2 | Latest | Structured logging |
| MapStruct | 1.6.3 | Object mapping |
| Lombok | Latest | Code generation |
| Spring Data JPA | Latest | Data access layer |
| Spring Boot Actuator | Latest | Monitoring & health checks |

## 🏗️ Project Structure

```
src/main/java/com/example/boilerplate/
├── BoilerplateApplication.java          # Main application class
├── controller/
│   └── HealthController.java            # Health check endpoint
├── service/
│   └── ApplicationDetailsService.java   # Business logic layer
├── repository/
│   └── ApplicationDetailsRepository.java # Data access layer
├── entity/
│   └── ApplicationDetails.java          # JPA entity
├── dto/
│   └── HealthCheckResponse.java         # Data transfer objects
├── mapper/
│   └── ApplicationDetailsMapper.java    # MapStruct mapper
└── exception/
    ├── CustomError.java                 # Custom business logic exceptions
    ├── ErrorCode.java                   # Standardized error code enum
    └── GlobalExceptionHandler.java      # Global error handling
```

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd spring-boilerplate
```

### 2. Build the Application
```bash
./mvnw clean compile
```

### 3. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### Health Check
- **GET** `/health` - Application health status with version info
- **GET** `/actuator/health` - Spring Boot Actuator health endpoint
- **GET** `/actuator/info` - Application information
- **GET** `/actuator/metrics` - Application metrics

### Error Handling Demo
- **GET** `/health/validate?applicationName={name}` - Validate application name (demonstrates CustomError)
- **GET** `/health/business-rule?environment={env}` - Check business rules (demonstrates CustomError)

### Database Console (Development)
- **GET** `/h2-console` - H2 database web console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: `password`

## 🗄️ Database

### Development (H2)
- In-memory database with automatic schema generation
- Data is reset on each application restart
- Web console available at `/h2-console`

### Production (PostgreSQL)
To switch to PostgreSQL, update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/boilerplate
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 📊 Logging

### Configuration
- **Log4j2** with JSON structured logging
- **Trace ID and Request ID** support via Spring Boot Actuator
- **Log files** stored in `logs/` directory
- **Log rotation** with size and time-based policies

### Log Levels
- Application logs: `INFO`
- Spring Framework: `WARN`
- Hibernate: `WARN`
- Root level: `INFO`

### Log Output
- **Console**: JSON format with trace/request IDs
- **File**: `logs/application.log` (all levels)
- **Error File**: `logs/error.log` (ERROR and FATAL only)

## 🔧 Configuration

### Key Properties
```properties
# Application
spring.application.name=boilerplate

# Database (H2)
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop

# Actuator
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always

# Logging
logging.level.org.springframework.web=INFO
spring.mvc.favicon.enabled=false
```

## 🏥 Health Check

The health endpoint (`/health`) returns:
- Application status
- Version information
- Database connectivity status
- Timestamp

Example Response:
```json
{
  "status": "UP",
  "applicationName": "boilerplate",
  "version": "1.0.0",
  "description": "Spring Boot Boilerplate Application",
  "environment": "development",
  "buildNumber": "1",
  "timestamp": "2024-01-15T10:30:00"
}
```

## 🚨 Error Handling

### Custom Error System
The application uses a custom error handling system for business logic errors:

#### ErrorCode Enum
```java
public enum ErrorCode {
    INVALID_PAYLOAD,
    USER_NOT_AUTHORIZED,
    BUSINESS_RULE_VIOLATION,
    DUPLICATE_ENTRY,
    INVALID_STATE,
    RESOURCE_NOT_FOUND
}
```

#### CustomError Usage
```java
// Instead of: throw new IllegalArgumentException("Invalid input");
throw new CustomError(ErrorCode.INVALID_PAYLOAD, "Invalid payload provided");
throw new CustomError(ErrorCode.BUSINESS_RULE_VIOLATION, "Business rule validation failed");
```

### Global Exception Handler
- **CustomError**: Returns specific error message with error code (400 Bad Request)
- **Generic exceptions**: 500 Internal Server Error with generic message
- **404 Not Found**: Custom error response
- **IllegalArgumentException**: 400 Bad Request with generic message
- **Comprehensive logging** with stack traces (server-side only)
- **Secure responses** - No stack traces sent to clients

### Error Response Examples

#### CustomError Response
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "INVALID_PAYLOAD",
  "message": "Application name cannot be null or empty",
  "path": "/health/validate",
  "application": "boilerplate"
}
```

#### Generic Error Response
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "BAD_REQUEST",
  "message": "An invalid request was made",
  "path": "/health/validate",
  "application": "boilerplate"
}
```

### Testing Error Handling
```bash
# Test CustomError - Invalid application name
curl "http://localhost:8080/health/validate?applicationName="

# Test CustomError - Business rule violation
curl "http://localhost:8080/health/business-rule?environment=production"

# Test 404 Not Found
curl "http://localhost:8080/nonexistent-endpoint"
```

## 🏗️ Architecture

### Layers
1. **Controller Layer** (`controller/`) - REST endpoints
2. **Service Layer** (`service/`) - Business logic
3. **Repository Layer** (`repository/`) - Data access
4. **Entity Layer** (`entity/`) - JPA entities
5. **DTO Layer** (`dto/`) - Data transfer objects
6. **Mapper Layer** (`mapper/`) - Object mapping

### Design Patterns
- **Repository Pattern** - Data access abstraction
- **Service Layer Pattern** - Business logic encapsulation
- **DTO Pattern** - Data transfer object separation
- **Global Exception Handler** - Centralized error handling
- **Custom Error Pattern** - Business logic error handling with standardized codes

## 🧪 Testing

### Run Tests
```bash
./mvnw test
```

### Test Coverage
```bash
./mvnw jacoco:report
```

## 📦 Build

### Create JAR
```bash
./mvnw clean package
```

### Run JAR
```bash
java -jar target/boilerplate-0.0.1-SNAPSHOT.jar
```

## 🔍 Monitoring

### Actuator Endpoints
- `/actuator/health` - Health status
- `/actuator/info` - Application info
- `/actuator/metrics` - Application metrics
- `/actuator/prometheus` - Prometheus metrics

### Custom Health Check
- `/health` - Custom health endpoint with version info

## 🚀 Deployment

### Docker (Recommended)
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/boilerplate-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables
```bash
# Database
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/boilerplate
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password

# Application
SPRING_PROFILES_ACTIVE=production
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the code examples

---

**Happy Coding! 🎉** 