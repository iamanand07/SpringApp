package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepo;
	

	@GetMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Course> getAllCourses() throws JsonProcessingException {
		return courseRepo.findAll();
	}
	
	@GetMapping(value = "/course/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Course getCourseById(@PathVariable("id") Long id) throws JsonProcessingException{
		log.info("fetching course with id - {}", id);
		return courseRepo.findById(id).orElse(null);
	}
	
	@PostMapping(value = "/course")
	public Course addCourse(@RequestBody Course course) {
		return courseRepo.save(course);
	}
	
	@PutMapping(value = "/course/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Course updateCourse(@PathVariable("id") Long id,@RequestBody Course course) {
		
		Course existingCourse = courseRepo.findById(id).get();
		existingCourse.setName(course.getName());
		
		return courseRepo.save(existingCourse);
				
	}
	
	@DeleteMapping(value = "/course/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String ,String> removeCourse(@PathVariable("id") Long id) throws JsonProcessingException {
		log.info("removing course with id - {}", id);
		courseRepo.deleteById(id);
		return Map.of("status", "success");
	}

}
