package com.taskflow.unit;

import com.taskflow.dto.ProjectSummaryResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void resumen_conTareasYVencida_devuelveResumen() throws Exception {
        Project proyecto = new Project(2L, "App Móvil", "d", 2L, null);

        // Tareas: 1 TODO overdue, 2 IN_PROGRESS, 1 DONE (past date but DONE)
        Task t1 = new Task(5L, "T-5", "d", TaskStatus.DONE, Priority.MED, 2L, 1L, LocalDate.now().minusDays(5));
        Task t2 = new Task(6L, "T-6", "d", TaskStatus.IN_PROGRESS, Priority.MED, 2L, 1L, LocalDate.now().plusDays(5));
        Task t3 = new Task(7L, "T-7", "d", TaskStatus.IN_PROGRESS, Priority.MED, 2L, 1L, null);
        Task t4 = new Task(8L, "T-8", "d", TaskStatus.TODO, Priority.MED, 2L, 1L, LocalDate.now().minusDays(1));

        List<Task> tareas = List.of(t1, t2, t3, t4);
        when(taskRepository.findByProjectId(2L)).thenReturn(tareas);

        ProjectSummaryResponse resumen = projectService.resumen(proyecto);

        assertEquals(Long.valueOf(2L), resumen.projectId());
        assertEquals("App Móvil", resumen.projectName());
        assertEquals(4L, resumen.totalTasks());
        assertEquals(1L, resumen.byStatus().get("TODO").longValue());
        assertEquals(2L, resumen.byStatus().get("IN_PROGRESS").longValue());
        assertEquals(1L, resumen.byStatus().get("DONE").longValue());
        assertEquals(1L, resumen.overdue());
    }

    @Test
    void resumen_sinTareas_devuelveCeros() throws Exception {
        Project proyecto = new Project(3L, "Legacy", "d", 1L, null);
        when(taskRepository.findByProjectId(3L)).thenReturn(List.of());

        ProjectSummaryResponse resumen = projectService.resumen(proyecto);

        assertEquals(Long.valueOf(3L), resumen.projectId());
        assertEquals("Legacy", resumen.projectName());
        assertEquals(0L, resumen.totalTasks());
        assertEquals(0L, resumen.byStatus().get("TODO").longValue());
        assertEquals(0L, resumen.byStatus().get("IN_PROGRESS").longValue());
        assertEquals(0L, resumen.byStatus().get("DONE").longValue());
        assertEquals(0L, resumen.overdue());
    }
}
