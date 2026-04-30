package com.alarko.SpringBootAlarkoOrnek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alarko.SpringBootAlarkoOrnek.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial,Long> {

	List<Tutorial> findByTitleContaining(String title); 
}
