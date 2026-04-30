package com.alarko.SpringBootAlarkoOrnek.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Entity
@Table(name="Project")

@Schema(name = "Product", description = "Product model")
//Burada @Schema annotation'ı ile ProductModel'in Swagger UI
//üzerinde nasıl gösterileceğini belirtiyoruz.
//Bu annotation ile ProductModel'in Swagger UI 
//üzerindeki adını ve açıklamasını belirtebiliyoruz.
public class Project implements Serializable {
	
    @Schema(name = "id", description = "Project id", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_name")
    @Schema(name = "projectName", description = "Project name", example = "Project 1")
	private String projectName;
	
	
	@Column(name="project_code")
    @Schema(name = "projectCode", description = "Product code", example = "Project 1")
	private String projectCode;
	
	
	
	@Column(name="insert_date")
	@Temporal(TemporalType.TIMESTAMP)
    @Schema(name = "insertDate", description = "Insert name", example = "01.01.2024")
	private Date insertDate;


	public Project()
	{
		
	}

	public Project(Long id, String projectName, String projectCode, Date insertDate) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.projectCode = projectCode;
		this.insertDate = insertDate;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
}
