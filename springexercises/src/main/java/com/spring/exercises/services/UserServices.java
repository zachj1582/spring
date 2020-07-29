package com.spring.exercises.services;

import java.util.List;

import com.spring.exercises.DTO.UserDTO;
import com.spring.exercises.model.User;

public interface UserServices {

	List<UserDTO> getUsers(int page, int limit);

	User getUser(Long id);

	UserDTO createUser(UserDTO userDTO);

	void updateUser(User user);

	void deleteUser(Long id);

	User getUserByEmail(String email);
	

}
