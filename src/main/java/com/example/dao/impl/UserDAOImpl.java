package com.example.dao.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDAO;
import com.example.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private final MongoOperations mongoOperations;

    @Autowired
    public UserDAOImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    
	@Override
	public Optional<List<User>> getUsers() {
		List<User> d = this.mongoOperations.find(new Query(), User.class);
		Optional<List<User>> users = Optional.ofNullable(d);
		return users;
	}

	@Override
	public Optional<User> getUser(String userId) {
		User d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)), User.class);
		Optional<User> user = Optional.ofNullable(d);
		return user;
	}

	@Override
	public User addUser(User user) {
		this.mongoOperations.save(user);
		return getUser(user.getUserId()).get();
	}

	@Override
	public void updateUser(User user) {
		this.mongoOperations.save(user);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		User d = this.mongoOperations.findOne(new Query(Criteria.where("email").is(email)), User.class);
		Optional<User> user = Optional.ofNullable(d);
		return user;
	}

}
