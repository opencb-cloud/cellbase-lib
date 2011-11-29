package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.lib.api.CytobandDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;

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
	public List<Cytoband> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cytoband> getAllByRegion(String chromosome, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cytoband> getAllByRegion(String chromosome, int start, int end) {
		System.err.println("toy" + chromosome+":" + start + "_" + end);
		Criteria criteria = this.openSession().createCriteria(Cytoband.class);
		criteria.add(Restrictions.le("chromosome", chromosome));
		criteria.add(Restrictions.le("start", end));
		criteria.add(Restrictions.ge("end", start));
		return (List<Cytoband>) this.executeAndClose(criteria);
	}


	@Override
	public List<Cytoband> getAllByRegion(Region region) {
		System.out.println("rrrrrrrrrrrr");
		return this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());
	}


	@Override
	public List<List<Cytoband>> getAllByRegionList(List<Region> regionList) {
		System.out.println("rrrrrrrrrrrr");
		List<List<Cytoband>> results = new ArrayList<List<Cytoband>>();
		for (Region region : regionList) {
			results.add(this.getAllByRegion(region));
		}
		return results;
	}

	
	

}
