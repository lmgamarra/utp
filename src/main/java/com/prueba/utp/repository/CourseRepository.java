package com.prueba.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.utp.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {	
	
}
