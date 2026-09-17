package com.taskflow.unit;

import com.taskflow.dto.ProjectSummaryResponse;
import com.taskflow.exception.TaskValidationException;
import com.taskflow.model.Priority;
import com.taskflow.model.Project;
import com.taskflow.model.Task;
import com.taskflow.model.TaskStatus;
import com.taskflow.repository.ProjectRepository;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectSummaryServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService service;

    private final Project proyecto = new Project(2L, "App Móvil", "d", 1L, null);

    private Task tarea(Long id, TaskStatus status, LocalDate dueDate) throws TaskValidationException {
        return new Task(id, "Tarea " + id, "d", status, Priority.MED, 2L, 1L, dueDate);
    }

    @Test
    void resumen_cuentaEstadosYVencidas() throws TaskValidationException {
        when(taskRepository.findByProjectId(2L)).thenReturn(List.of(
                tarea(1L, TaskStatus.TODO, null),
                tarea(2L, TaskStatus.TODO, null),
                tarea(3L, TaskStatus.TODO, null),
                tarea(4L, TaskStatus.IN_PROGRESS, null),
                tarea(5L, TaskStatus.DONE, null),
                tarea(6L, TaskStatus.IN_PROGRESS, LocalDate.now().minusDays(2)) // vencida
        ));

        ProjectSummaryResponse res = service.resumen(proyecto);

        assertEquals(6, res.totalTasks());
        assertEquals(3L, res.byStatus().get("TODO").longValue());
        assertEquals(2L, res.byStatus().get("IN_PROGRESS").longValue());
        assertEquals(1L, res.byStatus().get("DONE").longValue());
        assertEquals(1, res.overdue());
    }

    @Test
    void resumen_proyectoSinTareas_devuelveCeros() {
        when(taskRepository.findByProjectId(3L)).thenReturn(List.of());
        Project proyecto3 = new Project(3L, "Vacio", "d", 1L, null);

        ProjectSummaryResponse res = service.resumen(proyecto3);

        assertEquals(0, res.totalTasks());
        assertEquals(0L, res.byStatus().get("TODO").longValue());
        assertEquals(0L, res.byStatus().get("IN_PROGRESS").longValue());
        assertEquals(0L, res.byStatus().get("DONE").longValue());
        assertEquals(0, res.overdue());
    }
}
