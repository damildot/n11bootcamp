package com.alarko.SpringBootAlarkoOrnek.controller;

import com.alarko.SpringBootAlarkoOrnek.entity.Project;
import com.alarko.SpringBootAlarkoOrnek.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectServiceImpl projectServiceImpl;

    @Test
    void shouldReturnProjectsPage() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Test Project");
        project.setProjectCode("PRJ001");

        Mockito.when(projectServiceImpl.getAll())
                .thenReturn(Collections.singletonList(project));

        mockMvc.perform(get("/allprojects"))
                .andExpect(status().isOk())
                .andExpect(view().name("projects"))
                .andExpect(model().attributeExists("projeListesi"))
                .andExpect(model().attribute("projeListesi", hasSize(1)));

        verify(projectServiceImpl).getAll();
    }
}