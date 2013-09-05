package com.daydreamers.services;

import java.util.List;

import com.daydreamers.model.Image;
import com.daydreamers.model.User;

public interface UserService {

	User getUser(String login);
	User getUserForUpdate(String login);
	void deleteUser(String login);
	void addUser(User user);
	void updateUser(String login, User user);
	List<User> getAllUsers();
	
	
	
	void createImageForUser(String userLogin, Image image);
}
