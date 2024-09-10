package com.prueba.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.utp.model.Grade;

public interface GradeRepository extends JpaRepository<Grade,Long> {	
	
}
