package com.daydreamers.services;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {

	T create(T item);
	T read(ID itemId);
	List<T> readAll();
	T readByExample(T item);	
	T update(T item);
	void delete(ID item);
	
}
