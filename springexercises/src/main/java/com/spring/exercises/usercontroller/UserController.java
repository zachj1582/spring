package com.spring.exercises.usercontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<User> getUsers(
			@RequestParam(value= "page", defaultValue = "1") int page, 
			@RequestParam(value = "limit", defaultValue = "5") int limit){
		List<User> users = userServices.getUsers(page, limit);
		return users;
	}
	
	@GetMapping(path="/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userServices.getUser(id);
		return user;
	}
	
	@GetMapping(path="/email/{email}")
	public User getUser(@PathVariable String email) {
		User user = userServices.getUserByEmail(email);
		return user;
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		userServices.createUser(user);
	}
	
	@PutMapping
	public void updateUser(@RequestBody User user) {
		userServices.updateUser(user);
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteUser(@PathVariable Long id) {
		userServices.deleteUser(id);
	}
	
}







