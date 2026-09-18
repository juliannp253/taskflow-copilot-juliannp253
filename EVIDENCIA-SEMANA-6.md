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
Este día el agente empezó a escribir código, en específico dos endpoints nuevos para la API de TaskFlow, **GET /tasks/overdue** y **GET /tasks/unassigned**, con sus tests correspondientes. Para llevar a cabo estas implementaciones, proporcioné especifícaciones de cómo debería de implementar las nuevas funcionalidades (**specs/overdue.md** y **specs/unassigned.md**), el agente las lee y ahora restringimos a que siga nuestro procedimiento y evitamos que tome sus propias decisiones

> **Prompt ejecutado en terminal:**
> ```bash
> Implementa la especificación de specs/overdue.md al pie de la letra. Cuando termines, corre mvn -q test y confirma que pasa.
> ```

![text](evidencia/dia2/img/mp-2.1.png) ![text](evidencia/dia2/img/mp-2.2.png) ![text](evidencia/dia2/img/mp-2.png)

> **Prompt ejecutado en terminal:**
> ```bash
> /plan Implementa la especificación de specs/unassigned.md al pie de la letra. Cuando termines, corre mvn -q test y confirma que pasa.
> ```

![alt text](evidencia/dia2/img/mp-6.1.png) ![alt text](evidencia/dia2/img/mp-6.png)

### ¿Dónde está? 
`.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`

### ¿Cómo se comprueba? 
`evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`

### ¿Qué no salió? 
Se realizó un primer intento de implementación de **specs/unassigned.md** en donde al pedirle al agente que nos generé un **/plan** previo a implementar el código, sugerí los siguientes cambios en el plan:

```text
El plan no dice qué casos usa el test unit de orden. La spec los pide: el repositorio devuelve, en este orden, una sin responsable con fecha en 10 días, una con responsable, una sin responsable sin fecha y una sin responsable con fecha en 2 días; sinResponsable() devuelve las tres sin responsable en el orden 2 días, 10 días, sin fecha, comparando los ids en orden. Agrégalo al plan.
```

---

## Día 3 · MCP

### ¿Qué construí? 
una frase, con tus palabras.
### ¿Dónde está? 
`.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
### ¿Cómo se comprueba? 
`evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
### ¿Qué no salió? 
el error tal cual y qué intentaste. Si todo salió, escribe «nada».

---

## Día 4 · Skills y agentes

### ¿Qué construí? 
una frase, con tus palabras.
### ¿Dónde está? 
`.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
### ¿Cómo se comprueba? 
`evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
### ¿Qué no salió? 
el error tal cual y qué intentaste. Si todo salió, escribe «nada».

---

## Día 5 · VS Code y proyecto final

### Qué construí? 
una frase, con tus palabras.
### ¿Dónde está? 
`.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
### ¿Cómo se comprueba? 
`evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
### ¿Qué no salió? 
el error tal cual y qué intentaste. Si todo salió, escribe «nada».

---

## Cierre
- **Créditos:** cuántos gastaste y qué harías distinto para gastar menos.
- **Una cosa que el agente hizo mal:** cuál, quién la detectó y cómo quedó.