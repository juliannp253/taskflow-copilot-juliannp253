# Evidencia de la semana · GitHub Copilot
Alumno: `Julian Padron Nuñez` · Repo: `https://github.com/juliannp253/taskflow-copilot-juliannp253`

## Día 1 · La CLI
- **Qué construí:** 

Con las prácticas llevadas a cabo esta semana empecé a familiarizarme con el uso de Copilot mediante la CLI y ver de lo que es capaz de hacer al abrir el agente dentro de un proyecto.

Al abrir el agente dentro de uno de nuestros proyectos, este es capaz de leer, buscar, editar, o eliminar archivos (según nuestros permisos) para poder contestar a preguntas que le hagamos o realizar tareas que le designemos dentro del proyecto.

![¿Qué hay en este repo?](evidencia/dia1/img/pregunta1.png)
![¿Existe un endpoint que no existe?](evidencia/dia1/img/pregunta3.png)

En cuestión de comandos, un agente es capaz de ejecutar los comandos en la terminal siempre que se lo autoricemos. Para la ejecucuión de un comando, Copilot siempre nos pide una autorización en la cual podemos aprobar una vez, aprobar siempre, o denegar la ejecución de dicho comando.

**Prompt**: Corre mvn -q test y dime solo si terminó bien o con error.
![aprobación de comando](evidencia/dia1/img/mp-8.png)


- **Dónde está:** `.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`

Aquí podemos encontrar el resultado de la ejecución del siguiente prompt:
```text
Escribe el archivo docs/ARQUITECTURA.md para un desarrollador que llega nuevo a TaskFlow. Explica: las capas y paquetes; el recorrido completo de POST /projects/{projectId}/tasks desde el controlador hasta la base de datos; dónde viven las reglas de negocio; cómo funciona la seguridad con JWT; y cómo están organizados los tests. Escribe entre backticks cada clase del proyecto (por ejemplo `TaskService`) y cada archivo con su ruta desde la raíz del repositorio (por ejemplo `src/main/java/com/taskflow/model/Task.java`). No modifiques ningún otro archivo.
```

- **Cómo se comprueba:** `evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
- **Qué no salió:** Nada

## Día 2 · Especificar, implementar y revisar
- **Qué construí:** una frase, con tus palabras.
- **Dónde está:** `.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
- **Cómo se comprueba:** `evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
- **Qué no salió:** el error tal cual y qué intentaste. Si todo salió, escribe «nada».
## Día 3 · MCP
- **Qué construí:** una frase, con tus palabras.
- **Dónde está:** `.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
- **Cómo se comprueba:** `evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
- **Qué no salió:** el error tal cual y qué intentaste. Si todo salió, escribe «nada».
## Día 4 · Skills y agentes
- **Qué construí:** una frase, con tus palabras.
- **Dónde está:** `.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
- **Cómo se comprueba:** `evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
- **Qué no salió:** el error tal cual y qué intentaste. Si todo salió, escribe «nada».
## Día 5 · VS Code y proyecto final
- **Qué construí:** una frase, con tus palabras.
- **Dónde está:** `.github/copilot-instructions.md`, `docs/ARQUITECTURA.md`
- **Cómo se comprueba:** `evidencia/dia1/verificador.txt`, última línea `0 NO EXISTE`
- **Qué no salió:** el error tal cual y qué intentaste. Si todo salió, escribe «nada».

## Cierre
- **Créditos:** cuántos gastaste y qué harías distinto para gastar menos.
- **Una cosa que el agente hizo mal:** cuál, quién la detectó y cómo quedó.