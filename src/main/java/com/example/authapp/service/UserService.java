package com.example.authapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.authapp.model.User;
import com.example.authapp.repository.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public User login(String email, String pass) {
		return userMapper.login(email, pass);
	}
	
	public User findByEmail(String email) {
		return userMapper.findByEmail(email);
	}
	
	public User register(User user) {
		try {
			// Check if user already exists
			User existingUser = userMapper.findByEmail(user.getEmail());
			if (existingUser != null) {
				return null;
			}
			
			// Insert new user
			userMapper.insertUser(user);
			return userMapper.login(user.getEmail(), user.getPass());
		} catch (Exception e) {
			System.err.println("Registration error: " + e.getMessage());
			return null;
		}
	}
}
