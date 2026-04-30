package com.alarko.SpringBootAlarkoOrnek.repository;

import com.alarko.SpringBootAlarkoOrnek.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void shouldSaveAndFindProject() {
        Project p = new Project();
        p.setProjectName("Test Project");
        p.setProjectCode("PRJ001");
        p.setInsertDate(new Date());

        Project saved = projectRepository.save(p);

        assertThat(saved.getId()).isNotNull();
        assertThat(projectRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldDeleteProject() {
        Project p = new Project();
        p.setProjectName("Delete Project");
        p.setProjectCode("DEL001");
        p.setInsertDate(new Date());

        Project saved = projectRepository.save(p);

        projectRepository.deleteById(saved.getId());

        assertThat(projectRepository.findById(saved.getId())).isNotPresent();
    }
}