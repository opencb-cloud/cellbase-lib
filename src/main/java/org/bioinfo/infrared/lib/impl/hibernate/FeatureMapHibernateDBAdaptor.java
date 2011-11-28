package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.lib.api.FeatureMapDBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class FeatureMapHibernateDBAdaptor extends HibernateDBAdaptor implements FeatureMapDBAdaptor {
	private static int FEATURE_MAP_CHUNK_SIZE = 400;
	
	public FeatureMapHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FeatureMap> getAllByRegion(String chromosome, int start, int end) {
		int chunk_start = start / FeatureMapHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		int chunk_end = end / FeatureMapHibernateDBAdaptor.FEATURE_MAP_CHUNK_SIZE;
		
		/** Obtenemos los chunks de feature map **/
		Criteria criteria = this.openSession().createCriteria(FeatureMap.class);
		criteria.add(Restrictions.ge("chunk_id", chunk_start));
		criteria.add(Restrictions.le("chunk_id", chunk_end));
		criteria.add(Restrictions.eq("chromosome", chromosome));
		
		
		return (List<FeatureMap>)executeAndClose(criteria);
	
	}
	
	

}
