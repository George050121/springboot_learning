# Spring Boot Learning Project

This repository records my daily Spring Boot learning progress. The project starts from a small coffee shop API and will grow as I learn more about Spring Boot, MyBatis-Plus, testing, configuration, and deployment.

## Current Stack

- Java 17
- Spring Boot 3.2.11
- Spring Security
- MyBatis-Plus 3.5.7
- MySQL
- Maven Wrapper
- JUnit 5 / Spring Boot Test
- Lombok

## What This Project Includes

- Basic REST APIs for a coffee shop demo
- MyBatis-Plus mapper usage
- Service and controller layers
- Global exception handling
- Configuration with environment variables
- Database-backed login authentication with Spring Security
- BCrypt password verification
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
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── CoffeeController.java
│   │   │   │   └── CoffeeShopController.java
│   │   │   ├── entity/
│   │   │   │   ├── Coffee.java
│   │   │   │   └── User.java
│   │   │   ├── exception/
│   │   │   │   └── CoffeeNotFoundException.java
│   │   │   ├── handler/
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── mapper/
│   │   │   │   ├── CoffeeMapper.java
│   │   │   │   └── UserMapper.java
│   │   │   └── service/
│   │   │       ├── CoffeeService.java
│   │   │       └── UserDetailsServiceImpl.java
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
| Security config | `SecurityConfig` | Configures protected routes, form login, and BCrypt password encoding. |
| Controller | `CoffeeController`, `CoffeeShopController` | Receives HTTP requests, maps URLs to Java methods, and returns responses. |
| Service | `CoffeeService`, `UserDetailsServiceImpl` | Holds business logic and loads database users for Spring Security authentication. |
| Mapper | `CoffeeMapper`, `UserMapper` | Extends MyBatis-Plus `BaseMapper` interfaces and provides CRUD access to database tables. |
| Entity | `Coffee`, `User` | Represents the `coffee` and `sys_user` tables and maps Java fields to database columns. |
| Exception | `CoffeeNotFoundException` | Represents domain-specific errors. |
| Handler | `GlobalExceptionHandler`, `ErrorResponse` | Converts exceptions into consistent API error responses. |
| Configuration | `application.yml`, `application.properties` | Stores server, datasource, MyBatis-Plus, and app-specific settings. |
| Tests | `CoffeeControllerTest`, `CoffeeServiceTest`, `DemoApplicationTests` | Verifies controller behavior, service behavior, and application context startup. |

### Request Flow

```text
Client
  -> Spring Security Filter Chain
  -> Controller
  -> Service
  -> Mapper
  -> MyBatis-Plus
  -> MySQL
```

For example, when adding a coffee:

1. `POST /coffee/add` receives JSON in `CoffeeController`.
2. Spring Security checks whether the user is authenticated.
3. The request body is converted into a `Coffee` object.
4. MyBatis-Plus calls `CoffeeMapper.insert(coffee)`.
5. MySQL stores the row and auto-generates the id.
6. The API returns a success message.

### Authentication Flow

The application uses Spring Security form login.

```text
Browser
  -> GET /login
  -> POST /login with username, password, and CSRF token
  -> UserDetailsServiceImpl
  -> UserMapper
  -> sys_user table
  -> BCryptPasswordEncoder.matches(...)
```

Important details:

- All routes require login except static resources such as `/css/**` and `/js/**`.
- Users are loaded from the `sys_user` table.
- Passwords in `sys_user.password` must be BCrypt hashes, not plain text.
- Roles are normalized so both `USER` and `ROLE_USER` can be handled safely.

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

When running from VS Code, the Java process may not automatically inherit shell environment variables. A local `.vscode/launch.json` can pass `DB_USERNAME` and `DB_PASSWORD` to the app. The `.vscode/` folder is ignored by Git, so local secrets do not get committed.

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

The default Spring Security login page is available at:

```text
http://localhost:9090/login
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
