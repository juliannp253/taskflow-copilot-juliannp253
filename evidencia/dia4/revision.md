# Copilot CLI Session

> [!NOTE]
> - **Session ID:** `f3099d82-344c-44cc-9f5b-e03a266a038b`  
> - **Started:** 17/9/2026, 2:07:45 p.m.  
> - **Duration:** 55s  
> - **Exported:** 17/9/2026, 2:08:41 p.m.  

---

<sub>5s</sub>

### User

Revisa evidencia/dia4/summary.diff contra la especificación specs/summary.md.

---

<sub>12s</sub>

### Copilot

Leyendo en paralelo el diff (evidence/dia4/summary.diff) y la especificación (specs/summary.md) para compararlos conforme a la checklist del revisor. Ejecutando lecturas.

---

<sub>12s</sub>

### `view` — Failed

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidence/dia4/summary.diff.**

/home/juliannp253/taskflow-copilot-juliannp253/evidence/dia4/summary.diff

```
Path does not exist
```


---

<sub>12s</sub>

### `view`

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/specs/summary.md.**

/home/juliannp253/taskflow-copilot-juliannp253/specs/summary.md

<details>
<summary>52 lines</summary>

````
## Qué se pide

Un endpoint nuevo, `GET /projects/{id}/summary`, que resume un proyecto: **cuántas tareas tiene en
cada estado y cuántas están vencidas**. Sirve para pintar un tablero sin descargar todas las tareas del
proyecto. Pide el token de `POST /auth/login`, como todo lo que no es público.

## Respuesta 200

```json
{
  "projectId": 2,
  "projectName": "App Móvil",
  "totalTasks": 4,
  "byStatus": { "TODO": 1, "IN_PROGRESS": 2, "DONE": 1 },
  "overdue": 1
}
```

| Campo | Tipo | Qué es |
|---|---|---|
| `projectId` | número | el `id` del proyecto pedido |
| `projectName` | texto | el `name` del proyecto |
| `totalTasks` | número | cuántas tareas tiene el proyecto |
| `byStatus` | objeto | una clave por cada valor de `TaskStatus` (`TODO`, `IN_PROGRESS`, `DONE`), **siempre las tres**, aunque valgan 0 |
| `overdue` | número | cuántas tareas del proyecto cumplen `Task.estaVencida()` |

## Reglas

1. **Vencida** es exactamente lo que ya dice `Task.estaVencida()`: tiene fecha límite, la fecha ya pasó y
   el estado no es `DONE`. Se reutiliza ese método; no se reescribe la regla en el servicio.
2. Un proyecto **sin tareas** responde 200 con `totalTasks` 0, las tres claves de `byStatus` en 0 y `overdue` 0.
3. Un proyecto **que no existe** responde **404** con el `ErrorResponse` uniforme de siempre
   (`ProjectNotFoundException`, igual que `GET /projects/{id}`).
4. **Sin token** responde **401**, como todo lo que no es público.
5. Cualquier usuario autenticado puede pedir el resumen de cualquier proyecto (igual que `GET /projects/{id}/tasks`).

## Resultado esperado con la semilla del perfil `h2`

| Petición | `totalTasks` | `byStatus` | `overdue` |
|---|---|---|---|
| `GET /projects/1/summary` | 5 | `TODO` 3 · `IN_PROGRESS` 1 · `DONE` 1 | 0 (la tarea 2 tiene fecha pasada, pero está `DONE`) |
| `GET /projects/2/summary` | 4 | `TODO` 1 · `IN_PROGRESS` 2 · `DONE` 1 | 1 (la tarea 7) |
| `GET /projects/3/summary` | 0 | `TODO` 0 · `IN_PROGRESS` 0 · `DONE` 0 | 0 |
| `GET /projects/99/summary` | — | — | 404 |

Si antes de probar creaste tareas en un proyecto (por la UI o con el servidor MCP), `totalTasks` y
`TODO` de ese proyecto suben en la misma cantidad. Al reiniciar la app con el perfil `h2` vuelven a
ser los de la tabla.

## Cómo se construye (convenciones de TaskFlow)

