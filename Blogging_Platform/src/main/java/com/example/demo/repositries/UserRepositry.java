package com.example.demo.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepositry extends JpaRepository<User, Integer> {
	
public boolean existsByEmail(String email);
	public boolean existsByPhoneNO(String phoneNO);
}
