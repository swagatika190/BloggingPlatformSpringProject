package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repositries.CommentRepositry;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class CommentDao {
	@Autowired
	private CommentRepositry repositry;
	
	@Autowired
	private PostDao post_dao;
	
	@Autowired
	private UserDao user_dao;
	
	public Comment saveComment(Comment comment,int user_Id,int post_Id) {
		User user = user_dao.fetchUser(user_Id);
		Post post = post_dao.fetchPost(post_Id);
		
		comment.setPost(post);
		comment.setUser(user);
		
		return repositry.save(comment);
	}
	
	public Comment updateComment(Comment comment,int id) {
		if(!repositry.existsById(id)) {
			throw new EntityNotFoundException("Comment with the id :"+id+" is not present");
		}
		comment.setId(id);
		return repositry.save(comment);
	}
	
	
	public Comment fetchComment(int id) {
		Optional<Comment> optional = repositry.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Comment with the id :"+id+" is not present");
		}
		return optional.get();
		
	}
	
	public List<Comment> fetchAllCommentsForPost(int postId) {
		Post post = post_dao.fetchPost(postId);
		List<Comment> list = post.getComments();
		if(list.isEmpty()) {
			throw new EntityNotFoundException("Comments for the post with the "+postId+" is not present");
		}
		return list;
	}
	
	
	public Comment deleteComment(int id) {
		Comment comment = fetchComment(id);
		 repositry.delete(comment);
		 return comment;
	}
	
	public boolean deleteCommentForAPost(int id) {
		Post post = post_dao.fetchPost(id);
		post.setComments(null);
		post_dao.updatePost(post, id);
		return true;
		
	}
}



