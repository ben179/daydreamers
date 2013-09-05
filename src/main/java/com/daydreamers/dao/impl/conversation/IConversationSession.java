package com.daydreamers.dao.impl.conversation;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface IConversationSession {

	public abstract void startConversation(Session session);

	public abstract void endConversation();

	public abstract void closeSession();

	public abstract void commitTransaction();

	public abstract Session getSession();

	public abstract void setSession(Session session);

	public abstract void resetSession();

	public abstract Transaction getTransaction();

	public abstract void setTransaction(Transaction transaction);

}