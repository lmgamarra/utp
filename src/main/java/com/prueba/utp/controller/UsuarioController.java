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

import com.prueba.utp.model.User;
import com.prueba.utp.repository.UsuarioRepository;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<User> getAllUsers() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	public User createUser(@RequestBody User usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> usuarioOptional = usuarioRepository.findById(id);
		return usuarioOptional.map(usuario -> ResponseEntity.ok().body(usuario)).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User usuarioDetalle) {
		Optional<User> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			User usuario = usuarioOptional.get();
			usuario.setUsername(usuarioDetalle.getUsername());
			usuario.setEmail(usuarioDetalle.getEmail());
			usuarioRepository.save(usuario);
			return ResponseEntity.ok().body(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		Optional<User> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			User usuario = usuarioOptional.get();			
			usuarioRepository.delete(usuario);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

}
