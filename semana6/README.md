# Proyecto final · Semana 6 · GitHub Copilot

**Alumno:** `Julian Padron Nuñez` · **Usuario de GitHub:** `juliannp253`

## 1. Qué construí


| | Feature | Especificación |
|---|---|---|
| [x] | `GET /tasks/search?q=` — buscar tareas por título | [`specs/search.md`](../specs/search.md) |

## 2. El pull request

- **URL del PR (mergeado):** `https://github.com/juliannp253/taskflow-copilot-juliannp253/pull/5`
- **Commit del merge en `main`:** `a369aa9 (HEAD -> main, origin/main, origin/HEAD) Merge pull request #5 from juliannp253/feature/search`
- **Comentarios de Copilot code review:** `2`

## 3. Cómo lo hice

> Una fila por paso. La columna «Evidencia» apunta a un archivo de esta carpeta o a un comando que
> cualquiera puede repetir.

| Paso | Qué hice | Evidencia |
|---|---|---|
| Rama y spec | `git switch -c feature/<feature>` y copié la spec a `specs/` | `git log --oneline main..feature/search` (antes del merge) |
| Implementación | `copilot -p "/crear-endpoint-taskflow …"` con `gpt-5-mini` | `semana6/sesion-implementacion.md` (tiene la línea `Skill "crear-endpoint-taskflow" loaded successfully`) |
| Revisión | agente `revisor` sobre `semana6/proyecto-final.diff` | `semana6/revision.md` (termina con `Veredicto:`) |
| Tests | `mvn test` en verde | `Tests run: 84, Failures: 0, Errors: 0, Skipped: 0` |
| Comprobación REST | `verificar.ps1` con `casos-search.ps1` | sección 5 de este documento |
| Code review | Copilot en el PR | la pestaña *Files changed* del PR |

## 4. Qué hizo el agente y qué corregí yo

| # | Qué hizo mal el agente (archivo) | Quién lo detectó | Cómo quedó corregido |
|---|---|---|---|
| 1 | `En @Test buscarPorTitulo_nullOBlank_lanza, la especificación exige que null y una cadena en blanco no lleguen al repositorio, pero estas aserciones solo comprueban la excepción. Si el servicio llamara al repositorio antes de fallar, el test seguiría pasando.` | `Copilot reviewer` | `"Lee semana6/code-review.md y aplica esos comentarios de code review. Puedes cambiar los tests que agregaste en esta rama (git diff main); no modifiques los tests que ya estaban en main. Al terminar corre mvn -q test."` |


## 5. Comprobaciones REST

```text
Repositorio: /home/juliannp253/taskflow-copilot-juliannp253
URL de la app: http://127.0.0.1:8080
Empaquetando con Maven (mvn -q package -DskipTests), tarda unos segundos...
App arrancando (PID 15946). Esperando a que /info responda...
App lista en 10 s.
[OK]    GET /tasks/overdue devuelve solo la tarea 7
[OK]    GET /tasks/unassigned devuelve las tareas 4 y 6
[OK]    GET /projects/1/summary
[OK]    GET /projects/2/summary
[OK]    GET /projects/3/summary
[OK]    GET /projects/99/summary responde 404
[OK]    GET /projects/1/summary sin token responde 401
[OK]    GET /tasks/search?q=api devuelve 9 y 5, en ese orden
[OK]    GET /tasks/search?q=API devuelve lo mismo (no distingue mayúsculas)
[OK]    GET /tasks/search?q=zzz devuelve 200 con []
[OK]    GET /tasks/search?q=(espacios) responde 400 con el mensaje de la spec
[OK]    GET /tasks/search sin q responde 400 con el mensaje de la spec
[OK]    GET /tasks/search sin token responde 401
App detenida (PID 15946).
[OK]    App apagada: el puerto 8080 ya no responde
RESULTADO: 14/14 OK
```

## 6. Créditos de la semana

| Qué | AI credits |
|---|---|
| Usados en septiembre según github.com (incluye semanas anteriores si usaste Copilot antes) | `146` |
| Implementación con la skill (`AI Credits` del PF-2) | `8.18` |
| Revisión del `revisor` (`AI Credits` del PF-3) | `1.74` |
| Correcciones del PF-4 y del PF-6, si hubo (`AI Credits`) | `1.88` |
