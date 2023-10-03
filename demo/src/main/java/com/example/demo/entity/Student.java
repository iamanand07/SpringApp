package com.example.demo.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Student {
	
	@Id
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String phoneNumber;

	@ManyToMany
	private List<Course> courses;
	

}
