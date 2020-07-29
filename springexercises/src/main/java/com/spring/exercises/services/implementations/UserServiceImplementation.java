package com.spring.exercises.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.exercises.DTO.UserDTO;
import com.spring.exercises.dao.UserRepository;
import com.spring.exercises.model.User;
import com.spring.exercises.services.UserServices;
import com.spring.exercises.shared.Utils;

@Service
public class UserServiceImplementation implements UserServices {

	private UserRepository userRepository;
	private Utils utils;

	public UserServiceImplementation(UserRepository userRepository, Utils utils) {
		this.userRepository = userRepository;
		this.utils = utils;
		
	}

	@Override
	public List<User> getUsers(int page, int limit) {
		List<User> users;
		
		if (page>0) page --;
		PageRequest pageableRequest = PageRequest.of(page,  limit);
		
		Page<User> userPage = userRepository.findAll(pageableRequest);
		
		users = userPage.getContent();
		
		
		return users;
	}

	@Override
	public User getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		
		user.setEncryptedPassword("password-test");
		user.setEmailVerification(true);
		user.setUserId(utils.generateUserId(20));
		
		User createdUser = userRepository.save(user);
		UserDTO returnUser = new UserDTO();
		BeanUtils.copyProperties(createdUser, returnUser);
		return returnUser;
	}

	@Override
	public void updateUser(User user) {
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		for (int i = 0; i<users.size(); i++) {
			if (users.get(i).getId() == user.getId()) {
				userRepository.save(user);
			}
		}
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	
}







