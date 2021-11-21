package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.User;

public interface UserService {

	List<User> getUsers();
	User getUser(String userId);
	User getUserByEmail(String email);
	User addUser(User user);
	
}
