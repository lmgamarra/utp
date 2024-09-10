package com.prueba.utp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.utp.model.Student;
import com.prueba.utp.repository.StudentRepository;

@RestController
@RequestMapping("api/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentrById(@PathVariable Long id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		return studentOptional.map(student -> ResponseEntity.ok().body(student)).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();
			student.setName(studentDetails.getName());
			student.setLastname(studentDetails.getLastname());			
			studentRepository.save(student);
			return ResponseEntity.ok().body(student);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();			
			studentRepository.delete(student);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

}
