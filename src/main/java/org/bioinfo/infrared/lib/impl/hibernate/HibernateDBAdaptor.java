package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.List;

import org.bioinfo.commons.log.Logger;
import org.bioinfo.infrared.lib.api.DBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class HibernateDBAdaptor implements DBAdaptor{

	private SessionFactory sessionFactory;
	private Session session;

	protected Logger logger;
	
//	private static Map<String, SessionFactory> sessionFactories;
	
	public HibernateDBAdaptor() {
//		this.hibernateDBConnector = new HibernateDBAdaptorFactory();
	}
	
	public HibernateDBAdaptor(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

		logger = new Logger();
		logger.setLevel(Logger.DEBUG_LEVEL);
	}
	
	protected Session getSession() {
		if(session != null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	protected List<?> execute(Criteria criteria){
		List<?> result = criteria.list();
//		hibernateDBConnector.openSession();
		this.getSession().close();
		return result;
	}

	protected List<?> execute(Query query){
		List<?> result = query.list();
		this.getSession().close();
		return result;
	}
}
