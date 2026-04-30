package com.alarko.SpringBootAlarkoOrnek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alarko.SpringBootAlarkoOrnek.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
