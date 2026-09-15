# Arquitectura de TaskFlow

Bienvenido a TaskFlow. Este documento explica la estructura del proyecto, el flujo de una petición `POST /projects/{projectId}/tasks`, dónde residen las reglas de negocio, cómo funciona la seguridad con JWT y cómo están organizados los tests.

- Paquete raíz: `com.taskflow`

Capas y paquetes

- Capa Controller (HTTP): bajo `src/main/java/com/taskflow/controller`.
  - Clases principales: `ProjectController`, `TaskController`, `AuthController`.
  - Rol: recibir solicitudes HTTP, validar DTOs (`record`), y delegar a la capa de servicio.
  - Ejemplo de archivo: `src/main/java/com/taskflow/controller/TaskController.java`.

- Capa Service (casos de uso): bajo `src/main/java/com/taskflow/service`.
  - Clases principales: `ProjectService`, `TaskService`, `AuthService`, `JpaUserDetailsService`.
  - Rol: coordinar la lógica de aplicación, orquestar repositorios y aplicar reglas de negocio de alto nivel.
  - Importante: aquí se llaman a las fábricas/constructores de entidades y se aplican validaciones que dependen de reglas de aplicación.
  - Ejemplo de archivo: `src/main/java/com/taskflow/service/TaskService.java`.

- Capa Repository (persistencia): bajo `src/main/java/com/taskflow/repository`.
  - Interfaces Spring Data JPA: `ProjectRepository`, `TaskRepository`, `UserRepository`.
  - Rol: acceso a la base de datos, consultas y operaciones CRUD.
  - Ejemplo de archivo: `src/main/java/com/taskflow/repository/TaskRepository.java`.

- Capa Model (dominio): bajo `src/main/java/com/taskflow/model`.
  - Entidades con comportamiento rico: `Task`, `Project`, `User`.
  - Rol: contener invariantes y reglas de dominio (métodos como `Task.crear(...)`, `Task.estaVencida()`).
  - Ejemplo de archivo: `src/main/java/com/taskflow/model/Task.java`.

- DTOs y Mappers: bajo `src/main/java/com/taskflow/dto` y `src/main/java/com/taskflow/mapper`.
  - DTOs son `record` con validación Bean Validation y `@Valid` en controladores.
  - Mappers: funciones que convierten entre entidades y DTOs (`TaskMapper`).
  - Ejemplos: `src/main/java/com/taskflow/dto/TaskRequest.java`, `src/main/java/com/taskflow/mapper/TaskMapper.java`.

Recorrido de `POST /projects/{projectId}/tasks`

1. Petición HTTP llega a `POST /projects/{projectId}/tasks` en `src/main/java/com/taskflow/controller/TaskController.java`.
2. Spring MVC deserializa el cuerpo a un DTO (`TaskRequest`) y aplica validaciones con Bean Validation (`@Valid`). Si la validación falla, `GlobalExceptionHandler` responde `400 Bad Request`.
3. `TaskController.createTask` verifica que el proyecto exista llamando a `projectService.buscarPorId(projectId)` y lanzando `ProjectNotFoundException` si no existe.
4. `TaskController` llama a `taskService.crear(request, projectId)`.
5. `TaskService.crear` convierte el DTO a entidad usando `TaskMapper.aEntidadNueva(request, projectId)` (esa fábrica pasa por `Task.crear` y aplica la regla de fecha) y guarda con `TaskRepository.save(...)`.
6. Tras el save, `TaskController` construye la cabecera `Location` hacia `/tasks/{id}` y responde `201 Created` con el cuerpo `TaskMapper.aResponse(creada)`.
7. Excepciones relevantes: `TaskValidationException` (reglas de entrada) → 400; `TaskNotFoundException`/`ProjectNotFoundException` → 404; `TaskStateException` → 422 (según lo maneja `GlobalExceptionHandler`).

Dónde viven las reglas de negocio

- Reglas de dominio (invariantes, validaciones intrínsecas a la entidad): implementadas en las entidades del dominio dentro de `src/main/java/com/taskflow/model`, por ejemplo `Task.crear(...)`, `Task.estaVencida()`.
- Reglas de aplicación o casos de uso (orquestación, permisos, flujos): implementadas en la capa `service` (`src/main/java/com/taskflow/service`), por ejemplo `TaskService` y `ProjectService`.
- Validación de entrada y formatos: en los DTOs (`src/main/java/com/taskflow/dto`) usando Bean Validation (`@NotNull`, `@Size`, etc.).
- Reglas de seguridad (quién puede borrar/editar): combinan `@PreAuthorize` en los controladores y clases de seguridad personalizadas (por ejemplo `ProjectSecurity`) en `src/main/java/com/taskflow/security`.

