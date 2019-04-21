package com.example.demoRest.entiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Component
@Entity
@Table(name = "PARENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Parent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@JsonProperty
	@Column(name = "ID1")
	private Long id1;
	
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
	
	@OneToMany(mappedBy="emp")
	private List<Children> childrens= new ArrayList<>();

	public Long getId() {
		return id1;
	}

	public void setId(Long id) {
		this.id1 = id;
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
		this.salary = salary ;
		System.out.println("krishna");
	}



}
