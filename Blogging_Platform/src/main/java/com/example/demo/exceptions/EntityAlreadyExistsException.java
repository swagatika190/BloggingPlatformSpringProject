package com.example.demo.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
	public EntityAlreadyExistsException(String msg) {
		super(msg);
	}

}
