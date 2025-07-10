# Spring Boot Boilerplate Makefile

.PHONY: help build test run clean docker-build docker-run docker-stop

# Default target
help:
	@echo "Spring Boot Boilerplate - Available Commands:"
	@echo ""
	@echo "Development:"
	@echo "  build        - Build the project with Maven"
	@echo "  test         - Run tests"
	@echo "  run          - Run the application locally"
	@echo "  clean        - Clean build artifacts"
	@echo ""
	@echo "Docker:"
	@echo "  docker-build - Build Docker image"
	@echo "  docker-run   - Run with Docker Compose"
	@echo "  docker-stop  - Stop Docker containers"
	@echo ""
	@echo "Utilities:"
	@echo "  logs         - Show application logs"
	@echo "  health       - Check application health"

# Development commands
build:
	@echo "Building Spring Boot application..."
	mvn clean compile

test:
	@echo "Running tests..."
	mvn test

run:
	@echo "Starting Spring Boot application..."
	mvn spring-boot:run

clean:
	@echo "Cleaning build artifacts..."
	mvn clean

# Docker commands
docker-build:
	@echo "Building Docker image..."
	docker build -t spring-boilerplate .

docker-run:
	@echo "Starting application with Docker Compose..."
	docker-compose up --build

docker-stop:
	@echo "Stopping Docker containers..."
	docker-compose down

# Utility commands
logs:
	@echo "Showing application logs..."
	@if [ -f logs/spring-boilerplate.log ]; then \
		tail -f logs/spring-boilerplate.log; \
	else \
		echo "No log file found. Start the application first."; \
	fi

health:
	@echo "Checking application health..."
	@curl -s http://localhost:8080/api/health | jq . || echo "Application not running or jq not installed"

# Production commands
package:
	@echo "Creating production package..."
	mvn clean package -DskipTests

docker-prod:
	@echo "Building production Docker image..."
	docker build -t spring-boilerplate:latest .

# Database commands (if using PostgreSQL profile)
db-start:
	@echo "Starting PostgreSQL database..."
	docker-compose --profile with-database up -d postgres

db-stop:
	@echo "Stopping PostgreSQL database..."
	docker-compose --profile with-database down

# Format and lint
format:
	@echo "Formatting code..."
	mvn spotless:apply

lint:
	@echo "Checking code style..."
	mvn spotless:check 