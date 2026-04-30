package com.alarko.SpringBootAlarkoOrnek.controller;

import com.alarko.SpringBootAlarkoOrnek.entity.Tutorial;
import com.alarko.SpringBootAlarkoOrnek.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TutorialController.class)
public class TutorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TutorialRepository tutorialRepository;

    @Test
    void shouldGetAllTutorials() throws Exception {
        Tutorial t1 = new Tutorial("Java", "Java Description");
        Tutorial t2 = new Tutorial("Spring", "Spring Description");

        Mockito.when(tutorialRepository.findAll())
                .thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/api/tutorials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(tutorialRepository).findAll();
    }

    @Test
    void shouldReturnNoContentWhenTutorialListIsEmpty() throws Exception {
        Mockito.when(tutorialRepository.findAll())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/tutorials"))
                .andExpect(status().isNoContent());

        verify(tutorialRepository).findAll();
    }

    @Test
    void shouldGetTutorialsByTitle() throws Exception {
        Tutorial tutorial = new Tutorial("Spring Boot", "Test Description");

        Mockito.when(tutorialRepository.findByTitleContaining("Spring"))
                .thenReturn(Collections.singletonList(tutorial));

        mockMvc.perform(get("/api/tutorials").param("title", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Spring Boot"));

        verify(tutorialRepository).findByTitleContaining("Spring");
    }

    @Test
    void shouldCreateTutorial() throws Exception {
        Tutorial saved = new Tutorial("New Tutorial", "New Description");

        Mockito.when(tutorialRepository.save(any(Tutorial.class)))
                .thenReturn(saved);

        mockMvc.perform(post("/api/tutorials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Tutorial\",\"description\":\"New Description\",\"published\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Tutorial"))
                .andExpect(jsonPath("$.description").value("New Description"));

        verify(tutorialRepository).save(any(Tutorial.class));
    }

    @Test
    void shouldGetTutorialById() throws Exception {
        Tutorial tutorial = new Tutorial("Detail Tutorial", "Detail Description");

        Mockito.when(tutorialRepository.findById(1L))
                .thenReturn(Optional.of(tutorial));

        mockMvc.perform(get("/api/tutorials/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Detail Tutorial"))
                .andExpect(jsonPath("$.description").value("Detail Description"));

        verify(tutorialRepository).findById(1L);
    }

    @Test
    void shouldReturnNotFoundWhenTutorialDoesNotExist() throws Exception {
        Mockito.when(tutorialRepository.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tutorials/1"))
                .andExpect(status().isNotFound());

        verify(tutorialRepository).findById(1L);
    }

    @Test
    void shouldUpdateTutorial() throws Exception {
        Tutorial existing = new Tutorial("Old Title", "Old Description");
        Tutorial updated = new Tutorial("Updated Title", "Updated Description");

        Mockito.when(tutorialRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        Mockito.when(tutorialRepository.save(any(Tutorial.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/tutorials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"description\":\"Updated Description\",\"published\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        verify(tutorialRepository).findById(1L);
        verify(tutorialRepository).save(any(Tutorial.class));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingMissingTutorial() throws Exception {
        Mockito.when(tutorialRepository.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/tutorials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"description\":\"Updated Description\",\"published\":true}"))
                .andExpect(status().isNotFound());

        verify(tutorialRepository).findById(1L);
    }

    @Test
    void shouldDeleteTutorial() throws Exception {
        mockMvc.perform(delete("/api/tutorials/1"))
                .andExpect(status().isNoContent());

        verify(tutorialRepository).deleteById(1L);
    }
}