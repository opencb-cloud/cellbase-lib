package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.lib.api.CytobandDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CytobandHibernateDBAdaptor extends HibernateDBAdaptor implements CytobandDBAdaptor {

	public CytobandHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<? extends Object> getAll() {
		Criteria criteria = this.openSession().createCriteria(Cytoband.class);
		return criteria.list();
	}

	@Override
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> getAllChromosomeNames() {
		Criteria criteria = this.openSession().createCriteria(Cytoband.class)
		.setProjection(Projections.distinct(Projections.property("chromosome"))).addOrder(Order.asc("chromosome"));
		return criteria.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Cytoband> getAllByChromosome(String chromosome) {
		Criteria criteria = this.openSession().createCriteria(Cytoband.class);
		criteria.add(Restrictions.eq("chromosome", chromosome));
		criteria.addOrder(Order.asc("start"));
		return (List<Cytoband>) this.executeAndClose(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<List<Cytoband>> getAllByChromosomeList(List<String> chromosomes) {
		List<List<Cytoband>> cytobands = new ArrayList<List<Cytoband>>(); 
		for (String chromosome : chromosomes) {
			 cytobands.add(this.getAllByChromosome(chromosome));
		}
		 return cytobands;
	}
	

	@Override
	public List<Cytoband> getAllByRegion(String chromosome, int start) {
		return this.getAllByRegion(chromosome, start, start);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cytoband> getAllByRegion(String chromosome, int start, int end) {
		Criteria criteria = this.openSession().createCriteria(Cytoband.class);
		criteria.add(Restrictions.eq("chromosome", chromosome));
		criteria.add(Restrictions.le("start", end));
		criteria.add(Restrictions.ge("end", start));
		criteria.addOrder(Order.asc("start"));
		return (List<Cytoband>) this.executeAndClose(criteria);
	}


	@Override
	public List<Cytoband> getAllByRegion(Region region) {
		return this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());
	}


	@Override
	public List<List<Cytoband>> getAllByRegionList(List<Region> regionList) {
		List<List<Cytoband>> results = new ArrayList<List<Cytoband>>();
		for (Region region : regionList) {
			results.add(this.getAllByRegion(region));
		}
		return results;
	}

	@Override
	public List<Cytoband> getAllByRegion(String chromosome) {
		return this.getAllByChromosome(chromosome);
	}

	
	

}
