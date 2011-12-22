package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.RegulatoryRegion;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.infrared.lib.api.RegulatoryRegionDBAdaptor;
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
import org.hibernate.criterion.Restrictions;

class RegulatoryRegionHibernateDBAdaptor extends HibernateDBAdaptor implements RegulatoryRegionDBAdaptor {


	public RegulatoryRegionHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(Region region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, List<String> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start,
			List<String> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegulatoryRegion> getAllByRegion(String chromosome, int start, int end, List<String> type) {
		Criteria criteria = this.openSession().createCriteria(RegulatoryRegion.class)
		.add(Restrictions.ge("end", start))
		.add(Restrictions.le("start", end))
		.add(Restrictions.eq("chromosome", chromosome))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));
		
		if (type != null){
			criteria.add(Restrictions.in("type", type));
		}
		
		return criteria.list();
	}
	
	
	@Override
	public List<RegulatoryRegion> getAllByInternalId(String id) {
		  String Hquery = "from RegulatoryRegion re where re.regulatoryRegionId=:id";
		  Query query = this.openSession().createQuery(Hquery);
		  query.setParameter("id", Integer.valueOf(id));
		  return (List<RegulatoryRegion>) executeAndClose(query);
	}
	
	
	
	@Override
	public List<RegulatoryRegion> getAllByInternalIdList(List<String> idList) {
		List<RegulatoryRegion> result = new ArrayList<RegulatoryRegion>();
		for (String id : idList) {
			result.addAll(this.getAllByInternalId(id));
		}
		return result;
		
	}
	
	

	@Override
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList) {
		return this.getAllByRegionList(regionList, null);
	}

	@Override
	public List<List<RegulatoryRegion>> getAllByRegionList(List<Region> regionList,List<String> type) {
		List<List<RegulatoryRegion>> result = new ArrayList<List<RegulatoryRegion>>();
		for (Region region : regionList) {
			result.add(this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), type));
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

	@Override
	public List<RegulatoryRegion> getAllByRegion(Region region, String type) {
		// TODO Auto-generated method stub
		return null;
	}


	


}
