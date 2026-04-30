package com.alarko.SpringBootAlarkoOrnek.repository;

import com.alarko.SpringBootAlarkoOrnek.entity.Tutorial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TutorialRepositoryTest {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Test
    void shouldSaveAndFindTutorial() {
        Tutorial tutorial = new Tutorial("Spring Boot", "Spring Boot Description");

        Tutorial saved = tutorialRepository.save(tutorial);

        assertThat(saved.getId()).isNotNull();
        assertThat(tutorialRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldFindByTitleContaining() {
        tutorialRepository.save(new Tutorial("Spring Boot", "Description 1"));
        tutorialRepository.save(new Tutorial("Java Core", "Description 2"));

        List<Tutorial> result = tutorialRepository.findByTitleContaining("Spring");

        assertThat(result).isNotEmpty();
    }

    @Test
    void shouldDeleteTutorial() {
        Tutorial saved = tutorialRepository.save(new Tutorial("Delete Me", "Description"));

        tutorialRepository.deleteById(saved.getId());

        assertThat(tutorialRepository.findById(saved.getId())).isNotPresent();
    }
}