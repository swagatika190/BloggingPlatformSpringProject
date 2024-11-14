package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PostDao;
import com.example.demo.entity.Post;
import com.example.demo.entity.ResponseStructure;

@Service
public class PostService {
	@Autowired
	private PostDao dao;

	public ResponseStructure<Post> savePost(Post post, int userId) {
		post.setCreationTime(LocalDateTime.now());
		Post post2 = dao.savePost(post, userId);

		return new ResponseStructure<Post>(HttpStatus.OK.value(), "Post saved Succesfully", post2, LocalDateTime.now());
	}

	public ResponseStructure<Post> updatePost(Post post, int id) {
		Post post2 = dao.updatePost(post, id);
		return new ResponseStructure<Post>(HttpStatus.OK.value(), "POst updated succesfully", post2,
				LocalDateTime.now());
	}

	public ResponseStructure<Post> fetchPost(int id) {
		Post post = dao.fetchPost(id);
		return new ResponseStructure<Post>(HttpStatus.OK.value(), "Post retrived succesfully", post,
				LocalDateTime.now());
	}

	public ResponseStructure<List<Post>> fetchAllPosts() {
		List<Post> list = dao.fetchAllPosts();
		list.sort((p1, p2) -> p1.getCreationTime().compareTo(p2.getCreationTime()));
		return new ResponseStructure<List<Post>>(HttpStatus.OK.value(), "Posts retrived successfully", list,
				LocalDateTime.now());
	}

	public ResponseStructure<List<Post>> fetchPostsByUserId(int id) {
		List<Post> list = dao.fetchPostsByUserId(id);
		list.sort((p1, p2) -> p1.getCreationTime().compareTo(p2.getCreationTime()));
		return new ResponseStructure<List<Post>>(HttpStatus.OK.value(),
				"Posts for the User " + id + " retrived succesfully", list, LocalDateTime.now());
	}

	public ResponseStructure<Post> deletePost(int id) {
		Post post = dao.deletePost(id);
		return new ResponseStructure<Post>(HttpStatus.OK.value(), "Post deleted Succesfully", post,
				LocalDateTime.now());
	}

}
