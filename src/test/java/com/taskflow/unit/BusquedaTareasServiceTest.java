package com.taskflow.unit;

import com.taskflow.exception.TaskValidationException;
import com.taskflow.model.Priority;
import com.taskflow.model.Task;
import com.taskflow.model.TaskStatus;
import com.taskflow.repository.TaskRepository;
import com.taskflow.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusquedaTareasServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService service;

    private Task tarea(Long id, Long assigneeId) throws TaskValidationException {
        return new Task(id, "Title " + id, "d", TaskStatus.TODO, Priority.MED, 1L, assigneeId, LocalDate.now());
    }

    @Test
    void buscarPorTitulo_ordenaYTrimpeaYverifica() throws Exception {
        // repositorio devuelve en orden no alfabético
        Task t1 = new Task(5L, "zeta api task", "d", TaskStatus.TODO, Priority.MED, 1L, null, LocalDate.now());
        Task t2 = new Task(9L, "API doc task", "d", TaskStatus.TODO, Priority.MED, 1L, null, LocalDate.now());
        when(taskRepository.findByTitleContainingIgnoreCase("api")).thenReturn(List.of(t1, t2));

        List<Task> resultados = service.buscarPorTitulo("  api ");

        // deben regresar ordenados por título (case-insensitive): API doc task, zeta api task
        assertEquals(2, resultados.size());
        assertEquals(9L, resultados.get(0).getId());
        assertEquals(5L, resultados.get(1).getId());

        verify(taskRepository).findByTitleContainingIgnoreCase("api");
    }

    @Test
    void buscarPorTitulo_nullOBlank_lanza() {
        assertThrows(TaskValidationException.class, () -> service.buscarPorTitulo(null));
        assertThrows(TaskValidationException.class, () -> service.buscarPorTitulo("   "));

        // el servicio no debe delegar al repositorio cuando la entrada es inválida
        verifyNoInteractions(taskRepository);
    }
}
