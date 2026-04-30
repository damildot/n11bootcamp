package com.alarko.SpringBootAlarkoOrnek.service;

import java.util.List;

import com.alarko.SpringBootAlarkoOrnek.entity.Project;



public interface ProjectService {
	
	List<Project> getAll();
	
	Project getById(Long id);
	
	Project save(Project project);
	
	Project update(Project project);
	
	Boolean delete(Long id);

}