# Sakila Analytics Backend

A GraphQL API built with Spring Boot for serving analytics data from the Sakila DVD rental database.

## Overview

This backend provides a comprehensive analytics API for the Sakila DVD rental database, exposing key business metrics through GraphQL queries. It's designed to power a React dashboard with 5 key business metrics, all filterable by store location and date range.

## Features

- **GraphQL API** with 5 main query endpoints
- **Business Analytics** including:
  - Top rented films
  - Revenue by category
  - Top customers by spending
  - Key business metrics (revenue & active rentals)
  - Recent transactions
- **Flexible Filtering** by store ID and date range
- **Spring Boot 3.2+** with Java 17+
- **PostgreSQL/MySQL** database support
- **Comprehensive Testing** with JUnit 5 and Mockito

## Tech Stack

- **Framework**: Spring Boot 3.2+ with Java 17+
- **GraphQL**: Spring for GraphQL (spring-boot-starter-graphql)
- **Database**: PostgreSQL 15+ (MySQL 8+ alternative)
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Testing**: JUnit 5 + Mockito
- **Logging**: SLF4J + Logback

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL or MySQL database with Sakila sample database

### Database Setup

1. **Install PostgreSQL/MySQL**
2. **Load Sakila Database**:
   ```bash
   # For PostgreSQL
   psql -U postgres -c "CREATE DATABASE pagila;"
   psql -U postgres -d pagila -f sakila-schema.sql
   psql -U postgres -d pagila -f sakila-data.sql
   
   # For MySQL
   mysql -u root -e "CREATE DATABASE sakila;"
   mysql -u root sakila < sakila-schema.sql
   mysql -u root sakila < sakila-data.sql
   ```

### Configuration

1. **Environment Variables** (create `.env` file):
   ```bash
   DB_USERNAME=postgres
   DB_PASSWORD=your_password
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=pagila
   ```

2. **Database Configuration** (edit `application.yml` if needed):
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/pagila
       username: ${DB_USERNAME:postgres}
       password: ${DB_PASSWORD:postgres}
   ```

### Running the Application

1. **Build the project**:
   ```bash
   mvn clean install
   ```

2. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

3. **Access GraphQL Playground**:
   - Open http://localhost:8080/graphiql in your browser
   - Explore the API using the interactive GraphQL interface

## API Endpoints

### GraphQL Queries

#### 1. Get Top Rented Films
```graphql
query {
  getTopRentedFilms(storeId: 1, startDate: "2023-01-01", endDate: "2023-12-31") {
    filmId
    title
    rentalCount
  }
}
```

#### 2. Get Revenue by Category
```graphql
query {
  getRevenueByCategory(storeId: 1, startDate: "2023-01-01", endDate: "2023-12-31") {
    category
    revenue
    percentage
  }
}
```

#### 3. Get Top Customers
```graphql
query {
  getTopCustomers(storeId: 1, startDate: "2023-01-01", endDate: "2023-12-31") {
    customerId
    fullName
    totalRentals
    totalSpent
  }
}
```

#### 4. Get Key Metrics
```graphql
query {
  getKeyMetrics(storeId: 1, startDate: "2023-01-01", endDate: "2023-12-31") {
    totalRevenue
    activeRentals
  }
}
```

#### 5. Get Recent Transactions
```graphql
query {
  getRecentTransactions(storeId: 1, startDate: "2023-01-01", endDate: "2023-12-31", limit: 10) {
    paymentId
    customerName
    filmTitle
    amount
    paymentDate
  }
}
```

## Project Structure

```
src/
├── main/
│   ├── java/com/sakila/analytics/
│   │   ├── SakilaAnalyticsApplication.java
│   │   ├── config/
│   │   │   ├── GraphQLConfig.java
│   │   │   └── CorsConfig.java
│   │   ├── model/
│   │   │   ├── entity/          # JPA Entities
│   │   │   └── dto/            # Data Transfer Objects
│   │   ├── repository/          # Spring Data JPA repositories
│   │   ├── service/             # Business logic layer
│   │   └── resolver/            # GraphQL query resolvers
│   └── resources/
│       ├── application.yml      # Spring Boot configuration
│       ├── application-dev.yml  # Development settings
│       ├── application-prod.yml # Production settings
│       └── graphql/
│           └── schema.graphqls  # GraphQL schema definition
└── test/                       # Unit and integration tests
```

## Testing

Run all tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=AnalyticsServiceTest
```

## Docker Support

Using Docker Compose (recommended for development):

```bash
# Start PostgreSQL and the application
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## Environment Profiles

- **Development**: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
- **Production**: `mvn spring-boot:run -Dspring-boot.run.profiles=prod`

## API Documentation

### GraphQL Schema

The complete GraphQL schema is available at `/graphql/schema.graphqls` and includes:

- **Query Types**: 5 main analytics queries
- **Data Types**: FilmRental, CategoryRevenue, CustomerStats, Metrics, Transaction
- **Filtering**: Optional storeId, startDate, endDate parameters
- **Pagination**: Built-in limits for performance

### Response Formats

All responses follow consistent patterns:
- **Arrays**: For multi-result queries
- **Objects**: For single-result queries
- **Null Handling**: Graceful null handling for optional filters
- **Date Formats**: ISO 8601 for dates, YYYY-MM-DD for input

## Performance Considerations

- **Database Indexes**: Ensure proper indexes on frequently queried columns
- **Connection Pooling**: Configured via Spring Boot defaults
- **Query Optimization**: Custom JPQL queries for complex analytics
- **Caching**: Can be added via Spring Cache abstraction
- **Pagination**: Built-in limits prevent large result sets

## Troubleshooting

### Common Issues

1. **Database Connection**:
   - Verify database is running
   - Check connection parameters in `application.yml`
   - Ensure Sakila database is loaded

2. **GraphQL Schema Errors**:
   - Check `schema.graphqls` syntax
   - Verify resolver method names match schema
   - Restart application after schema changes

3. **Performance Issues**:
   - Check database query performance
   - Verify proper indexes exist
   - Consider adding caching

### Logging

Enable debug logging:
```yaml
logging:
  level:
    com.sakila.analytics: DEBUG
    org.springframework.graphql: DEBUG
    org.hibernate.SQL: DEBUG
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run all tests and ensure they pass
6. Submit a pull request

## License

This project is licensed under the MIT License.
