package com.example.demo.daoInterface;

import java.util.List;

import com.example.demo.entity.Comment;

public interface CommentDao {
	Comment saveComment(Comment comment,int user_Id,int post_Id);
	Comment updateComment(Comment comment,int id);
	Comment fetchComment(int id);
	List<Comment> fetchAllCommentsForPost(int postId) ;
	Comment deleteComment(int id);
	boolean deleteCommentForAPost(int id);

}
