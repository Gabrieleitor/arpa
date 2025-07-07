# ARPA - Article Registration System

System for registering articles that enter the company, developed with Spring Boot following hexagonal architecture.

## Features

- Article entry and exit registration
- Management of identification types, articles and person types
- Hexagonal architecture with Value Objects
- Multi-environment configuration (local, development, production)
- REST API with validations
- Embedded database (H2) and PostgreSQL

## Technologies

- Java 24
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL / H2
- Gradle
- Docker

## Project Structure

```
src/main/java/com/acueducto/arpa/
│
├── domain/                        # Domain Layer (business logic)
│   ├── model/                     # Entities and Value Objects
│   │   └── vo/                    # Value Objects
│   ├── ports/                     # Ports (interfaces for repositories)
│   │   └── repository/
│   └── service/                   # Domain services (use cases)
│
├── application/                   # Application Layer (handlers)
│   └── handler/
│
├── infrastructure/                # Infrastructure Layer (adapters)
│   └── adapter/
│       ├── persistence/           # Persistence adapters (JPA, implementations)
│       │   ├── mapper/
│       │   └── entity/
│       └── rest/                  # REST controllers and DTOs
│           └── dto/
│
└── ArpaApplication.java           # Main Spring Boot application entry point
```

## Environment Configuration

### Available Profiles

- **local** (default): H2 in memory
- **dev**: PostgreSQL for development
- **prod**: PostgreSQL for production

### Default Configuration

If `SPRING_PROFILES_ACTIVE` is not specified, the application automatically uses the `local` profile.

### Environment Variables

```bash
# For development
export SPRING_PROFILES_ACTIVE=dev

# For production
export SPRING_PROFILES_ACTIVE=prod

# For local (optional, it's the default value)
export SPRING_PROFILES_ACTIVE=local
```

## Installation and Execution

### Prerequisites

- Java 24
- Gradle
- PostgreSQL (for dev and prod environments)

### Local Execution

```bash
# Clone the repository
git clone <repository-url>
cd arpa

# Run with local profile (H2)
./gradlew bootRun

# Or specify the profile explicitly
./gradlew bootRun --args='--spring.profiles.active=local'
```

### Docker Execution

```bash
# Build the image
docker build -t arpa-app .

# Run with local profile
docker run -p 8080:8080 arpa-app

# Run with specific profile
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev arpa-app
```

### Test Execution

```bash
# Run all tests
./gradlew test

# Run tests with report
./gradlew test --info
```

## API Endpoints

### Article Registration

```
POST /api/articles/entry
POST /api/articles/{id}/exit
```

### Catalog Management

```
GET    /api/identification-types
POST   /api/identification-types
PUT    /api/identification-types/{id}
DELETE /api/identification-types/{id}

GET    /api/article-types
POST   /api/article-types
PUT    /api/article-types/{id}
DELETE /api/article-types/{id}

GET    /api/person-types
POST   /api/person-types
PUT    /api/person-types/{id}
DELETE /api/person-types/{id}
```

### Entry Registration Example

```json
POST /api/articles/entry
{
  "identificationTypeId": 1,
  "personTypeId": 1,
  "articleTypeId": 1,
  "name": "John Doe",
  "make": "HP",
  "serial": "123456789",
  "comment": "Laptop for development"
}
```

## Database Configuration

### Local (H2)
- In-memory database
- H2 Console available at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

### Development and Production (PostgreSQL)
- Configure in `application-dev.properties` and `application-prod.properties`
- Create corresponding database and user
- Ensure connectivity from the application

## Development

### Hexagonal Architecture

The project follows hexagonal architecture principles:

- **Domain**: Business logic and entities
- **Application**: Use cases and services
- **Infrastructure**: Adapters (REST, database)

### Value Objects

Main fields use Value Objects to encapsulate validations:

- `Name`: Required validation
- `Make`: Required validation
- `Serial`: Required validation
- `Comment`: Length validation (max 255 characters)

### Adding New Endpoints

1. Create DTO in `infrastructure/adapter/rest/dto/`
2. Add method in the corresponding controller
3. Implement logic in the application service
4. Add unit tests

## Deployment

### Azure

To deploy on Azure:

1. Configure environment variables in Azure App Service
2. Set `SPRING_PROFILES_ACTIVE=prod`
3. Configure connection to Azure Database for PostgreSQL
4. Deploy using Azure CLI or GitHub Actions

### Production Environment Variables

```bash
SPRING_PROFILES_ACTIVE=prod
SPRING_DATASOURCE_URL=jdbc:postgresql://your-azure-postgresql-server:5432/arpaprod
SPRING_DATASOURCE_USERNAME=your-username
SPRING_DATASOURCE_PASSWORD=your-password
```

## Contribution

1. Fork the project
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is under the MIT License - see the [LICENSE.md](LICENSE.md) file for details. 