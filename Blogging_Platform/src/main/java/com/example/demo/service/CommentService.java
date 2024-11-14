package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentDao;
import com.example.demo.entity.Comment;
import com.example.demo.entity.ResponseStructure;

@Service
public class CommentService {
	@Autowired
	private CommentDao dao;

	public ResponseStructure<Comment> saveComment(Comment com, int userId, int postId) {
		com.setCreationTime(LocalDateTime.now());
		Comment comment = dao.saveComment(com, userId, postId);
		
		return new ResponseStructure<Comment>(HttpStatus.OK.value(), "Comment saved succesfully", comment, LocalDateTime.now());
	}

	public ResponseStructure<Comment> updateComment(Comment com, int id) {
		Comment comment = dao.updateComment(com, id);
		return new ResponseStructure<Comment>(HttpStatus.OK.value(), "Comment updated succesfully", comment, LocalDateTime.now());
	}

	public ResponseStructure<Comment> fetchComment(int id) {
		Comment comment = dao.fetchComment(id);
		return new ResponseStructure<Comment>(HttpStatus.OK.value(), "Comment retrived succesfully", comment, LocalDateTime.now());
	}

	public ResponseStructure<List<Comment>> fetchCommentsForPost(int postId) {
		List<Comment> list = dao.fetchAllCommentsForPost(postId);
		list.sort((c1,c2)->c1.getCreationTime().compareTo(c2.getCreationTime()));
		return new ResponseStructure<>(HttpStatus.OK.value(), "Comments retrived succesfully", list, LocalDateTime.now());
	}

	public ResponseStructure<Comment> deleteComment(int id) {
		Comment comment = dao.deleteComment(id);
		return new ResponseStructure<Comment>(HttpStatus.OK.value(), "Comment deletd succesfully", comment, LocalDateTime.now());
	}

	public ResponseStructure<String> deleteCommentsForPost(int postId) {
		boolean b = dao.deleteCommentForAPost(postId);
		return new ResponseStructure<>(HttpStatus.OK.value(), "Comment deleted succesfully", "Comments for the post "+postId+" deletd succesfully", LocalDateTime.now());
	}


}
