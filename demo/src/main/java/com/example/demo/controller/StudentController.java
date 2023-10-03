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

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepo;

	@GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getAllStudents() throws JsonProcessingException {
		return studentRepo.findAll();
	}

	@GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudentByID(@PathVariable("id") Long id) throws JsonProcessingException {
		log.info("fetching student with id - {}", id);
		return studentRepo.findById(id).orElse(null);
	}

	@PostMapping(value = "/students")
	public Student addStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}

	@PutMapping(value = "/students/{id}")
	public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
		Student existingStudent = studentRepo.findById(id).get();

		existingStudent.setName(student.getName());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setCourses(student.getCourses());
		return studentRepo.save(existingStudent);
	}

	@DeleteMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> removeStudentByID(@PathVariable("id") Long id) throws JsonProcessingException {
		log.info("removing student with id - {}", id);
		studentRepo.deleteById(id);
		return Map.of("status", "success");
	}

	@GetMapping("/hello")
	public String getHello() {
		return "Hello";
	}

}
