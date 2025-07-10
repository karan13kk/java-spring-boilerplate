# Spring Boot Boilerplate

A production-ready Spring Boot boilerplate application with comprehensive logging, error handling, health checks, and database integration.

## 🚀 Features

- **Spring Boot 3.5.3** with Java 21
- **H2 In-Memory Database** for development (PostgreSQL ready for production)
- **Log4j2** with structured JSON logging and trace/request ID support
- **Spring Boot Actuator** for health checks and monitoring
- **Global Exception Handling** with standardized error responses
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

### Global Exception Handler
- **Generic exceptions**: 500 Internal Server Error
- **404 Not Found**: Custom error response
- **IllegalArgumentException**: 400 Bad Request
- **Standardized error format** with timestamp, status, and message

### Error Response Format
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "NOT_FOUND",
  "message": "The requested resource was not found",
  "path": "/api/nonexistent",
  "application": "boilerplate"
}
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