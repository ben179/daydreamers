package com.daydreamers.dao.impl.conversation;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class ConversationSession implements IConversationSession {

	private Session session = null;
	private Transaction transaction = null;
	
	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#startConversation(org.hibernate.Session)
	 */
	@Override
	public void startConversation(Session session) {
		this.session = session;
		this.session.setFlushMode(FlushMode.MANUAL);
		this.transaction = session.beginTransaction();		
	}
	
	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#endConversation()
	 */
	@Override
	public void endConversation() {
		session.flush();
		transaction.commit();
		session.close();
		session = null;
		transaction = null;
	}
	
	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#closeSession()
	 */
	@Override
	public void closeSession() {
		session.close();
	}
	
	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#commitTransaction()
	 */
	@Override
	public void commitTransaction() {
		transaction.commit();
	}
	
	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#getSession()
	 */
	@Override
	public Session getSession() {
		return session;
	}

	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		this.session = session;
	}
	
	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#resetSession()
	 */
	@Override
	public void resetSession() {
		session = null;
	}

	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#getTransaction()
	 */
	@Override
	public Transaction getTransaction() {
		return transaction;
	}

	/* (non-Javadoc)
	 * @see com.daydreamers.dao.impl.conversation.IConversationSession#setTransaction(org.hibernate.Transaction)
	 */
	@Override
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
}
