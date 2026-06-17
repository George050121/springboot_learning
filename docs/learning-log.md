# Learning Log

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
- All kinds of annotations in Spring Boot, such as @RestController, @RequestMapping, @Autowired, and @RequestBody, are utilized to simplify configuration and implement Inversion of Control (IoC).

### Next Ideas

- Add request validation.
- Refactor controller to call service instead of mapper directly.
- Add an integration test profile with H2 or Testcontainers.
- Write notes about common Spring Boot annotations.
