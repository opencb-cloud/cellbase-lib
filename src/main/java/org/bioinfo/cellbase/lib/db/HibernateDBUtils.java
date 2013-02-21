package org.bioinfo.cellbase.lib.db;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Deprecated
public class HibernateDBUtils {

	private static final SessionFactory sessionFactory = createSessionFactory();

	private static String staticTest;
	
	@Deprecated
    private static SessionFactory createSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

	@Deprecated
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	public static void setStaticTest(String msg) {
		staticTest = msg;
	}
    
	public static  String getStaticTest() {
		return staticTest;
	}
    
    public static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory buildSessionFactory(Properties properties) {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	if(properties != null && properties.keySet().size() > 0) {
        		Configuration cfg = new Configuration();
        		cfg.setProperties(properties);
        		return cfg.buildSessionFactory();
        	}else {
        		return new Configuration().configure().buildSessionFactory();
        	}
        } catch(Throwable ex) { 
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
