package com.spring.exercises.services;

import java.util.List;

import com.spring.exercises.model.User;

public interface UserServices {

	List<User> getUsers();

	User getUser(Long id);

	void createUser(User user);

	

}
