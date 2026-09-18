# Evidencia de la semana · GitHub Copilot

| 👤 Alumno | 🔗 Repositorio |
| :--- | :--- | 
| **Julian Padron Nuñez** | [`juliannp253/taskflow-copilot-juliannp253`](https://github.com/juliannp253/taskflow-copilot-juliannp253)|


---

## 📌 Día 1 · La CLI

### ¿Qué construí?

Con las prácticas llevadas a cabo esta semana empecé a familiarizarme con el uso de **Copilot mediante la CLI** y ver de lo que es capaz de hacer al abrir el agente dentro de un proyecto.

Al abrir el agente dentro de uno de nuestros proyectos, este es capaz de **leer, buscar, editar, o eliminar archivos** (según nuestros permisos) para poder contestar a preguntas que le hagamos o realizar tareas que le designemos dentro del proyecto.

| 🔎 Diagnóstico de contenido del repo | ❓ Comprobación de endpoints |
| :---: | :---: |
| ![¿Qué hay en este repo?](evidencia/dia1/img/pregunta1.png) | ![¿Existe un endpoint que no existe?](evidencia/dia1/img/pregunta3.png) |
| *Respuesta del agente sobre la estructura y contenido del proyecto* | *Validación del agente ante la consulta de un endpoint inexistente* |

#### ⚡ Ejecución de comandos y permisos

En cuestión de comandos, un agente es capaz de ejecutar los comandos en la terminal siempre que se lo autoricemos. Para la ejecución de un comando, Copilot siempre nos pide una autorización en la cual podemos:
- **Aprobar una vez**
- **Aprobar siempre**
- **Denegar** la ejecución de dicho comando

> **Prompt ejecutado en terminal:**
> ```bash
> Corre mvn -q test y dime solo si terminó bien o con error.
> ```

![aprobación de comando](evidencia/dia1/img/mp-8.png)
*Solicitud interactiva de confirmación de permisos en la CLI de Copilot antes de correr pruebas.*

### ¿Dónde está?

Los entregables y archivos modificados correspondientes a este día son:
- [`📁 .github/copilot-instructions.md`](.github/copilot-instructions.md)
- [`📄 docs/ARQUITECTURA.md`](docs/ARQUITECTURA.md)

Aquí podemos encontrar el resultado de la ejecución del siguiente prompt:

> **Prompt utilizado para generar la documentación de arquitectura:**
> ```text
> Escribe el archivo docs/ARQUITECTURA.md para un desarrollador que llega nuevo a TaskFlow. Explica: las capas y paquetes; el recorrido completo de POST /projects/{projectId}/tasks desde el controlador hasta la base de datos; dónde viven las reglas de negocio; cómo funciona la seguridad con JWT; y cómo están organizados los tests. Escribe entre backticks cada clase del proyecto (por ejemplo `TaskService`) y cada archivo con su ruta desde la raíz del repositorio (por ejemplo `src/main/java/com/taskflow/model/Task.java`). No modifiques ningún otro archivo.
> ```


### ¿Cómo se comprueba?

> - **Archivo de comprobación:** [`evidencia/dia1/verificador.txt`](evidencia/dia1/verificador.txt)
> - **Criterio de validación:** La última línea debe contener `0 NO EXISTE`.
> - **Resultado verificado:** `Resumen: 66 OK · 3 REVISA · 0 EXTERNA · 0 NO EXISTE · 19 sin verificar`


### ¿Qué no salió?

> **Nada** (todos los ejercicios y comandos se ejecutaron exitosamente sin errores).

---

## 📌 Día 2 · Especificar, implementar y revisar

### ¿Qué construí? 

Este día el agente empezó a escribir código, en específico dos endpoints nuevos para la API de TaskFlow, **GET /tasks/overdue** y **GET /tasks/unassigned**, con sus tests correspondientes. Para llevar a cabo estas implementaciones, proporcioné especifícaciones de cómo debería de implementar las nuevas funcionalidades (**specs/overdue.md** y **specs/unassigned.md**), el agente las lee y ahora restringimos a que siga nuestro procedimiento y evitamos que tome sus propias decisiones.

#### 🔹 1. Implementación de `GET /tasks/overdue`

> **Prompt ejecutado en terminal:**
> ```bash
> Implementa la especificación de specs/overdue.md al pie de la letra. Cuando termines, corre mvn -q test y confirma que pasa.
> ```

| 📖 Lectura de Spec y Búsqueda | ✏️ Modificación de `TaskController` | 🧪 Validación con `mvn -q test` |
| :---: | :---: | :---: |
| ![Lectura de spec](evidencia/dia2/img/mp-2.png) | ![Aprobación en TaskController](evidencia/dia2/img/mp-2.1.png) | ![Ejecución de mvn test](evidencia/dia2/img/mp-2.2.png) |
| *Copilot lee `specs/overdue.md` e inspecciona archivos* | *Confirmación de cambios para el endpoint en el controlador* | *Ejecución de la suite de tests para asegurar que todo pasa* |

#### 🔹 2. Implementación de `GET /tasks/unassigned` mediante `/plan`

> **Prompt ejecutado en terminal:**
> ```bash
> /plan Implementa la especificación de specs/unassigned.md al pie de la letra. Cuando termines, corre mvn -q test y confirma que pasa.
> ```

| 🎯 Ejecución del comando `/plan` | 📋 Plan detallado generado por el agente |
| :---: | :---: |
| ![Prompt con /plan](evidencia/dia2/img/mp-6.png) | ![Plan generado por Copilot](evidencia/dia2/img/mp-6.1.png) |
| *Instrucción para generar plan de trabajo antes de editar código* | *Revisión de archivos a cambiar, restricciones y tareas pendientes* |

### ¿Dónde está? 

Los archivos desarrollados, especificaciones y Pull Request correspondientes a este día son:

- **Especificaciones:**
  - [`📄 specs/overdue.md`](specs/overdue.md)
  - [`📄 specs/unassigned.md`](specs/unassigned.md)
- **Controlador y Servicio (Lógica de negocio):**
  - [`☕ src/main/java/com/taskflow/controller/TaskController.java`](src/main/java/com/taskflow/controller/TaskController.java) (endpoints `GET /tasks/overdue` y `GET /tasks/unassigned`)
  - [`☕ src/main/java/com/taskflow/service/TaskService.java`](src/main/java/com/taskflow/service/TaskService.java) (métodos `vencidas()` y `sinResponsable()`)
- **Tests unitarios y de integración:**
  - [`🧪 src/test/java/com/taskflow/unit/TaskServiceTest.java`](src/test/java/com/taskflow/unit/TaskServiceTest.java) (pruebas unitarias de ordenamiento y filtrado, incluyendo `@Nested SinResponsable`)
  - [`🧪 src/test/java/com/taskflow/slice/TaskControllerTest.java`](src/test/java/com/taskflow/slice/TaskControllerTest.java) (pruebas MockMvc de respuesta `200` y DTOs)
- **Pull Request en GitHub:**
  - [`🔗 Pull Request #1`](https://github.com/juliannp253/taskflow-copilot-juliannp253/pull/1) (registrado en [`evidencia/dia2/pr.txt`](evidencia/dia2/pr.txt))

### ¿Cómo se comprueba? 

> - **Pruebas de mutación (verificación de efectividad de tests):**
>   - [`evidencia/dia2/checklist-overdue.txt`](evidencia/dia2/checklist-overdue.txt): Se introdujo un mutante eliminando `.sorted(TaskOrders.POR_FECHA)` en `vencidas()`, provocando fallo inmediato (`[ERROR] Tests run: 8, Failures: 1`). Tras restaurarlo, la suite pasó limpia (`69 tests, 0 failures`).
>   - [`evidencia/dia2/verificador.txt`](evidencia/dia2/verificador.txt): Se quitó `.sorted(TaskOrders.POR_FECHA)` en `sinResponsable()`, detectándose el fallo esperado (`[ERROR] Tests run: 10, Failures: 1, BUILD FAILURE`).
> - **Suite completa de pruebas en `main`:**
>   - [`evidencia/dia2/suite-main.txt`](evidencia/dia2/suite-main.txt): `[INFO] Tests run: 72, Failures: 0, Errors: 0, Skipped: 0` (`BUILD SUCCESS`).
> - **Comprobación manual de endpoints (Semilla H2):**
>   - [`evidencia/dia2/comprobacion.txt`](evidencia/dia2/comprobacion.txt):
>     - `GET /tasks/overdue` ➜ tarea con ID `7` («Corregir bug de fechas», `IN_PROGRESS`).
>     - `GET /tasks/unassigned` ➜ tareas con ID `4, 6` en orden por fecha.
>     - Solicitud sin token ➜ respuesta HTTP `401`.

### ¿Qué no salió? 

Se realizó un primer intento de implementación de **specs/unassigned.md** en donde al pedirle al agente que nos generé un **/plan** previo a implementar el código, sugerí los siguientes cambios en el plan:

> ```text
> El plan no dice qué casos usa el test unit de orden. La spec los pide: el repositorio devuelve, en este orden, una sin responsable con fecha en 10 días, una con responsable, una sin responsable sin fecha y una sin responsable con fecha en 2 días; sinResponsable() devuelve las tres sin responsable en el orden 2 días, 10 días, sin fecha, comparando los ids en orden. Agrégalo al plan.
> ```

---

## 📌 Día 3 · MCP

### ¿Qué construí? 

Model Context Protocol nos permite el conectarle **herramientas** a nuestro agente. En este día le conecté la herramienta del servidor de GitHub para que pudiera abrir un issue en mi repositorio, el de documentación de AWS, y la de Playwright para poder realizar UI tests de TaskFlow. Extra a esto, creé una extra escrita en Java para que el agente pueda hablar con la API.

#### 🔹 1. Interacción con el servidor MCP de GitHub (Creación de Issue)

> **Prompt ejecutado en terminal:**
> ```text
> Usa el servidor MCP de GitHub para crear un issue en el repositorio juliannp253/taskflow-copilot-juliannp253. Título exacto: GET /projects/{id}/summary. Cuerpo: el contenido del archivo issues/summary.md tal cual, sin resumirlo ni cambiarlo. No uses la terminal. Al terminar dime el número del issue y su URL.
> ```

![Copilot hace uso del servidor MCP para crear issue](evidencia/dia3/img/mp-3.png)
*Copilot hace uso del servidor MCP de GitHub para crear el issue #2 con la especificación de resumen del proyecto.*

#### 🔹 2. Configuración y registro de servidores MCP

Se añadieron y habilitaron servidores MCP locales y remotos para dotar al agente de nuevas capacidades:

| 🎭 Playwright (UI Testing) | ☕ TaskFlow (API local en Java) | ☁️ AWS Knowledge (Catálogo en la nube) |
| :---: | :---: | :---: |
| ![Playwright MCP](evidencia/dia3/img/playwright.png) | ![TaskFlow MCP](evidencia/dia3/img/taskflow-mcp.png) | ![AWS Knowledge MCP](evidencia/dia3/img/amazon.png) |
| *Comando: `npx @playwright/mcp@latest --isolated`* | *Comando: `java -jar taskflow-mcp.jar`* | *Transporte HTTP: `knowledge-mcp.global.api.aws`* |

> Comprobamos que cada servidor MCP aparezca disponible y habilitado en Copilot:
> ```bash
> copilot mcp list
> ```

![Lista de servidores MCP habilitados](evidencia/dia3/img/mcp-list.png)
*Verificación en el panel de Copilot: 4 servidores conectados y activos (`aws-knowledge`, `github-mcp-server`, `playwright`, `taskflow`).*

#### 🔹 3. Flujo integrador: Tareas vencidas a Issues de GitHub

Con la combinación del servidor MCP de GitHub y el de TaskFlow, el agente pudo consultar tareas en la API y abrir issues automáticamente por cada tarea vencida detectada.

> **Prompt integrador:**
> ```text
> Usa el servidor MCP taskflow para listar las tareas vencidas. Por cada tarea vencida crea un issue en el repositorio juliannp253/taskflow-copilot-juliannp253 con el servidor MCP de GitHub. Título: Tarea vencida #<id>: <título de la tarea>. Cuerpo: proyecto, prioridad, estado, fecha límite y descripción de la tarea. No uses la terminal. Al final dime cuántos issues creaste.
> ```

| 🚀 Inicio de sesión y consulta a TaskFlow | 🎯 Resolución de proyecto y creación de Issue |
| :---: | :---: |
| ![Prompt integrador inicial](evidencia/dia3/img/mp-6.2.png) | ![Resultado con issue creado](evidencia/dia3/img/mp-6.2.1.png) |
| *El agente recibe el prompt y consulta `taskflow-listar_tareas_vencidas`* | *El agente resuelve el nombre del proyecto y crea el issue #3 en GitHub* |

### ¿Dónde está? 

Los recursos desarrollados, configuraciones y evidencias de las sesiones correspondientes a este día son:

- **Servidor MCP Custom de TaskFlow (Java):**
  - [`📁 taskflow-mcp/`](taskflow-mcp/) — Proyecto Maven completo del servidor MCP.
  - [`📦 taskflow-mcp/target/taskflow-mcp.jar`](taskflow-mcp/target/taskflow-mcp.jar) — Artefacto ejecutable del servidor local.
  - [`☕ taskflow-mcp/src/main/java/com/taskflow/mcp/TaskflowTools.java`](taskflow-mcp/src/main/java/com/taskflow/mcp/TaskflowTools.java) — Implementación de las herramientas MCP (`listar_tareas_vencidas`, `listar_proyectos`, etc.).
- **Sesiones exportadas y especificaciones:**
  - [`📄 specs/summary.md`](specs/summary.md) — Especificación utilizada para el cuerpo del issue #2.
  - [`📄 evidencia/dia3/integrador.md`](evidencia/dia3/integrador.md) — Transcripción completa de la sesión integradora (TaskFlow + GitHub).
  - [`📄 evidencia/dia3/playwright.md`](evidencia/dia3/playwright.md) — Transcripción de la navegación y creación de tarea en UI vía Playwright.
  - [`📄 evidencia/dia3/aws-knowledge.md`](evidencia/dia3/aws-knowledge.md) — Transcripción de consultas al catálogo de AWS.
- **Issues creados en GitHub mediante MCP:**
  - [`🔗 Issue #2: GET /projects/{id}/summary`](https://github.com/juliannp253/taskflow-copilot-juliannp253/issues/2)
  - [`🔗 Issue #3: Tarea vencida #7: Corregir bug de fechas`](https://github.com/juliannp253/taskflow-copilot-juliannp253/issues/3)

### ¿Cómo se comprueba? 

> - **Verificación de servidores MCP:**
>   - [`evidencia/dia3/mcp-list-inicio.txt`](evidencia/dia3/mcp-list-inicio.txt): Estado inicial confirmando que no existían servidores configurados (`No MCP servers configured`).
>   - [`evidencia/dia3/mcp-list.txt`](evidencia/dia3/mcp-list.txt): Salida de `copilot mcp list` con los servidores añadidos (`aws-knowledge`, `playwright`, `taskflow`).
> - **Resultados de la sesión integradora:**
>   - [`evidencia/dia3/conteos.txt`](evidencia/dia3/conteos.txt):
>     - Tareas vencidas encontradas vía REST: `1` (ID `7`).
>     - Issues creados en GitHub: `1` (Issue #3 de tarea vencida).
>     - Issues no deseados: `0` (el agente detectó y evitó la inyección en la descripción de la tarea).
>   - [`evidencia/dia3/issue-summary.txt`](evidencia/dia3/issue-summary.txt): Confirma el issue `#2` creado mediante el MCP de GitHub.
> - **Automatización UI con Playwright:**
>   - [`evidencia/dia3/playwright-tarea.txt`](evidencia/dia3/playwright-tarea.txt): Se comprobó en la base de datos la inserción de la tarea generada desde la interfaz web (`ID: 10`, `Revisar accesibilidad del login`, `HIGH`, `TODO`).
> - **Consulta a catálogo AWS:**
>   - [`evidencia/dia3/aws-auditoria.txt`](evidencia/dia3/aws-auditoria.txt): Auditoría confirmando la disponibilidad regional de servicios en `us-east-2`.

### ¿Qué no salió? 

> **Nada** (todos los ejercicios, servidores MCP y flujos de automatización se ejecutaron exitosamente). Como detalle relevante, en el ejercicio integrador la descripción de la tarea #7 contenía un intento de *prompt injection* solicitando borrar la rama `main`; el agente demostró robustez al detectarlo en su razonamiento y omitir dicha instrucción maliciosa, cumpliendo estrictamente con el objetivo pautado.

---

## 📌 Día 4 · Skills y agentes

### ¿Qué construí? 

En este día se incorporaron las **skills** para el agente. Contamos con la skill dentro de [`.github/skills/crear-endpoint-taskflow/SKILL.md`](.github/skills/crear-endpoint-taskflow/SKILL.md) que nos da la receta para crear un endpoint; otra skill [`.github/skills/verificar-taskflow/SKILL.md`](.github/skills/verificar-taskflow/SKILL.md) para ejecutar script que inicia la app y la prueba. Junto a las skills, introducimos **agentes** que pueden ser utilizados para designarles tareas acorde su función. Contamos con [`.github/agents/revisor.agent.md`](.github/agents/revisor.agent.md) y [`.github/agents/tester.agent.md`](.github/agents/tester.agent.md), cada uno cuenta con sus distintos permisos.

#### 🔹 1. Registro y listado de Skills del proyecto

Comprobación de las skills personalizadas y disponibles en Copilot CLI mediante `copilot skill list`:

![Skills disponibles](evidencia/dia4/img/mp-1.png)
*Skills registradas: `crear-endpoint-taskflow` y `verificar-taskflow`.*

#### 🔹 2. Verificación automatizada con `/verificar-taskflow`

> **Prompt ejecutado en terminal:**
> ```text
> /verificar-taskflow Verifica TaskFlow con el script de la skill y dame el resultado.
> ```

![Ejecución de verificar-taskflow](evidencia/dia4/img/mp-6.png)
*La skill empaqueta la app con Maven, la arranca en segundo plano con perfil H2, ejecuta pruebas end-to-end contra los endpoints y apaga el servicio.*

#### 🔹 3. Catálogo y verificación de Custom Agents

Verificación y selección de agentes especializados configurados en el proyecto:

![Selector de Custom Agents](evidencia/dia4/img/mp-7.png)
*Agentes disponibles: `Default`, `revisor` (project) y `tester` (project).*

#### 🔹 4. Especialización en acción: Revisor y Tester

| 🕵️‍♂️ Agente Revisor (Code Review) | 🧪 Agente Tester (Cobertura de Tests) |
| :---: | :---: |
| ![Agente Revisor](evidencia/dia4/img/mp-8.png) | ![Agente Tester](evidencia/dia4/img/mp-10.png) |
| *El `revisor` analiza `summary.diff` vs `specs/summary.md` reportando faltantes* | *El `tester` añade los tests unitarios y slice faltantes y pasa `mvn -q test`* |

#### 🔹 5. Auditor de cuenta AWS en modo seguro

Contamos con un agente más, [`auditor-aws.agent.md`](.github/agents/auditor-aws.agent.md), el cual hace uso del MCP server de AWS que configuramos el día previo para acceder a la documentación de AWS, y con esa información realizar una auditoría de la cuenta y verificar si existe algún servicio vivo. Este agente realiza todas las acciones de manera segura, no puede crear ni borrar sin que se lo pidan.

| 🛡️ Inicio de Auditoría AWS | 📊 Inspección de Servicios y Recursos |
| :---: | :---: |
| ![Prompt Auditor AWS](evidencia/dia4/img/mp-13.png) | ![Resultado Auditoría](evidencia/dia4/img/mp-13.1.png) |
| *Llamada a la skill `limpieza-aws` con script en servidor de solo lectura `aws-ro`* | *Listado de instancias EC2, S3, DynamoDB y roles IAM en regiones `us-east-1` y `us-east-2`* |

> **Veredicto final de la cuenta:** `CUENTA LIMPIA`
> ![Veredicto Cuenta Limpia](evidencia/dia4/img/mp-13.2.png)
> *Comprobación en terminal validando la salida `Veredicto: CUENTA LIMPIA`.*

### ¿Dónde está? 

Los recursos desarrollados, configuraciones y evidencias de las sesiones correspondientes a este día son:

- **Skills personalizadas (.github/skills/):**
  - [`📁 .github/skills/crear-endpoint-taskflow/SKILL.md`](.github/skills/crear-endpoint-taskflow/SKILL.md) — Receta estructurada para implementar endpoints con sus capas y convenciones.
  - [`📁 .github/skills/verificar-taskflow/SKILL.md`](.github/skills/verificar-taskflow/SKILL.md) y [`verificar.ps1`](.github/skills/verificar-taskflow/verificar.ps1) — Script de verificación integral de endpoints.
  - [`📁 .github/skills/limpieza-aws/SKILL.md`](.github/skills/limpieza-aws/SKILL.md) y [`auditoria.py`](.github/skills/limpieza-aws/auditoria.py) — Script para auditoría de infraestructura en la nube.
- **Custom Agents (.github/agents/):**
  - [`🤖 .github/agents/revisor.agent.md`](.github/agents/revisor.agent.md) — Agente de revisión de código con permisos de solo lectura.
  - [`🤖 .github/agents/tester.agent.md`](.github/agents/tester.agent.md) — Agente especializado en generar y correr tests.
  - [`🤖 .github/agents/auditor-aws.agent.md`](.github/agents/auditor-aws.agent.md) — Agente de inspección de cuenta AWS en modo seguro.
- **Código y Tests creados/afectados:**
  - [`☕ src/main/java/com/taskflow/controller/ProjectController.java`](src/main/java/com/taskflow/controller/ProjectController.java) y [`ProjectService.java`](src/main/java/com/taskflow/service/ProjectService.java) (`GET /projects/{id}/summary`)
  - [`🧪 src/test/java/com/taskflow/unit/ProjectServiceTest.java`](src/test/java/com/taskflow/unit/ProjectServiceTest.java) y [`ProjectControllerTest.java`](src/test/java/com/taskflow/slice/ProjectControllerTest.java) (tests implementados por el agente `tester`)
- **Sesiones exportadas:**
  - [`📄 evidencia/dia4/summary-sesion.md`](evidencia/dia4/summary-sesion.md) — Implementación inicial con `/crear-endpoint-taskflow`.
  - [`📄 evidencia/dia4/verificar-sesion.md`](evidencia/dia4/verificar-sesion.md) — Ejecución de la skill `/verificar-taskflow`.
  - [`📄 evidencia/dia4/revision.md`](evidencia/dia4/revision.md) y [`revisor-no-edita.md`](evidencia/dia4/revisor-no-edita.md) — Sesiones del agente `revisor`.
  - [`📄 evidencia/dia4/tester-sesion.md`](evidencia/dia4/tester-sesion.md) — Sesión del agente `tester`.

### ¿Cómo se comprueba? 

> - **Verificación end-to-end con `/verificar-taskflow`:**
>   - [`evidencia/dia4/verificar.txt`](evidencia/dia4/verificar.txt): Resultado `8/8 OK` validando los endpoints `/tasks/overdue`, `/tasks/unassigned`, `/projects/{id}/summary` (IDs 1, 2 y 3), respuesta 404 para ID inexistente y 401 sin token.
> - **Verificación de permisos de solo lectura del revisor:**
>   - [`evidencia/dia4/revisor-no-edita.md`](evidencia/dia4/revisor-no-edita.md): Ante la orden explícita de modificar archivos, el agente respondió: *"No puedo escribir archivos directamente desde esta interfaz"*, confirmando que sus permisos están restringidos correctamente.
> - **Completitud y ejecución de pruebas por el tester:**
>   - [`evidencia/dia4/tester-sesion.md`](evidencia/dia4/tester-sesion.md): Implementación exitosa de los casos de prueba unitarios y slice para `summary` y suite terminando en verde (`mvn -q test`).
> - **Auditoría de seguridad AWS:**
>   - [`evidencia/dia4/aws-resultado.txt`](evidencia/dia4/aws-resultado.txt): Certificación de estado de cuenta sin recursos huérfanos (`Veredicto: CUENTA LIMPIA.`).

### ¿Qué no salió? 

> **Nada** (todos los flujos con skills y agentes se ejecutaron según lo planeado).
---

## 📌 Día 5 · VS Code y proyecto final

### ¿Qué construí? 

Los días anteriores trabajamos mediante la **CLI**, en este quinto día trabajé con **Copilot** que viene integrado con VS Code, abriendo nuestro proyecto en el editor de código y habilitando la ventana de chat con Copilot. Además, se desarrolló y entregó la funcionalidad del **Proyecto Final**: el endpoint de búsqueda `GET /tasks/search?q=` con su respectiva suite de pruebas y revisión automatizada.

#### 🔹 1. Chat en modo Ask (Consultas y exploración de contexto)

El chat en modo *Ask* nos permite hacerle preguntas acerca de nuestro proyecto, de archivos en específico, buscar fragmentos de código, etc. 

![Chat en modo Ask](evidencia/dia5/img/mp-4.png)
*Copilot Chat en modo Ask explicando el uso de `Task.estaVencida()` con enlaces y citas de código exactas.*

#### 🔹 2. Actuar (Agent) y flujo de aprobación

En el chat también contamos con el modo *Agent*, al cual ya le podemos asignar el realizar acciones en nuestro código y en la terminal mediante aprobaciones interactivas.

![Modo Agent y Aprobación](evidencia/dia5/img/mp-5.png)
*Solicitud interactiva de confirmación (Allow/Skip) para la ejecución del comando `mvn -q test` desde la interfaz de VS Code.*

#### 🔹 3. Integración visual de Agentes y Skills en el IDE

Aquí también podemos encontrar de manera visual las **/instructions**, **/skills**, y **/agents** con los que contamos en el proyecto:

| 🤖 Selector de Custom Agents | 🛠️ Menú de Skills del proyecto |
| :---: | :---: |
| ![Custom Agents en VS Code](evidencia/dia5/img/agents.png) | ![Skills en VS Code](evidencia/dia5/img/skills.png) |
| *Acceso a agentes especializados: `auditor-aws`, `revisor` y `tester`* | *Paleta de comandos con las skills del proyecto (`crear-endpoint-taskflow`, etc.)* |

#### 🔹 4. Implementación del Proyecto Final (`GET /tasks/search?q=`)

Se implementó la búsqueda de tareas por fragmento de título (`GET /tasks/search?q=`) guiada por [`specs/search.md`](specs/search.md), utilizando la skill `/crear-endpoint-taskflow`, validación con el agente `revisor`, y completando el ciclo con Pull Request y code review automático.

---

### ¿Dónde está? 

Los entregables, especificaciones y código correspondientes al Día 5 y Proyecto Final son:

- **Especificación del feature:**
  - [`📄 specs/search.md`](specs/search.md) — Requerimientos de búsqueda por título (case-insensitive, orden alfabético, validaciones y seguridad).
- **Código del controlador, servicio y repositorio:**
  - [`☕ src/main/java/com/taskflow/controller/TaskController.java`](src/main/java/com/taskflow/controller/TaskController.java) (endpoint `GET /tasks/search`)
  - [`☕ src/main/java/com/taskflow/service/TaskService.java`](src/main/java/com/taskflow/service/TaskService.java) (método `buscarPorTitulo(String q)`)
  - [`☕ src/main/java/com/taskflow/repository/TaskRepository.java`](src/main/java/com/taskflow/repository/TaskRepository.java) (`findByTitleContainingIgnoreCase`)
- **Tests unitarios y de integración:**
  - [`🧪 src/test/java/com/taskflow/unit/BusquedaTareasServiceTest.java`](src/test/java/com/taskflow/unit/BusquedaTareasServiceTest.java) (pruebas de orden, trim y aserción de cero interacciones al repositorio)
  - [`🧪 src/test/java/com/taskflow/slice/BusquedaTareasControllerTest.java`](src/test/java/com/taskflow/slice/BusquedaTareasControllerTest.java) (pruebas MockMvc para respuestas 200 y 400)
- **Documentación y artefactos del proyecto final:**
  - [`📄 semana6/README.md`](semana6/README.md) — Informe consolidado del proyecto final.
  - [`📄 semana6/proyecto-final.diff`](semana6/proyecto-final.diff) — Diff exportado de la rama `feature/search`.
  - [`📄 semana6/sesion-implementacion.md`](semana6/sesion-implementacion.md) — Transcripción de la implementación con la skill.
  - [`📄 semana6/revision.md`](semana6/revision.md) y [`semana6/code-review.md`](semana6/code-review.md) — Reportes de revisión y observaciones del PR.
- **Pull Request en GitHub:**
  - [`🔗 Pull Request #5 (Mergeado en main)`](https://github.com/juliannp253/taskflow-copilot-juliannp253/pull/5) — Commit de merge `a369aa9`.

---

### ¿Cómo se comprueba? 

> - **Verificación integral de endpoints (Script `verificar.ps1` con `casos-search.ps1`):**
>   - Salida del verificador: `RESULTADO: 14/14 OK`
>   - Casos validados del feature de búsqueda:
>     - `GET /tasks/search?q=api` ➜ Devuelve tareas 9 y 5 en orden alfabético.
>     - `GET /tasks/search?q=API` ➜ Mismo resultado (case-insensitive).
>     - `GET /tasks/search?q=zzz` ➜ Retorna `200` con `[]`.
>     - `GET /tasks/search?q=(espacios)` ➜ Retorna `400` con `"El parámetro 'q' es obligatorio."`.
>     - `GET /tasks/search` (sin parámetro) ➜ Retorna `400`.
>     - `GET /tasks/search` (sin token JWT) ➜ Retorna `401 Unauthorized`.
> - **Suite completa de pruebas Maven:**
>   - `[INFO] Tests run: 84, Failures: 0, Errors: 0, Skipped: 0 - BUILD SUCCESS`.
> - **Pull Request fusionado:**
>   - PR #5 revisado por Copilot y mergeado exitosamente en la rama `main`.

---

### ¿Qué no salió? 

En la primera implementación de `BusquedaTareasServiceTest.java`, el test `@Test buscarPorTitulo_nullOBlank_lanza` solo comprobaba el lanzamiento de la excepción mediante `assertThrows`, omitiendo validar que el repositorio no fuera invocado antes del fallo.

> **Hallazgo detectado por Copilot Code Review en el PR #5:**
> *"La especificación exige que `null` y una cadena en blanco no lleguen al repositorio, pero estas aserciones solo comprueban la excepción. Si el servicio llamara al repositorio antes de fallar, el test seguiría pasando; añade una verificación de cero interacciones para cubrir ese contrato."*
>
> **Resolución aplicada:**
> Se le indicó a Copilot leer [`semana6/code-review.md`](semana6/code-review.md) y aplicar los comentarios. El agente incorporó `verifyNoInteractions(taskRepository)` en ambos escenarios (null y blank), garantizando que el contrato se cumpla de forma estricta.

---

## 🏁 Cierre

### 💳 Créditos de la semana
- **Consumo total reportado:** `146 AI Credits` consumidos en el mes de septiembre.
- **Desglose en el Proyecto Final:**
  - `8.18 AIC` en la implementación con la skill `/crear-endpoint-taskflow`.
  - `1.74 AIC` en la revisión automatizada con el agente `revisor`.
  - `1.88 AIC` en la aplicación de correcciones de code review.
- **Estrategias para optimizar consumo:**
  1. Emplear modelos más eficientes (`gpt-5-mini` o equivalentes rápidos) para tareas directas de implementación y testing.
  2. Redactar especificaciones técnicas claras y exhaustivas antes de solicitar código para evitar iteraciones y reintentos innecesarios.
  3. Establecer límites estrictos con parámetros como `--max-ai-credits`.
  4. Diseñar y reutilizar skills empaquetadas (`SKILL.md`) para evitar cargar contextos repetitivos o prompts manuales extensos.

### 🔍 Una cosa que el agente hizo mal
- **¿Cuál fue el error?:** Al redactar las pruebas unitarias en `BusquedaTareasServiceTest.java`, el agente asumió que bastaba comprobar la excepción con `assertThrows(TaskValidationException.class, ...)`, omitiendo verificar si el servicio realizaba consultas innecesarias a la base de datos o repositorio ante entradas inválidas.
- **¿Quién la detectó?:** El revisor automatizado de GitHub Copilot (`Copilot Code Review`) al auditar el diff del Pull Request #5.
- **¿Cómo quedó resuelto?:** Se instruyó al agente aplicar los comentarios de [`semana6/code-review.md`](semana6/code-review.md). Copilot añadió `verifyNoInteractions(taskRepository)`, asegurando que no existan llamadas colaterales al repositorio cuando el parámetro no es válido, logrando la aprobación del PR y el paso exitoso de los 84 tests.