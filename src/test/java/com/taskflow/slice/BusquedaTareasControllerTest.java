package com.taskflow.slice;

import com.taskflow.controller.TaskController;
import com.taskflow.dto.TaskResponse;
import com.taskflow.model.Task;
import com.taskflow.model.TaskStatus;
import com.taskflow.security.JwtAuthenticationFilter;
import com.taskflow.service.ProjectService;
import com.taskflow.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc(addFilters = false)
class BusquedaTareasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @MockitoBean
    private ProjectService projectService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getSearch_withQuery_returns200AndJson() throws Exception {
        TaskResponse r1 = new TaskResponse(9L, "Documentar la API con Swagger", "d", TaskStatus.TODO, null, 1L, null, LocalDate.now());
        TaskResponse r2 = new TaskResponse(5L, "Optimizar consultas de la API", "d", TaskStatus.TODO, null, 1L, null, LocalDate.now());
        when(taskService.buscarPorTitulo("api")).thenReturn(List.of(
                // el servicio devuelve entidades; el controller las mapea a DTOs en la respuesta
                new com.taskflow.model.Task(9L, "Documentar la API con Swagger", "d", TaskStatus.TODO, com.taskflow.model.Priority.MED, 1L, null, LocalDate.now()),
                new com.taskflow.model.Task(5L, "Optimizar consultas de la API", "d", TaskStatus.TODO, com.taskflow.model.Priority.MED, 1L, null, LocalDate.now())
        ));

        mockMvc.perform(get("/tasks/search?q=api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(9))
                .andExpect(jsonPath("$[0].title").value("Documentar la API con Swagger"))
                .andExpect(jsonPath("$[1].id").value(5))
                .andExpect(jsonPath("$[1].title").value("Optimizar consultas de la API"));
    }

    @Test
    void getSearch_missingQ_returns400WithMessage() throws Exception {
        when(taskService.buscarPorTitulo(null)).thenThrow(new com.taskflow.exception.TaskValidationException("El parámetro 'q' es obligatorio."));

        mockMvc.perform(get("/tasks/search"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El parámetro 'q' es obligatorio."));
    }
}
