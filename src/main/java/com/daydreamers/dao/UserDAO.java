package com.daydreamers.dao;

import com.daydreamers.model.User;

public interface UserDAO extends GenericDAO<User, Long> {
	
	User getUserByLogin(String login);
	User getUserForUpdateByLogin(String login);
	void saveOrUpdateUser(User user);
	void deleteUserByLogin(String login);
}
