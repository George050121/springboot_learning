# Learning Log

## 2026-06-18

### What I Worked On

- Added a database-backed login check with Spring Security.
- Added `SecurityConfig` to configure form login, protected routes, and `BCryptPasswordEncoder`.
- Added `User`, `UserMapper`, and `UserDetailsServiceImpl` so Spring Security can load users from the `sys_user` table.
- Added `spring-boot-starter-security` and Lombok dependencies.
- Updated controller tests after adding Spring Security so the coffee controller web-layer test still focuses on the coffee endpoint.
- Removed the temporary password generation endpoint because exposing a helper endpoint like `/generate-password` is risky and should not stay in the app.
- Updated the local database user password to a BCrypt hash for the known test password.

### New Things I Learned

- Spring Security automatically provides a default `/login` page when form login is enabled.
- `UserDetailsService` is the bridge between my own database table and Spring Security's authentication system.
- `BCryptPasswordEncoder.encode("123456")` creates a salted hash, so the output is different each time.
- BCrypt hashes are one-way. The app does not decrypt the hash; it checks a raw password with `matches(rawPassword, encodedPassword)`.
- Passwords stored in the database should be BCrypt hashes, not plain text.
- Spring Security form login uses CSRF protection by default, so direct `POST /login` requests need the CSRF token unless CSRF is explicitly disabled.
- The role value needs care: if the database stores `ROLE_USER`, the code should not add another `ROLE_` prefix and create `ROLE_ROLE_USER`.

### Bugs I Hit And How I Fixed Them

- Login failed even though the username and password looked correct.
  - Cause: the app was not getting the MySQL password when started from VS Code, so `DB_PASSWORD` fell back to an empty string.
  - Fix: keep `application.yml` using `${DB_PASSWORD:}` and pass the local environment variable through VS Code's ignored `.vscode/launch.json`.

- I confused the MySQL account with the application login account.
  - Cause: `root / database_password` is the database credential, but the web login user comes from `sys_user`.
  - Fix: verified the `sys_user` table and used the actual app account from that table.

- A temporary password generator endpoint was useful while learning but unsafe to keep.
  - Cause: an endpoint that generates password hashes should not be publicly available in the app.
  - Fix: deleted the temporary controller and kept BCrypt password generation as a local-only development action.

- `CoffeeControllerTest` failed after adding Security and user mapper scanning.
  - Cause: `@WebMvcTest` loaded the web slice with security filters and needed a mock for the newly scanned user mapper.
  - Fix: disabled filters for this controller-focused test and mocked `UserMapper`.

### Notes

- Environment variables are process-specific. A variable set in one terminal is not automatically visible to every app launched from VS Code or macOS.
- `application.yml` should keep this safe pattern:

```yaml
spring:
  datasource:
    password: ${DB_PASSWORD:}
```

- Local IDE secrets can go into `.vscode/launch.json` because `.vscode/` is ignored by Git in this project.
- Before pushing, check `git status` and make sure local secret files are ignored.

### Next Ideas

- Add a custom login page later.
- Add a registration or admin-only user creation flow that hashes passwords before saving.
- Add integration tests for the login flow.
- Consider a separate `application-local.yml` profile for local-only settings.

## 2026-06-17

### What I Worked On

- Cleaned up the project to use MyBatis-Plus consistently.
- Removed XML mapper usage for basic CRUD. It is no longer used because MyBatis-Plus supports built-in generic CRUD methods through the `BaseMapper` interface.
- Enforced JDK 17 instead of JDK 23 for long-term maintenance stability.
- Changed database password configuration to use environment variables.
- Prepared the project for GitHub as a Spring Boot learning repository.

### Notes

- `BaseMapper<Coffee>` already provides methods like `insert`, `selectById`, `selectList`, `updateById`, and `deleteById`.
- MyBatis-Plus `insert()` returns an `int`, meaning affected rows, not a `boolean`.
- Public repositories should not contain local database passwords.
- Learned that Lombok can reduce boilerplate code such as getters, setters, and constructors, but this project currently uses explicit JavaBean methods for clearer JDK 17 compatibility.
- Use Postman to test RESTful API endpoints and verify the correctness of various HTTP methods (GET, POST, PUT, DELETE).
- All kinds of annotations in Spring Boot, such as @RestController, @RequestMapping, @Autowired, and @RequestBody, are utilized to simplify configuration and implement Inversion of Control (IoC).

### Next Ideas

- Add request validation.
- Refactor controller to call service instead of mapper directly.
- Add an integration test profile with H2 or Testcontainers.
- Write notes about common Spring Boot annotations.