Cómo funciona la seguridad con JWT

- Endpoints públicos: `/auth/**`, `/info`, Swagger, consola H2 y archivos estáticos en `src/main/resources/static`.
- Para todo lo demás, Spring Security exige un token JWT en el header `Authorization: Bearer <token>`.
- Flujo de autenticación:
  1. El usuario envía credenciales a `POST /auth/login` (controlador: `src/main/java/com/taskflow/controller/AuthController.java`).
  2. `AuthService` valida credenciales contra `UserRepository` y, si son correctas, genera un JWT firmado que contiene el `username` y roles/claims.
  3. El JWT se devuelve al cliente. En peticiones subsecuentes, el `JwtAuthenticationFilter` (o filtro equivalente) extrae y valida el token, reconstruye la autenticación y la coloca en el `SecurityContext`.
  4. Los `@PreAuthorize` en controladores y la lógica de seguridad (por ejemplo `ProjectSecurity`) usan esa autenticación para permitir o denegar acciones.
- La configuración de seguridad está en `src/main/java/com/taskflow/config` o en clases con `@Configuration` relevantes (p. ej. `SecurityConfig`).
- JWT es sin estado: no hay sesión en servidor; revocación requiere estrategia adicional (lista de tokens, expiración corta, rotación).

Organización de tests

- Unit tests: usan JUnit 5 y Mockito sin arrancar Spring. Están en `src/test/java/...` y cubren lógicas de unidad en `service` y `model`. Nombres comunes: `*Test.java`.
- Slice tests: `@WebMvcTest` para controladores y `@DataJpaTest` para repositorios cuando se necesita soporte parcial de Spring.
- Integration tests: `@SpringBootTest` con perfil `test` para pruebas de integración completas. Tests de integración que usan Testcontainers tienen sufijo `*IT.java` y requieren `-Ddocker.tests=true` para ejecutarse.
- Ejecución local rápida: usar `mvn -q test` para correr la suite normal. Para una sola clase: `mvn -q test -Dtest=TaskServiceTest`.
- Datos de ejemplo y pruebas: el perfil `h2` y la clase `DataSeeder` rellenan datos de ejemplo (usuarios `ana`, `luis`, `admin`) cuando se arranca la aplicación con `mvn spring-boot:run -Dspring-boot.run.profiles=h2`.

Buenas prácticas al trabajar en el proyecto

- Reusar las reglas del dominio que están en `src/main/java/com/taskflow/model` en lugar de duplicarlas en servicios o controladores.
- Usar DTOs (`src/main/java/com/taskflow/dto`) para la comunicación HTTP; nunca exponer entidades JPA directamente.
- Mantener inyección por constructor y evitar Lombok (convención del proyecto).
- Nuevos endpoints que crean recursos deben devolver `201 Created` con cabecera `Location`.
- No modificar tests existentes para que pasen; arreglar el código si uno falla y luego ejecutar `mvn -q test`.

Archivos y clases clave (referencias)

- `src/main/java/com/taskflow/controller/TaskController.java` - controlador HTTP para tareas
- `src/main/java/com/taskflow/controller/ProjectController.java` - controlador de proyectos
- `src/main/java/com/taskflow/controller/AuthController.java` - login y autenticación
- `src/main/java/com/taskflow/service/TaskService.java` - lógica de casos de uso para tareas
- `src/main/java/com/taskflow/repository/TaskRepository.java` - persistencia de tareas
- `src/main/java/com/taskflow/model/Task.java` - entidad `Task` y sus reglas de negocio
- `src/main/java/com/taskflow/dto/TaskRequest.java` - DTO de creación/actualización de tareas
- `src/main/java/com/taskflow/mapper/TaskMapper.java` - conversión entre entidad y DTO
- `src/main/java/com/taskflow/config/SecurityConfig.java` - configuración de Spring Security y filtros JWT
- `src/main/java/com/taskflow/security/ProjectSecurity.java` - reglas específicas de autorización para proyectos
- `src/main/java/com/taskflow/advice/GlobalExceptionHandler.java` - maneja mapeo de excepciones a respuestas HTTP

Si se necesita más detalle (diagramas, ejemplos de código, o un recorrido en vivo por el código), indicarlo y se proveerá una guía paso a paso.
Las fechas límite se validan en la fábrica `Task.crear` (`src/main/java/com/taskflow/model/Task.java`).
