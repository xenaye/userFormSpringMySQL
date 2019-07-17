package com.xenaye.projects.userservice.service;

import java.util.List;

import com.xenaye.projects.userservice.model.User;

public interface ApplicationService {

	User createUser(User user);

	List<User> getAllUsers();
}
