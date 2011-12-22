package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.TfbsDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

class TfbsHibernateDBAdaptor extends HibernateDBAdaptor implements TfbsDBAdaptor {


	public TfbsHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Tfbs> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tfbs> getAllByRegion(String chromosome, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tfbs> getAllByRegion(String chromosome, int start, int end) {
//		Criteria criteria = this.openSession().createCriteria(Tfbs.class)
//		.add(Restrictions.ge("end", start))
//		.add(Restrictions.le("start", end))
//		.add(Restrictions.eq("chromosome", chromosome))
//		.addOrder(Order.asc("chromosome"))
//		.addOrder(Order.asc("start"))
//		.addOrder(Order.asc("cellType"));
//
//		 ProjectionList projList = Projections.projectionList();
//		 projList.add(Projections.groupProperty("tfName"));
//	     projList.add(Projections.groupProperty("cellType"));
//	     projList.add(Projections.groupProperty("start"));
//		
//	     criteria.setProjection(projList);
//	     
//	     
	     
	     String Hquery = "from Tfbs tf left join fetch tf.pwm p where tf.end >= :start and tf.start <= :end and tf.chromosome=:chromosome  group by tf.tfName, tf.cellType, tf.start, p.pwmId order by tf.start, tf.cellType asc";
	     Query query = this.openSession().createQuery(Hquery);
	     query.setParameter("start", start);
	     query.setParameter("end", end);
	     query.setParameter("chromosome", chromosome);
	     
		return (List<Tfbs>) this.executeAndClose(query);
	}


	@Override
	public List<Tfbs> getAllByRegion(Region region) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<Tfbs>> getAllByRegionList(List<Region> regionList) {
		 List<List<Tfbs>> result = new ArrayList<List<Tfbs>>();
		 
		 for (Region region : regionList) {
			 result.add(this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd()));		 
		 }
		
		return result;
	}

	@Override
	public List<Tfbs> getAllByInternalId(String id) {
		  String Hquery = "from Tfbs tf left join fetch tf.pwm p where tf.tfbsId=:id group by tf.tfName, tf.cellType, tf.start, p.pwmId order by tf.start, tf.cellType asc";
		  Query query = this.openSession().createQuery(Hquery);
		  query.setParameter("id", Integer.valueOf(id));
		  return (List<Tfbs>) executeAndClose(query);
	}
	
	
	
	@Override
	public List<Tfbs> getAllByInternalIdList(List<String> idList) {
		List<Tfbs> result = new ArrayList<Tfbs>();
		for (String id : idList) {
			result.addAll(this.getAllByInternalId(id));
		}
		return result;
		
	}
	
	
	
	
	
	
	@Override
	public List<? extends Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
