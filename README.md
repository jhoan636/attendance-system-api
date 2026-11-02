# Attendance System API · Aula Taller

API REST para el control de asistencia de Aula Taller: registra la asistencia de los estudiantes a cursos de acompañamiento y servicios, organizada por sede y programa académico. Está en producción y alimenta el frontend en React ([attendance-system-web](https://github.com/jhoan636/attendance-system-web)).

## Stack

- Java 17 · Spring Boot 4
- Spring Web MVC · Spring Data JPA · Bean Validation
- PostgreSQL · Flyway para versionar el esquema
- springdoc-openapi (Swagger UI)
- Lombok · Maven
- Despliegue en Railway

## Dominio

| Recurso | Qué representa |
| --- | --- |
| `User` | Personas del sistema, identificadas por documento |
| `Campus` | Sedes |
| `AcademicProgram` | Programas académicos |
| `AccompanimentCourse` | Cursos de acompañamiento |
| `ServiceType` | Tipos de servicio que generan asistencia |
| `Attendance` | Registro de asistencia (con `createdAt` en UTC) |
| `RoleAccessCode` | Códigos de acceso por rol |

## Arquitectura

Capas clásicas de Spring: `controller` → `service` → `repository`, con `dto` para separar la API del modelo JPA (`entity`) y un `GlobalExceptionHandler` que devuelve errores con un formato único (`ApiError`).

```
src/main/java/com/aulataller/attendance
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
└── service
```

## Decisiones técnicas

- **Fechas en UTC:** la conexión JDBC usa UTC y `createdAt` usa `Instant`, para que los registros no cambien según la zona horaria del servidor.
- **Migraciones con Flyway:** el esquema se versiona con migraciones, con perfiles separados para local y producción.
- **Usuarios activos:** la búsqueda por documento solo devuelve usuarios activos.

## Cómo ejecutarlo

Requisitos: Java 17 y PostgreSQL.

```bash
# variables de conexión (ajústalas a tu entorno)
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/attendance
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=postgres

./mvnw spring-boot:run
```

Flyway crea las tablas al arrancar. La documentación interactiva queda en `http://localhost:8080/swagger-ui.html`.

## Autor

Jhoan Londoño Escobar · [LinkedIn](https://www.linkedin.com/in/jhoan-londono-escobar)
