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

## Project Architecture

```text
.
├── .github/
│   └── workflows/
│       └── ci.yml
├── docs/
│   └── learning-log.md
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── CoffeeController.java
│   │   │   │   └── CoffeeShopController.java
│   │   │   ├── entity/
│   │   │   │   └── Coffee.java
│   │   │   ├── exception/
│   │   │   │   └── CoffeeNotFoundException.java
│   │   │   ├── handler/
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── mapper/
│   │   │   │   └── CoffeeMapper.java
│   │   │   └── service/
│   │   │       └── CoffeeService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.yml
│   └── test/
│       ├── java/com/example/demo/
│       │   ├── CoffeeControllerTest.java
│       │   ├── CoffeeServiceTest.java
│       │   └── DemoApplicationTests.java
│       └── resources/
│           └── mockito-extensions/
│               └── org.mockito.plugins.MockMaker
├── .java-version
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

### Layer Responsibilities

| Layer | Main Files | Responsibility |
| --- | --- | --- |
| Application entry | `DemoApplication.java` | Starts the Spring Boot application and scans mapper interfaces. |
| Controller | `CoffeeController`, `CoffeeShopController` | Receives HTTP requests, maps URLs to Java methods, and returns responses. |
| Service | `CoffeeService` | Holds business logic and coordinates mapper calls. |
| Mapper | `CoffeeMapper` | Extends MyBatis-Plus `BaseMapper<Coffee>` and provides CRUD access to the database. |
| Entity | `Coffee` | Represents the `coffee` table and maps Java fields to database columns. |
| Exception | `CoffeeNotFoundException` | Represents domain-specific errors. |
| Handler | `GlobalExceptionHandler`, `ErrorResponse` | Converts exceptions into consistent API error responses. |
| Configuration | `application.yml`, `application.properties` | Stores server, datasource, MyBatis-Plus, and app-specific settings. |
| Tests | `CoffeeControllerTest`, `CoffeeServiceTest`, `DemoApplicationTests` | Verifies controller behavior, service behavior, and application context startup. |

### Request Flow

```text
Client
  -> Controller
  -> Service
  -> Mapper
  -> MyBatis-Plus
  -> MySQL
```

For example, when adding a coffee:

1. `POST /coffee/add` receives JSON in `CoffeeController`.
2. The request body is converted into a `Coffee` object.
3. MyBatis-Plus calls `CoffeeMapper.insert(coffee)`.
4. MySQL stores the row and auto-generates the id.
5. The API returns a success message.

### MyBatis-Plus Usage

This project uses MyBatis-Plus instead of handwritten XML mapper SQL for basic CRUD.

`CoffeeMapper` extends:

```java
BaseMapper<Coffee>
```

That gives the project built-in methods such as:

- `insert`
- `selectById`
- `selectList`
- `updateById`
- `deleteById`

The current project intentionally does not use `CoffeeMapper.xml` for these basic operations.

### Configuration Design

Database settings are read from environment variables, with local defaults:

```yaml
spring:
  datasource:
    url: ${DB_URL:jdbc:mysql://localhost:3306/coffee_shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true}
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:}
```

This keeps local database passwords out of GitHub.

### Build And CI

The project is pinned to JDK 17:

- `.java-version` documents the project Java version.
- `pom.xml` uses Maven Enforcer to reject non-JDK 17 builds.
- GitHub Actions runs tests on JDK 17 for every push and pull request to `main`.

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
