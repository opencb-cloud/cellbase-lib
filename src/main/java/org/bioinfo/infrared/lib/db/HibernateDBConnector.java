package org.bioinfo.infrared.lib.db;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Deprecated
public class HibernateDBConnector {

	private SessionFactory sessionFactory;

	public HibernateDBConnector() {
		this(null);
	}

	public HibernateDBConnector(Properties properties) {
		this.sessionFactory = buildSessionFactory(properties);
	}

	private SessionFactory buildSessionFactory(Properties properties) {
		try {
			if(properties != null && properties.keySet().size() > 0) {
				Configuration cfg = new Configuration();
				cfg.setProperties(properties);
				return cfg.buildSessionFactory();
			}else {
				// Create the SessionFactory from hibernate.cfg.xml
				return new Configuration().configure().buildSessionFactory();
			}
		} catch(Throwable ex) { 
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public Session openSession() {
		if(sessionFactory != null && !sessionFactory.isClosed()) {
			return sessionFactory.openSession();
		}else {
			System.err.println("Open session from SessionFactory failed.");
			return null;
		}
	}
	
}
