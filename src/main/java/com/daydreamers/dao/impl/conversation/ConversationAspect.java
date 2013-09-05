package com.daydreamers.dao.impl.conversation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ConversationAspect {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private IConversationSession conversation;
	
//	@Pointcut(value="execution(public * *(..))")
//	public void anyPublicMethod() {
//	}

	@Around("@annotation(annotation)")
	public void handleHibernateSession(ProceedingJoinPoint joinpoint, SessionPerConversation annotation) throws Throwable {
				
		if (annotation.firstStep()) {
			Session session = sessionFactory.openSession();
			conversation.startConversation(session);
		}
		
		joinpoint.proceed();			
	
		if (annotation.lastStep()) {
			conversation.endConversation();
		}
		
	}
}
