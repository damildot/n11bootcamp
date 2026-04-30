package com.alarko.SpringBootAlarkoOrnek.service.impl;

import com.alarko.SpringBootAlarkoOrnek.entity.Project;
import com.alarko.SpringBootAlarkoOrnek.repository.ProjectRepository;
import com.alarko.SpringBootAlarkoOrnek.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {

    private ProjectRepository projectRepository;
    private ProjectServiceImpl projectServiceImpl;

    @BeforeEach
    void setUp() {
        projectRepository = Mockito.mock(ProjectRepository.class);
        projectServiceImpl = new ProjectServiceImpl(projectRepository);
    }

    @Test
    void shouldGetAllProjects() {
        Project p1 = new Project();
        p1.setId(1L);
        p1.setProjectName("Project 1");
        p1.setProjectCode("PRJ001");

        Project p2 = new Project();
        p2.setId(2L);
        p2.setProjectName("Project 2");
        p2.setProjectCode("PRJ002");

        when(projectRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Project> result = projectServiceImpl.getAll();

        assertThat(result).hasSize(2);
        verify(projectRepository).findAll();
    }

    @Test
    void shouldGetProjectById() {
        Project p = new Project();
        p.setId(1L);
        p.setProjectName("Project 1");
        p.setProjectCode("PRJ001");

        when(projectRepository.getReferenceById(1L)).thenReturn(p);

        Project result = projectServiceImpl.getById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        verify(projectRepository).getReferenceById(1L);
    }

    @Test
    void shouldSaveProject() {
        Project p = new Project();
        p.setProjectName("Project Save");
        p.setProjectCode("SAVE001");

        Project saved = new Project();
        saved.setId(1L);
        saved.setProjectName("Project Save");
        saved.setProjectCode("SAVE001");

        when(projectRepository.save(p)).thenReturn(saved);

        Project result = projectServiceImpl.save(p);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getProjectCode()).isEqualTo("SAVE001");
        verify(projectRepository).save(p);
    }

    @Test
    void shouldThrowExceptionWhenProjectCodeIsNull() {
        Project p = new Project();
        p.setProjectName("Invalid Project");

        assertThatThrownBy(() -> projectServiceImpl.save(p))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("hatalı kayıt");

        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void shouldUpdateProject() {
        Project existing = new Project();
        existing.setId(1L);
        existing.setProjectName("Old Name");
        existing.setProjectCode("OLD001");

        Project update = new Project();
        update.setId(1L);
        update.setProjectName("New Name");
        update.setProjectCode("NEW001");

        when(projectRepository.getOne(1L)).thenReturn(existing);
        when(projectRepository.save(existing)).thenReturn(existing);

        Project result = projectServiceImpl.update(update);

        assertThat(result.getProjectName()).isEqualTo("New Name");
        assertThat(result.getProjectCode()).isEqualTo("NEW001");
        verify(projectRepository).getOne(1L);
        verify(projectRepository).save(existing);
    }

    @Test
    void shouldDeleteProject() {
        Boolean result = projectServiceImpl.delete(1L);

        assertThat(result).isTrue();
        verify(projectRepository).deleteById(1L);
    }
}