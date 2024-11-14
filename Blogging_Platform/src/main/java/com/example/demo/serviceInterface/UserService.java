package com.example.demo.serviceInterface;

import java.util.List;

import com.example.demo.entity.ResponseStructure;
import com.example.demo.entity.User;

public interface UserService {
	ResponseStructure<User>saveUser(User user );
	ResponseStructure<User>updateUser(User user,int id);
	ResponseStructure<User> fetchUser(int id);
	ResponseStructure<List<User>> fetchAllUsers();
	ResponseStructure<User> deleteUser(int id) ;

}
