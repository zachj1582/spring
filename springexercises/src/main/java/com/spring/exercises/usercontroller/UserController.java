package com.spring.exercises.usercontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exercises.DTO.UserDTO;
import com.spring.exercises.model.User;
import com.spring.exercises.model.request.UserRequest;
import com.spring.exercises.model.response.UserResponse;
import com.spring.exercises.services.UserServices;



@RestController
@RequestMapping("users")
public class UserController {
	
	private UserServices userServices;
	
	
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}

	
	@GetMapping
	public List<UserResponse> getUsers(
			@RequestParam(value= "page", defaultValue = "1") int page, 
			@RequestParam(value = "limit", defaultValue = "5") int limit){
		
		List<UserDTO> userDTOList = userServices.getUsers(page, limit);
		List<UserResponse> userResponseList = new ArrayList<UserResponse>();
		
		for(int i = 0; i < userDTOList.size(); i++) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDTOList.get(i), userResponse);
			userResponseList.add(userResponse);
		}
		return userResponseList;
	}
	
	@GetMapping(path="/{userId}")
	public UserResponse getUserByUserId(@PathVariable String userId) {
		UserDTO userDTO = userServices.getUserByUserId(userId);
		UserResponse user = new UserResponse();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}
	
	@GetMapping(path="/email/{email}")
	public UserResponse getUserByEmail(@PathVariable String email) {
		UserDTO userDTO = userServices.getUserByEmail(email);
		UserResponse user = new UserResponse();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}
	
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		UserDTO createdUser = userServices.createUser(userDTO);
		UserResponse returnUser = new UserResponse();
		BeanUtils.copyProperties(createdUser, returnUser);
		
		return returnUser;
	}
	
	@PutMapping(path="/{userId}")
	public UserResponse updateUser(@RequestBody UserRequest user, @PathVariable String userId) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		userDTO.setUserId(userId);
		
		UserDTO updateUserDTO = userServices.updateUser(userDTO);
		
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(updateUserDTO, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userServices.deleteUser(userId);
	}
	
}







