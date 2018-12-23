package com.example.demoRest.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Component
@NamedQuery(name="findByName1",query="select u from Employee u where name=?1")
@Entity
@Table(name = "EMPLOYEE")
@JsonPropertyOrder({ "id", "name", "surName", "employer", "manager", "salary" })

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty
	@Column(name = "ID")
	private Long id;
	
	@JsonProperty
	@Column(name = "NAME")
	private String name;
	
	@JsonProperty
	@Column(name = "SUR_NAME")
	private String surName;
	
	@JsonProperty
	@Column(name = "EMPLOYER")
	private String employer;
	
	@JsonProperty
	@Column(name = "MANAGER")
	private String manager;
	
	@JsonProperty
	@Column(name = "SALARY")
	private Long salary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary  = salary ;
		this.salary = salary ;
		System.out.println("krishna");
	}



}
