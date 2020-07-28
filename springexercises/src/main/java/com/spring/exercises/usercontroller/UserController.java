package com.spring.exercises.usercontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exercises.model.User;
import com.spring.exercises.services.UserServices;



@RestController
@RequestMapping("users")
public class UserController {
	
	private UserServices userServices;
	
	
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}

	
	@GetMapping
	public List<User> getUsers(){
		List<User> users = userServices.getUsers();
		return users;
	}
	
	@GetMapping(path="/{id}")
	public User getSingleUser(@PathVariable Long id) {
		User user = userServices.getUser(id);
		return user;
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		userServices.createUser(user);
	}
}