- DTO de salida: un `record` en `com.taskflow.dto`, `ProjectSummaryResponse`.
- La lógica de conteo va en el servicio (`ProjectService`), no en el controller.
- El controller solo traduce HTTP: busca el proyecto (404 si no existe) y delega.
- Tests:
  - **unit** del servicio con Mockito: un proyecto con tareas en los tres estados y una vencida; un proyecto sin tareas.
  - **slice** `@WebMvcTest` del controller: 200 con el JSON de arriba y 404 con un proyecto inexistente.
- `mvn -q test` en verde al terminar, sin modificar tests existentes.

## Criterios de aceptación

- [ ] `GET /projects/2/summary` con el token de `ana` devuelve el JSON del ejemplo.
- [ ] `GET /projects/3/summary` devuelve ceros en los cinco números.
- [ ] `GET /projects/99/summary` devuelve 404.
- [ ] `mvn -q test` en verde: los tests de antes más los nuevos.
````

</details>


---

<sub>20s</sub>

### Copilot

Leeré ambos archivos necesarios: el diff en evidencia/dia4/summary.diff y la especificación specs/summary.md para comparar alcance, contrato, reglas y tests. Ejecutando lecturas en paralelo.

---

<sub>20s</sub>

### `view`

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff.**

/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff

<details>
<summary>5 lines</summary>

```diff

diff --git a/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff b/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff
index 0000000..0000000 100644
--- a/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff
+++ b/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff
@@ -1,0 +1,0 @@
```

</details>


---

<sub>30s</sub>

### Copilot

Abriendo el diff en evidencia/dia4/summary.diff para listar los archivos y líneas modificadas; luego abriré los archivos afectados para comparar con la especificación. Ejecutando lectura del diff.

---

<sub>30s</sub>

### `view`

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff.**

/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff

<details>
<summary>5 lines</summary>

```diff

diff --git a/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff b/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff
index 0000000..0000000 100644
--- a/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff
+++ b/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff
@@ -1,0 +1,0 @@
```

</details>


---

<sub>37s</sub>

### Copilot

Listing both possible directories evidence/ and evidencia/ in parallel to find the diff file.

---

<sub>37s</sub>

### `view` — Failed

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidence.**

/home/juliannp253/taskflow-copilot-juliannp253/evidence

```
Path does not exist
```


---

<sub>37s</sub>

### `view`

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidencia.**

/home/juliannp253/taskflow-copilot-juliannp253/evidencia

```
dia1
dia2
dia3
dia4
```


---

<sub>40s</sub>

### `view`

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4.**

/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4 (lines 1--1)

```
summary-sesion.md
summary.diff
verificar-sesion.md
verificar.txt
```


---

<sub>42s</sub>

### `view` — Failed

**view the file at /home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff.**

/home/juliannp253/taskflow-copilot-juliannp253/evidencia/dia4/summary.diff (lines 1--1)

```
view_range out of bounds
```


---

<sub>55s</sub>

### Copilot

| # | Gravedad | Archivo:línea | Punto de la checklist | Qué pasa | Qué harías |
|---|---|---|---|---|---|
| 1 | Bloquea | evidencia/dia4/summary.diff (vacío) | 1 Alcance / 4 Contrato / 5 Convenciones / 7 Tests existentes | El diff está vacío: no hay archivos modificados para revisar frente a specs/summary.md. No aparece la implementación del endpoint ni los tests que la especificación exige. | Incluir en el diff los cambios: DTO record `ProjectSummaryResponse`, método en `ProjectService` que reutilice `Task.estaVencida()`, mapper, `ProjectController` con ruta GET /projects/{id}/summary (delegando al service), unit test del service (Mockito) y @WebMvcTest del controller; ejecutar `mvn -q test`. |

Casos sin test: no hay diff; por tanto faltan ambos tests solicitados (service unit y controller slice).

Veredicto: CAMBIOS PEDIDOS

Nota: Se leyó specs/summary.md. No fue posible abrir archivos de código porque el diff aportado no contiene cambios.

---

<sub>Generated by [GitHub Copilot CLI](https://github.com/features/copilot/cli)</sub>
