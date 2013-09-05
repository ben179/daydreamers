package com.daydreamers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daydreamers.dao.UserDAO;
import com.daydreamers.dao.impl.conversation.SessionPerConversation;
import com.daydreamers.model.Image;
import com.daydreamers.model.User;
import com.daydreamers.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Transactional(readOnly=true)
	public User getUser(String login) {
		return userDAO.getUserByLogin(login);
	}
	
	@SessionPerConversation(firstStep=true)
	public User getUserForUpdate(String login) {
		return userDAO.getUserForUpdateByLogin(login);
	}
	
	@Override
	@SessionPerConversation(lastStep=true)
	public void updateUser(String login, User user) {
		user.setLogin(login);
		User u = userDAO.getUserByLogin(user.getLogin());
		user.setId(u.getId());
		userDAO.merge(user);
	}	
	
	@Transactional(readOnly=false)
	public void deleteUser(String login) {
		userDAO.deleteUserByLogin(login);
	}

	@Transactional(readOnly=false)
	public void addUser(User user) {
		userDAO.saveOrUpdateUser(user);
	}
	
	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}


	@Transactional(readOnly=false)
	public void createImageForUser(String userLogin, Image image) {

		User u = userDAO.getUserByLogin(userLogin);
		if (u != null) {
			u.addImage(image);
		}
	}
}
