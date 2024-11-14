package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.ResponseStructure;
import com.example.demo.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	public ResponseStructure<User> saveUser(User user) {
		User user2 = dao.saveUser(user);

		return new ResponseStructure<>(HttpStatus.OK.value(), "User saved Succesfully", user2, LocalDateTime.now());
	}

	public ResponseStructure<User> updateUser(User user, int id) {
		User user2 = dao.updateUser(user, id);
		return new ResponseStructure<>(HttpStatus.OK.value(), "User updated Succesfully", user2, LocalDateTime.now());
	}

	public ResponseStructure<User> fetchUser(int id) {
		User user = dao.fetchUser(id);
		return new ResponseStructure<>(HttpStatus.OK.value(), "User data fetched Succesfully", user,
				LocalDateTime.now());
	}
	public ResponseStructure<List<User>> fetchAllUsers() {
		List<User> list = dao.fetchAllUsers();
		return new ResponseStructure<>(HttpStatus.OK.value(), "User saved Succesfully", list, LocalDateTime.now());
	}

	public ResponseStructure<User> deleteUser(int id) {
		User user = dao.deleteUser(id);
		return new ResponseStructure<>(HttpStatus.OK.value(), "User deleted Succesfully", user, LocalDateTime.now());
	}

}
