package com.example.demo.serviceInterface;

import java.util.List;

import com.example.demo.entity.Post;
import com.example.demo.entity.ResponseStructure;

public interface PostService {
	ResponseStructure<Post> savePost(Post post, int userId);
	ResponseStructure<Post> updatePost(Post post, int id);
	ResponseStructure<Post> fetchPost(int id);
	 ResponseStructure<List<Post>> fetchAllPosts() ;
	 ResponseStructure<List<Post>> fetchPostsByUserId(int id) ;
	 ResponseStructure<Post> deletePost(int id);

}
