package com.daydreamers.dao.impl;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class LogInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("ON DELETE >> " + entity.toString());
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		System.out.println("ON FLUSH DIRTY >> " + entity.toString());
		return false;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("ON LOAD >> " + entity.toString());
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		System.out.println("ON SAVE >> " + entity.toString());
		return false;
	}

	public void afterTransactionCompletion(Transaction tx) {
		System.out.print("AFTER TRANSACTION COMPLETITION >> ");
		if (tx.wasCommitted()) {
			
			System.out.println("WAS COMMITTED");
		} else {
			System.out.println("WAS NOT COMMITTED");
		}
	}
	
	public void preFlush(Iterator entities) {
		System.out.println("ABOUT TO FLUSH THE FOLLOWING ENTITIES : ");
		while (entities.hasNext()) {
			Object entity = entities.next();
			System.out.println(entity.toString());
		}
		
	}

}
