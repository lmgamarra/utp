package com.prueba.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.utp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

	
	
}
