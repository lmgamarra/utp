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
import com.prueba.utp.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.map(user -> ResponseEntity.ok().body(user)).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setUsername(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			user.setEmail(userDetails.getEmail());
			userRepository.save(user);
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();			
			userRepository.delete(user);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

}
