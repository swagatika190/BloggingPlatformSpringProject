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

import com.example.demo.entity.Post;
import com.example.demo.entity.ResponseStructure;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	public PostService service;

	@PostMapping("/save/{id}")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestBody Post post, @PathVariable("id") int userId) {
		ResponseStructure<Post> structure = service.savePost(post, userId);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Post>> updatePost(@RequestBody Post post, @PathVariable("id") int id) {
		ResponseStructure<Post> structure = service.updatePost(post, id);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseStructure<Post>> fetchPost(@PathVariable("id") int id) {
		ResponseStructure<Post> post = service.fetchPost(id);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<List<Post>>> fetchAllPosts() {
		ResponseStructure<List<Post>> structure = service.fetchAllPosts();
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@GetMapping("/fetchByUser/{id}")
	public ResponseEntity<ResponseStructure<List<Post>>> fetchPostsByUserId(@PathVariable("id") int id) {
		ResponseStructure<List<Post>> structure = service.fetchPostsByUserId(id);
		return new ResponseEntity<ResponseStructure<List<Post>>>(structure, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@PathVariable("id") int id) {
		ResponseStructure<Post> structure = service.deletePost(id);
		return new ResponseEntity<ResponseStructure<Post>>(structure, HttpStatus.OK);
	}
}


