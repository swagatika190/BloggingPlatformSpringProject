package com.example.demo.daoInterface;

import java.util.List;

import com.example.demo.entity.Post;

public interface PostDao {
	Post savePost(Post post, int userId);
	Post updatePost(Post post, int id);
	Post fetchPost(int id);
	List<Post> fetchAllPosts();
	List<Post> fetchPostsByUserId(int id);
	Post deletePost(int id) ;
}
