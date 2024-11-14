package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repositries.PostRepositry;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class PostDao {
	@Autowired
	private PostRepositry repositry;

	@Autowired
	private UserDao user_dao;

	public Post savePost(Post post, int userId) {
		User user = user_dao.fetchUser(userId);
		post.setUser(user);
		return repositry.save(post);
	}

	public Post updatePost(Post post, int id) {
		boolean existsById = repositry.existsById(id);
		if (!existsById) {
			throw new EntityNotFoundException("Post with the id " + id + " is not present");
		}
		post.setId(id);

		return repositry.save(post);

	}

	public Post fetchPost(int id) {
		Optional<Post> optional = repositry.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Post with the id " + id + " is not present");
		}
		return optional.get();
	}

	public List<Post> fetchAllPosts() {
		List<Post> list = repositry.findAll();
		if (list.isEmpty()) {
			throw new EntityNotFoundException("No posts data available in the DataBase");
		}
		return list;
	}

	public List<Post> fetchPostsByUserId(int id) {
		User user = user_dao.fetchUser(id);

		return user.getPost();
	}

	public Post deletePost(int id) {
		Post post = fetchPost(id);

		repositry.delete(post);
		return post;

	}
}



