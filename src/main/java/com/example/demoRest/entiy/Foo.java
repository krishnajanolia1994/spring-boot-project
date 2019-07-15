package com.example.demoRest.entiy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "FOO")
public class Foo
{
	@Id	
	private long id;
	private String name;
	public long getId() {
		return id;
	}
	
	


	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
      
}
