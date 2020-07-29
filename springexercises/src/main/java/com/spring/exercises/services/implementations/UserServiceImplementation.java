package com.spring.exercises.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public void createUser(User user) {
		userRepository.save(user);
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







