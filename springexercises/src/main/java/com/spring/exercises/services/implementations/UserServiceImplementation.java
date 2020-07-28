package com.spring.exercises.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.exercises.dao.UserRepository;
import com.spring.exercises.model.User;
import com.spring.exercises.services.UserServices;

@Service
public class UserServiceImplementation implements UserServices {

	private UserRepository userRepository;

	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUsers() {
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		return users;
	}

	@Override
	public User getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);
		
	}
	
}
