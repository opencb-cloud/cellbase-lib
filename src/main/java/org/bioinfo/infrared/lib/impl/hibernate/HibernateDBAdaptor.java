package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.List;

import org.bioinfo.infrared.lib.impl.DBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class HibernateDBAdaptor extends DBAdaptor{

	private SessionFactory sessionFactory;
	private Session session;
	
//	public HibernateDBAdaptor() {
//
//	}
	
	public HibernateDBAdaptor(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	protected Session openSession() {
		if(session == null || !session.isOpen()) {
			long t1 = System.currentTimeMillis();
			session = sessionFactory.openSession();
			logger.debug("HibernateDBAdaptorFactory in getGeneDBAdaptor(): Hibernate Session object for SessionFactory created in "+(System.currentTimeMillis()-t1)+" ms");
		}
		return session;
	}
	
	protected List<?> execute(Query query){
		List<?> result = query.list();
		return result;
	}
	

	protected List<?> executeAndClose(Criteria criteria){
		List<?> result = criteria.list();
		closeSession();
		return result;
	}

	protected List<?> executeAndClose(Query query){
		List<?> result = query.list();
		closeSession();
		return result;
	}
	
	protected void closeSession() {
		if(session != null && session.isOpen()) {
			session.close();
		}
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
