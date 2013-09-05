package com.daydreamers.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import com.daydreamers.dao.UserDAO;
import com.daydreamers.model.User;

@Repository
public class HibernateUserDAO extends HibernateGenericDAO<User, Long> implements UserDAO {


	@Override
	public User getUserByLogin(String login) {
		Criteria criteria = getSession().createCriteria(User.class).add(eq("login", login));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void saveOrUpdateUser(User user) {
		 getSession().saveOrUpdate(user);		 
	}

	@Override
	public void deleteUserByLogin(String login) {
		User user = getUserByLogin(login);
		if (user != null) {
			getSession().delete(user);
		}
	}

	@Override
	public User getUserForUpdateByLogin(String login) {
		Criteria criteria = getConversationSession().createCriteria(User.class).add(eq("login", login));
		criteria.setLockMode(LockMode.UPGRADE);
		User u = (User) criteria.uniqueResult();
		return u;
	}
}
