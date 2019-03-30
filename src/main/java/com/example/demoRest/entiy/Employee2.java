package com.example.demoRest.entiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Component
@NamedQuery(name="findByName2",query="select u from Employee2 u where name=?1")
@Entity
@Table(name = "EMPLOYEE2")
@JsonPropertyOrder({ "id", "name", "surName", "employer", "manager", "salary" })

public class Employee2 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty
	@Column(name = "ID")
	private Long id;
	
	@JsonProperty
	@Column(name = "NAME")
	private String name;
	
	@OneToOne
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Children> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Children> childrens) {
		this.childrens = childrens;
	}

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
		this.salary = salary ;
		System.out.println("krishna");
	}



}
