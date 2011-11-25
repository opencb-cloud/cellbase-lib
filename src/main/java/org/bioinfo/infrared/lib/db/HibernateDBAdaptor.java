package org.bioinfo.infrared.lib.db;

import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

@Deprecated
public class HibernateDBAdaptor {

	private HibernateDBConnector hibernateDBConnector;
	
	private SessionFactory sessionFactory;
	private Session session;

	protected Criteria criteria;

	public HibernateDBAdaptor() {
//		this.hibernateDBConnector = new HibernateDBAdaptorFactory();
	}
	
	public HibernateDBAdaptor(String species) {
//		this.hibernateDBConnector = new HibernateDBAdaptorFactory();
	}
	
	public HibernateDBAdaptor(HibernateDBConnector hibernateDBConnector) {
		this.hibernateDBConnector = hibernateDBConnector;
	}

	protected Session getSession() {
		if(session != null) {
//			sessionFactory = HibernateDBUtils.getSessionFactory();
			session = sessionFactory.openSession();
		}
//		return HibernateUtil.getSessionFactory().openSession();
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
	
	
//	
//	private static Map<String, HibernateDBADaopt> adapters;
//		
//	public static GeneDBAdapter getGeneDBAdapter(String species) {
//		
//		if(adapters.contains(species+"_gene")) {
//			return ;
//			
//		}else {
//			check1();
//			check2()
//			adpaters.ad(sp+"_gene", new GeneDBAdapter());
//		}
//		
//		
//	}
	
}
