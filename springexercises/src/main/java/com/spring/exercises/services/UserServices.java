package com.spring.exercises.services;

import java.util.List;

import com.spring.exercises.model.User;

public interface UserServices {

	List<User> getUsers(int page, int limit);

	User getUser(Long id);

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(Long id);

	User getUserByEmail(String email);
	

}
