package com.prueba.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.utp.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {	
	
}
