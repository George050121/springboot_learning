# Spring Boot Learning Project

This repository records my daily Spring Boot learning progress. The project starts from a small coffee shop API and will grow as I learn more about Spring Boot, MyBatis-Plus, testing, configuration, and deployment.

## Current Stack

- Java 17
- Spring Boot 3.2.11
- MyBatis-Plus 3.5.7
- MySQL
- Maven Wrapper
- JUnit 5 / Spring Boot Test

## What This Project Includes

- Basic REST APIs for a coffee shop demo
- MyBatis-Plus mapper usage
- Service and controller layers
- Global exception handling
- Configuration with environment variables
- Unit tests and web layer tests
- A daily learning log

## Run Locally

Make sure JDK 17 is active:

```bash
java -version
```

Configure your local database through environment variables if needed:

```bash
export DB_URL="jdbc:mysql://localhost:3306/coffee_shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
export DB_USERNAME="root"
export DB_PASSWORD="your_password"
```

Run tests:

```bash
./mvnw clean test
```

Start the app:

```bash
./mvnw spring-boot:run
```

The app runs on:

```text
http://localhost:9090
```

## API Notes

Main coffee endpoints are under:

```text
/coffee
```

Examples:

- `GET /coffee/menu`
- `GET /coffee/info/{id}`
- `POST /coffee/add`
- `PUT /coffee/update`
- `DELETE /coffee/delete/{id}`

## Learning Log

Daily notes are recorded in [docs/learning-log.md](docs/learning-log.md).

## Goal

Use this repository as a long-term Spring Boot learning journal: small daily changes, clear commits, and notes about what I learned.
