package org.bioinfo.infrared.db;

import java.util.List;

import org.bioinfo.infrared.dao.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;

public class HibernateDataAdapter {
	
	protected Session getSession(){
		   return HibernateUtil.getSessionFactory().openSession();
	}
	
	protected List execute(Criteria criteria){
		List result = criteria.list();
		this.getSession().close();
		return result;
	}
	
	protected List execute(Query query){
		List result = query.list();
		this.getSession().close();
		return result;
	}
}
