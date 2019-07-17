package com.xenaye.projects.userservice.service;

import java.util.List;

import com.xenaye.projects.userservice.model.User;
import com.xenaye.projects.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
