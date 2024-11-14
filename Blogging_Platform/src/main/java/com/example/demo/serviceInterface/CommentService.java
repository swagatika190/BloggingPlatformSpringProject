package com.example.demo.serviceInterface;

import java.util.List;

import com.example.demo.entity.Comment;
import com.example.demo.entity.ResponseStructure;

public interface CommentService {
	ResponseStructure<Comment> saveComment(Comment com, int userId, int postId);
	ResponseStructure<Comment> updateComment(Comment com, int id);
	 ResponseStructure<Comment> fetchComment(int id);
	 ResponseStructure<List<Comment>> fetchCommentsForPost(int postId);
	 ResponseStructure<Comment> deleteComment(int id);
	 ResponseStructure<String> deleteCommentsForPost(int postId);

}
