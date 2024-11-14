package com.example.demo.entity;


import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(unique = true,nullable=false)
	private String phoneNO;
	@Column(nullable = false)
	private String password;
	private String gender;
	private String dob;
	@OneToMany(mappedBy = "user")
	@Cascade(CascadeType.REMOVE)
	private List<Post> post;
	@OneToMany(mappedBy = "user")
	@Cascade(CascadeType.REMOVE)
	private List<Comment> comments;
	
}
