package com.spring.exercises.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.exercises.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
