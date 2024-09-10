package com.prueba.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.utp.model.User;

public interface UserRepository extends JpaRepository<User,Long> {	
	
}
