package com.example.authapp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.authapp.model.User;

@Mapper
public interface UserMapper {
	User login(@Param("email") String email, @Param("pass") String pass);
	
	User findByEmail(@Param("email") String email);
	
	void insertUser(User user);
}
