package com.spring.exercises.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.exercises.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(String email);
	User findByUserId(String userId);
	void deleteByUserId(String userId);
}
