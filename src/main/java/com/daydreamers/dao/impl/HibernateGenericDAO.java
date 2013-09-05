package com.daydreamers.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daydreamers.dao.GenericDAO;
import com.daydreamers.dao.impl.conversation.IConversationSession;


@Repository
public abstract class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> persistenceClass;

	@Autowired
	private IConversationSession conversationSession;
	
	@SuppressWarnings("unchecked")
	protected HibernateGenericDAO() {
		this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getPersistenceClass() {
		return persistenceClass;
	}

	public void setPersistenceClass(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		
		T entity;
		
		if (lock) {
			entity = (T) getSession().load(getPersistenceClass(), id, LockMode.UPGRADE);
		} else {
			entity = (T) getSession().load(getPersistenceClass(), id);
		}
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = getSession().createCriteria(getPersistenceClass());
		return criteria.list();		
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Example example = Example.create(exampleInstance);
		example.ignoreCase().enableLike(MatchMode.ANYWHERE);
		
		for (String property : excludeProperty) {
			example.excludeProperty(property);
		}
		
		Criteria criteria = getSession().createCriteria(persistenceClass).add(example);		
		return criteria.list();
	}

	@Override
	public void saveOrUpdate(T entity) {
		 getSession().saveOrUpdate(entity);		 
	}
	
	@Override
	public void update(T entity) {
		getSession().update(entity);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		return (T)getSession().merge(entity);
	}
	
	public void remove(ID id) {
		T entity = findById(id, true);
		if (entity != null) {
			getSession().delete(entity);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T makePersistent(T entity) {
		return (T) getSession().merge(entity);
	}

	public void flush() {
		getSession().flush();		
	}

	public void clear() {
		getSession().clear();		
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Session getConversationSession() {
		return conversationSession.getSession();
	}
	
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
}
