package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ResponseStructure;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		ResponseStructure<User> structure = service.saveUser(user);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user, @PathVariable("id") int id) {
		ResponseStructure<User> structure = service.updateUser(user, id);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<User>> fetchUser(@PathVariable("id") int id) {
		ResponseStructure<User> structure = service.fetchUser(id);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAllUsers() {
		ResponseStructure<List<User>> fetchAllUsers = service.fetchAllUsers();
		return new ResponseEntity<ResponseStructure<List<User>>>(fetchAllUsers, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable("id") int id) {
		ResponseStructure<User> structure = service.deleteUser(id);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}

}
