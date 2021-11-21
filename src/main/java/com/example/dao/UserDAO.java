package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.model.User;

public interface UserDAO {

	Optional<List<User>> getUsers();
	Optional<User> getUser(String userId);
	Optional<User> getUserByEmail(String email);
	User addUser(User user);
	void updateUser(User user);
}
