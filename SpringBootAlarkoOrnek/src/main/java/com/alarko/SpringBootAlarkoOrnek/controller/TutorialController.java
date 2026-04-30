package com.alarko.SpringBootAlarkoOrnek.controller;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alarko.SpringBootAlarkoOrnek.entity.Tutorial;
import com.alarko.SpringBootAlarkoOrnek.repository.TutorialRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api") //http://localhost:8080/api/tutorials
@CrossOrigin
@Tag(name = "Tutorial", description = "Tutorial Controller açıklaması")
public class TutorialController {
	
	@Autowired
	TutorialRepository tutorialRepository;
	
	@Operation(summary="Tutorial List", description="Tutorial Listesi Döndürür")
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//bu metoda dışarıdan bir json veri göndericeğiz gönderdiğimiz veri Tutorial nesnesine dönüştürülecek
	//ve datajpa kullanılarak veritabanına kayıt yapılacak
	//eklenen kayıt yine json olarak bu metodu kim çağırıyorsa ona geri dönecek
	@PostMapping("/tutorials")
	@Operation(summary="Tutorial Create", description="Yeni Bir Tutorial Ekler..")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			//insert into Tutorial (title,description) values(tutorial.getTitle(),tutorial.getDescription);
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//http://85.159.71.66:8080/api/tutorials/1
    @GetMapping("/tutorials/{id}")
    @Operation(summary="Tutorial Detail", description="Tutorial Detay Getirir")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {

        Optional<Tutorial> tutorialDetail = tutorialRepository.findById(id);

        if (tutorialDetail.isPresent()) {
            return new ResponseEntity<>(tutorialDetail.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	

}
