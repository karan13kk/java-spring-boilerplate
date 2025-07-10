# Spring Boot Boilerplate

A comprehensive Spring Boot boilerplate with health checks, logging, error handling, and Docker support.

## Features

- ✅ **Health Check API** - Built-in health endpoints
- ✅ **Global Exception Handling** - Comprehensive error handling with proper logging
- ✅ **Logging Setup** - Structured logging with file rotation
- ✅ **Docker Support** - Multi-stage Dockerfile with security best practices
- ✅ **Database Integration** - JPA/Hibernate with H2 for development
- ✅ **Actuator** - Spring Boot Actuator for monitoring
- ✅ **Validation** - Request validation with proper error responses
- ✅ **Maven Build** - Standard Maven project structure
- ✅ **OpenAPI Documentation** - Swagger UI for API documentation

## Quick Start

### Prerequisites

- Java 24 or higher
- Maven 3.6 or higher
- Docker (optional)

### Running Locally

1. **Clone and navigate to the project:**
   ```bash
   cd spring-boilerplate
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   - Application: http://localhost:8080
   - Health Check: http://localhost:8080/api/health
   - Detailed Health: http://localhost:8080/api/health/detailed
   - Actuator: http://localhost:8080/actuator
   - H2 Console: http://localhost:8080/h2-console
   - API Documentation: http://localhost:8080/swagger-ui.html

### Running with Docker

1. **Build and run with Docker Compose:**
   ```bash
   docker-compose up --build
   ```

2. **Run with database (PostgreSQL):**
   ```bash
   docker-compose --profile with-database up --build
   ```

3. **Build Docker image manually:**
   ```bash
   docker build -t spring-boilerplate .
   docker run -p 8080:8080 spring-boilerplate
   ```

## API Endpoints

### Health Check Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/health` | Basic health check |
| GET | `/api/health/detailed` | Detailed health check with system info |

### Actuator Endpoints

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Application health status |
| `/actuator/info` | Application information |
| `/actuator/metrics` | Application metrics |
| `/swagger-ui.html` | API Documentation (Swagger UI) |
| `/api-docs` | OpenAPI specification |

## Error Handling

The application includes comprehensive error handling with the following features:

- **Global Exception Handler** - Catches all unhandled exceptions
- **Database Error Handling** - Logs DB errors and returns 5xx responses
- **Validation Error Handling** - Returns 4xx for validation failures
- **Consistent Error Response Format** - Standardized error response structure

### Error Response Format

```json
{
  "timestamp": "2024-01-01 12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/example",
  "details": [
    "Field 'name' is required",
    "Field 'email' must be a valid email address"
  ]
}
```

## Logging

The application uses SLF4J with Logback for logging:

- **Console Logging** - Human-readable format
- **File Logging** - Rotated log files in `logs/` directory
- **Structured Logging** - JSON format for production
- **Log Levels** - Configurable per package

### Log Configuration

```yaml
logging:
  level:
    root: INFO
    com.example.springboilerplate: DEBUG
  file:
    name: logs/spring-boilerplate.log
    max-size: 10MB
    max-history: 30
```

## Configuration

### Application Properties

Key configuration options in `application.yml`:

- **Server Port**: 8080
- **Database**: H2 in-memory (configurable)
- **Logging**: File and console output
- **Actuator**: Health, info, and metrics endpoints

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SERVER_PORT` | Application port | 8080 |
| `SPRING_PROFILES_ACTIVE` | Active profile | default |
| `JAVA_OPTS` | JVM options | -Xms512m -Xmx1024m |

## Development

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/springboilerplate/
│   │       ├── controller/
│   │       │   └── HealthController.java
│   │       ├── dto/
│   │       │   └── ErrorResponse.java
│   │       ├── exception/
│   │       │   ├── BusinessException.java
│   │       │   └── GlobalExceptionHandler.java
│   │       └── SpringBoilerplateApplication.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── com/example/springboilerplate/
```

### Adding New Controllers

1. Create a new controller in the `controller` package
2. Use `@RestController` and `@RequestMapping` annotations
3. Add proper logging with SLF4J
4. Use validation annotations for request validation

Example:
```java
@RestController
@RequestMapping("/api/example")
public class ExampleController {
    
    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);
    
    @GetMapping
    public ResponseEntity<String> getExample() {
        logger.info("Example endpoint called");
        return ResponseEntity.ok("Hello World!");
    }
}
```

### Adding Custom Exceptions

1. Extend `BusinessException` for business logic errors
2. The `GlobalExceptionHandler` will automatically handle them
3. Use appropriate HTTP status codes

Example:
```java
throw new BusinessException("Resource not found", HttpStatus.NOT_FOUND, "NOT_FOUND");
```

## Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn test jacoco:report

# Run integration tests
mvn verify
```

### Test Structure

- **Unit Tests** - Test individual components
- **Integration Tests** - Test component interactions
- **End-to-End Tests** - Test complete workflows

## Production Deployment

### Docker Production

```bash
# Build production image
docker build -t spring-boilerplate:latest .

# Run with production settings
docker run -d \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=production \
  -e JAVA_OPTS="-Xms1g -Xmx2g" \
  spring-boilerplate:latest
```

### Environment Configuration

Create `application-production.yml` for production settings:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://db:5432/myapp
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  
logging:
  level:
    root: WARN
    com.example.springboilerplate: INFO
```

## Monitoring

### Health Checks

The application provides multiple health check endpoints:

- **Basic Health**: `/api/health`
- **Detailed Health**: `/api/health/detailed`
- **Actuator Health**: `/actuator/health`

### Metrics

Spring Boot Actuator provides metrics at `/actuator/metrics`:

- JVM metrics
- HTTP request metrics
- Database connection metrics
- Custom application metrics

## Security Considerations

- **Non-root Docker user** - Application runs as non-root user
- **Input validation** - All inputs are validated
- **Error message sanitization** - Sensitive information is not exposed
- **Logging security** - Sensitive data is not logged

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For questions and support, please open an issue on GitHub. 