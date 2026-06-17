# Learning Log

## 2026-06-17

### What I Worked On

- Cleaned up the project to use MyBatis-Plus consistently.
- Removed XML mapper usage for basic CRUD.
- Added JDK 17 project enforcement.
- Changed database password configuration to use environment variables.
- Prepared the project for GitHub as a Spring Boot learning repository.

### Notes

- `BaseMapper<Coffee>` already provides methods like `insert`, `selectById`, `selectList`, `updateById`, and `deleteById`.
- MyBatis-Plus `insert()` returns an `int`, meaning affected rows, not a `boolean`.
- Public repositories should not contain local database passwords.

### Next Ideas

- Add request validation.
- Refactor controller to call service instead of mapper directly.
- Add an integration test profile with H2 or Testcontainers.
- Write notes about common Spring Boot annotations.
