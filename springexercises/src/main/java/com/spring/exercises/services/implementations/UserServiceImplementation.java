package com.spring.exercises.services.implementations;

import java.util.ArrayList;
import java.util.List;
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
	public List<UserDTO> getUsers(int page, int limit) {
		List<User> users = new ArrayList<User>();
		
		if (page>0) page --;
		PageRequest pageableRequest = PageRequest.of(page, limit);
		
		Page<User> userPageList = userRepository.findAll(pageableRequest);

		users = userPageList.getContent();
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		
		for (int i = 0; i < users.size(); i++) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(users.get(i), userDTO);
			userDTOList.add(userDTO);
		}
		
		return userDTOList;
	}

	@Override
	public UserDTO getUserByUserId(String userId) {
		User user = userRepository.findByUserId(userId);
		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(user, returnValue);
		return returnValue;
	}
	
	@Override
	public UserDTO getUserByEmail(String email) {
		User foundUser = userRepository.findByEmail(email);
		UserDTO user = new UserDTO();
		BeanUtils.copyProperties(foundUser, user);
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
	public UserDTO updateUser(UserDTO userDTO) {
		User foundUser = userRepository.findByUserId(userDTO.getUserId());
		BeanUtils.copyProperties(userDTO, foundUser);
		
		User updatedUser = userRepository.save(foundUser);
		
		UserDTO returnValue = new UserDTO();
		
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteByUserId(userId);
	}
}







