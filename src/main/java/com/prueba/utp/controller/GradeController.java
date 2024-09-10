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

import com.prueba.utp.model.Grade;
import com.prueba.utp.repository.GradeRepository;

@RestController
@RequestMapping("api/grade")
public class GradeController {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@GetMapping
	public List<Grade> getAllGrades() {
		return gradeRepository.findAll();
	}
	
	@PostMapping
	public Grade createGrade(@RequestBody Grade grade) {
		return gradeRepository.save(grade);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Grade> getGraderById(@PathVariable Long id) {
		Optional<Grade> gradeOptional = gradeRepository.findById(id);
		return gradeOptional.map(grade -> ResponseEntity.ok().body(grade)).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade gradeDetails) {
		Optional<Grade> gradeOptional = gradeRepository.findById(id);
		if (gradeOptional.isPresent()) {
			Grade grade = gradeOptional.get();
			grade.setNote(gradeDetails.getNote());					
			gradeRepository.save(grade);
			return ResponseEntity.ok().body(grade);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Grade> deleteGrade(@PathVariable Long id) {
		Optional<Grade> gradeOptional = gradeRepository.findById(id);
		if (gradeOptional.isPresent()) {
			Grade course = gradeOptional.get();			
			gradeRepository.delete(course);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

}
