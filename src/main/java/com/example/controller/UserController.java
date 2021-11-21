package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.example.model.User;
import com.example.service.UserService;

@RestController
public class UserController {

	private static final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userSerivce;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return ResponseEntity.ok(this.userSerivce.addUser(user));
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(this.userSerivce.getUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		return ResponseEntity.ok(this.userSerivce.getUser(userId));
	}
	
	@RequestMapping(value="/{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
    	log.info("Delete user " + userId);
        
        return ResponseEntity.noContent().build();
    }
}
