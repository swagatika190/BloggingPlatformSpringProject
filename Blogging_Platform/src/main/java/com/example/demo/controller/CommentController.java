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

import com.example.demo.entity.Comment;
import com.example.demo.entity.ResponseStructure;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService service;

	@PostMapping("/save/{userID}/{postId}")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestBody Comment com,
			@PathVariable("userID") int userId, @PathVariable("postId") int postId) {

		ResponseStructure<Comment> structure = service.saveComment(com, userId, postId);
		return new ResponseEntity<>(structure, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Comment>> updateComment(@RequestBody Comment com,
			@PathVariable("id") int id) {
		ResponseStructure<Comment> structure = service.updateComment(com, id);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseStructure<Comment>> fetchComment(@PathVariable("id") int id) {
		ResponseStructure<Comment> structure = service.fetchComment(id);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@GetMapping("/fetchForPost/{postId}")
	public ResponseEntity<ResponseStructure<List<Comment>>> fetchCommentsForPost(@PathVariable("postId") int postId) {
		ResponseStructure<List<Comment>> structure = service.fetchCommentsForPost(postId);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(@PathVariable("id") int id) {
		ResponseStructure<Comment> structure = service.deleteComment(id);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll/{postID}")
	public ResponseEntity<ResponseStructure<String>> deleteCommentsForPost(@PathVariable("postID") int postId) {
		ResponseStructure<String> structure = service.deleteCommentsForPost(postId);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

}
