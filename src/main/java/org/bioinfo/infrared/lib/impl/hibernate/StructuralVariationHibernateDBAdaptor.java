package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.StructuralVariation;
import org.bioinfo.infrared.lib.api.StructuralVariationDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class StructuralVariationHibernateDBAdaptor extends HibernateDBAdaptor implements StructuralVariationDBAdaptor{

	public StructuralVariationHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public StructuralVariationHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StructuralVariation> getAllByRegion(Region region) {
		Query query = this.openSession().createQuery("select sv from StructuralVariation sv where sv.chromosome= :CHROMOSOME and sv.start <= :END and sv.end >= :START group by sv.displayId")
				.setParameter("CHROMOSOME", region.getChromosome())
				.setParameter("START", region.getStart())
				.setParameter("END", region.getEnd());
		return (List<StructuralVariation>) executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList) {
		List<List<StructuralVariation>> result = new ArrayList<List<StructuralVariation>>(regionList.size());
		Query query = null;
		Session session = this.openSession();
		for (Region region : regionList) {
			query = session.createQuery("select sv from StructuralVariation sv where sv.chromosome= :CHROMOSOME and sv.start <= :END and sv.end >= :START group by sv.displayId")
					.setParameter("CHROMOSOME", region.getChromosome())
					.setParameter("START", region.getStart())
					.setParameter("END", region.getEnd());
			result.add((List<StructuralVariation>) execute(query));
		}
		session.close();
		return result;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<StructuralVariation> getAllByRegion(Region region, int minLength, int maxLength) {
		Query query = this.openSession().createQuery("select sv from StructuralVariation sv where sv.chromosome= '"+region.getChromosome()+"' and sv.start <= "+region.getEnd()+" and sv.end >= "+region.getStart()+" and (sv.end-sv.start) >= "+minLength+" and (sv.end-sv.start) <= "+maxLength+" group by sv.displayId");
		return (List<StructuralVariation>) executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList, int minLength, int maxLength) {
		List<List<StructuralVariation>> structVariationListList = new ArrayList<List<StructuralVariation>>(regionList.size());
		Query query = null;
		Session session = this.openSession();
		for(Region region: regionList) {
			query = session.createQuery("select sv from StructuralVariation sv where sv.chromosome= '"+region.getChromosome()+"' and sv.start <= "+region.getEnd()+" and sv.end >= "+region.getStart()+" and (sv.end-sv.start) >= "+minLength+" and (sv.end-sv.start) <= "+maxLength+" group by sv.displayId");
			structVariationListList.add((List<StructuralVariation>) execute(query));
		}
		session.close();
		return structVariationListList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (g.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from structural_variation g where g.chromosome= '"+region.getChromosome()+"' and g.start <= "+region.getEnd()+" and g.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}

}
