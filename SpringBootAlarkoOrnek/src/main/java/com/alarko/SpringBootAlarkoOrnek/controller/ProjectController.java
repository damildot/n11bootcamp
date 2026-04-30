package com.alarko.SpringBootAlarkoOrnek.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alarko.SpringBootAlarkoOrnek.entity.Project;
import com.alarko.SpringBootAlarkoOrnek.service.impl.ProjectServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Project", description = "Project Controller açıklaması")
public class ProjectController {
	
	
	private final ProjectServiceImpl projectServiceImpl;

	public ProjectController(ProjectServiceImpl projectServiceImpl) {
		this.projectServiceImpl = projectServiceImpl;
	}

	
	@RequestMapping(value = "/allprojects", method = RequestMethod.GET)
	@Operation(summary="Project List", description="Project Listesi Döndürür")
	public ModelAndView getAllProjects() {
		List<Project> liste =projectServiceImpl.getAll();
		ModelAndView model = new ModelAndView("projects");
		model.addObject("projeListesi", liste);
		return model;
	}

}
