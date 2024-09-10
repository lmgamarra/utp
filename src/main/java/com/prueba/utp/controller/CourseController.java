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

import com.prueba.utp.model.Course;
import com.prueba.utp.repository.CourseRepository;

@RestController
@RequestMapping("api/course")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	@PostMapping
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getStudentrById(@PathVariable Long id) {
		Optional<Course> courseOptional = courseRepository.findById(id);
		return courseOptional.map(course -> ResponseEntity.ok().body(course)).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateStudent(@PathVariable Long id, @RequestBody Course courseDetails) {
		Optional<Course> courseOptional = courseRepository.findById(id);
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();
			course.setName(courseDetails.getName());					
			courseRepository.save(course);
			return ResponseEntity.ok().body(course);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteStudent(@PathVariable Long id) {
		Optional<Course> courseOptional = courseRepository.findById(id);
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();			
			courseRepository.delete(course);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

}
