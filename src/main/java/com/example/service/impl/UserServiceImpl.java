package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDAO;
import com.example.exception.ParrotException;
import com.example.model.User;
import com.example.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> getUsers() {
		Optional<List<User>> users=  this.userDAO.getUsers();
		return users.get();
	}

	@Override
	public User getUser(String userId) {
		Optional<User> user = this.userDAO.getUser(userId);
		if(user.isPresent()) {
		return user.get();
		} else {
			throw new ParrotException("User not exist");
		}
	}

	@Override
	public User addUser(User user) {
		if(user.getUserId().isEmpty()) {
			if(getUserByEmail(user.getEmail()) != null) {
				throw new ParrotException("User exist");
			}
			
			return this.userDAO.addUser(user);
		} 
		
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = this.userDAO.getUserByEmail(email);
		if(user.isPresent()) {
			return user.get();
			} else {
				throw new ParrotException("User not exist");
			}
	}


}
