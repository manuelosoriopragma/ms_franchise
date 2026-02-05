# Base Project Implementing Clean Architecture

## Before Starting

We will begin by explaining the different components of the project, starting with external components, continuing with the core business components (domain), and finally the initialization and configuration of the application.

Read the article [Clean Architecture — Isolating the Details](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Architecture

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

It is the innermost module of the architecture, belongs to the domain layer and encapsulates the business logic and rules through domain models and entities.

## Usecases

This gradle module belonging to the domain layer implements the system's use cases, defines application logic and reacts to invocations from the entry points module, orchestrating flows to the entities module.

## Infrastructure

### Helpers

In the helpers section we will have general utilities for Driven Adapters and Entry Points.

These utilities are not tied to concrete objects, generics are used to model generic behaviors of different persistence objects that may exist. This type of implementation is based on the [Unit of Work and Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006) design pattern.

These classes cannot exist alone and their behavior must be inherited in the **Driven Adapters**

### Driven Adapters

Driven adapters represent external implementations to our system, such as connections to REST services, SOAP, databases, reading flat files, and specifically any origin and data source with which we must interact.

### Entry Points

Entry points represent the application's entry points or the beginning of business flows.

## Application

This module is the outermost of the architecture, it is responsible for assembling the different modules, resolving dependencies and creating the beans of the use cases (UseCases) automatically, injecting concrete instances of the declared dependencies into them. It also starts the application (it is the only module in the project where we will find the "public static void main(String[] args)" function).

**Use case beans are made available automatically thanks to a '@ComponentScan' located in this layer.**

---

# Local Environment Execution Guide

## Prerequisites

- **Java 21** or higher
- **Gradle 8.x** or higher
- **Docker** and **Docker Compose**
- **Git**

## Environment Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd ms_franchise
```

### 2. Configure Environment Variables

The project uses the `deployment/ms_franchise.env` file to configure environment variables:

**Option A: Local Database (Docker)**
```env
PORT=8080
APP_NAME=ms_franchise
PROFILE=dev
DB_HOST=localhost
DB_PORT=5432
DB_NAME=franchise_db
DB_USERNAME=franchise_user
DB_PASSWORD=franchise_password
```

**Option B: AWS RDS Database**
```env
PORT=8080
APP_NAME=ms_franchise
PROFILE=dev
DB_HOST=franchise-instance-1.ckebtkjqnpjs.us-east-1.rds.amazonaws.com
DB_PORT=5432
DB_NAME=postgres
DB_USERNAME=franchise_user
DB_PASSWORD=franchise_password
```

**Note:** 
- For local environment with Docker, use `DB_HOST=localhost`
- For AWS RDS, use the RDS endpoint as `DB_HOST` and skip step 3 (Docker setup)

### 3. Start the Database with Docker (Skip if using AWS RDS)

**Only required if using local database (Option A)**

The project includes a `docker-compose.yml` file in the `deployment/` folder that configures PostgreSQL 15:

```bash
cd deployment
docker-compose up -d
```

This will start:
- **PostgreSQL 15** on port `5432`
- Database: `franchise_db`
- User: `franchise_user`
- Password: `franchise_password`
- Timezone: `America/Bogota`

**Verify that the database is running:**

```bash
docker-compose ps
```

**View database logs:**

```bash
docker-compose logs -f db
```

### 4. Build the Project

From the project root:

```bash
./gradlew clean build
```

Or on Windows:

```bash
gradlew.bat clean build
```

### 5. Run the Application

#### Option A: Using Gradle

```bash
./gradlew bootRun
```

#### Option B: Using the generated JAR

```bash
java -jar applications/app-service/build/libs/ms_franchise.jar
```

#### Option C: From the IDE

Run the main class:
```
co.com.nequi.MainApplication
```

Make sure to load environment variables from `deployment/ms_franchise.env`

### 6. Verify the Application is Running

**Health Check:**
```bash
curl http://localhost:8080/actuator/health
```

Expected response:
```json
{"status":"UP"}
```

## API Documentation

### Swagger UI

The OpenAPI documentation is available in the `deployment/openapi.yaml` file.

You can visualize it using:
- **Swagger Editor Online:** https://editor.swagger.io (paste the file content)
- **Swagger UI Local:** Configure Swagger UI pointing to the `openapi.yaml` file

### Postman Collection

The project includes a Postman collection in `deployment/collection/`:

1. **Import collection:** `NEQUI.postman_collection.json`
2. **Import environment:** `LOCAL.postman_environment.json`
3. **Configure `host` variable:** `http://localhost:8080`

## Available Endpoints

### Franchise
- `POST /franchise/create` - Create franchise
- `PUT /franchise/rename` - Update franchise name

### Branch
- `POST /branch/assign` - Assign branch to franchise
- `PUT /branch/rename` - Update branch name

### Product
- `POST /product/save` - Save product
- `DELETE /product/{productId}` - Delete product
- `PUT /product/updateStock` - Update product stock
- `PUT /product/rename` - Update product name
- `GET /product/maxStockProducts/{franchiseId}` - Get products with maximum stock by franchise

## Database Structure

The database schema is automatically initialized from:
```
applications/app-service/src/main/resources/db/schema.sql
```

**Tables:**
- `franchises` - Franchises
- `branches` - Branches
- `products` - Products

## Stop the Environment

### Stop the application
Press `Ctrl+C` in the terminal where the application is running

### Stop the database
```bash
cd deployment
docker-compose down
```

### Stop and remove volumes (data)
```bash
docker-compose down -v
```

## Run Tests

### All tests
```bash
./gradlew test
```

### Tests with coverage report
```bash
./gradlew test jacocoTestReport
```

The report will be available at:
```
build/reports/jacoco/test/html/index.html
```

## Troubleshooting

### Error: "Connection refused" to database
- Verify Docker is running: `docker ps`
- Verify PostgreSQL is up: `docker-compose ps`
- Verify credentials in `ms_franchise.env`

### Error: "Port 8080 already in use"
- Change port in `ms_franchise.env`: `PORT=8081`
- Or stop the process using port 8080

### Error: "Failed to fetch" in Swagger
- Verify the application is running
- Verify CORS configuration in `application.yaml`
- Make sure Swagger UI origin is in `allowed-origins`

## Technologies Used

- **Spring Boot 4.0.1** - Main framework
- **Spring WebFlux** - Reactive programming
- **R2DBC PostgreSQL** - Reactive driver for PostgreSQL
- **Lombok** - Boilerplate code reduction
- **Gradle** - Dependency management and build
- **Docker** - Service containerization
- **JUnit 5** - Testing
- **Mockito** - Mocking for tests

## Contact and Support

For more information about Clean Architecture, check:
- [Clean Architecture — Isolating the Details](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)
- [Repository Design Pattern](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)
