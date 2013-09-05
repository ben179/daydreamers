package com.daydreamers.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	T findById(ID id, boolean lock);
	List<T> findAll();
	List<T> findByExample(T exampleInstance, String... excludeProperty);
	T makePersistent(T entity);
	void saveOrUpdate(T entity);
	void update(T entity);
	T merge(T entity);
	void remove(ID id);
	void flush();
	void clear();
}
