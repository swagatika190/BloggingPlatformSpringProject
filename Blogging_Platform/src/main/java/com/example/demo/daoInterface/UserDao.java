package com.example.demo.daoInterface;

import java.util.List;

import com.example.demo.entity.User;

public interface UserDao {
	User saveUser(	User user);
	User updateUser(User user, int id);
	User fetchUser(int id);
	List<User> fetchAllUsers() ;
	User deleteUser(int id);
}
