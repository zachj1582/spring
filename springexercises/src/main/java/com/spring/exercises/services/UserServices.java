package com.spring.exercises.services;

import java.util.List;

import com.spring.exercises.DTO.UserDTO;
import com.spring.exercises.model.User;

public interface UserServices {

	List<UserDTO> getUsers(int page, int limit);

	UserDTO getUserByUserId(String userId);

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(UserDTO userDTO);

	void deleteUser(String userId);

	UserDTO getUserByEmail(String email);
	

}
