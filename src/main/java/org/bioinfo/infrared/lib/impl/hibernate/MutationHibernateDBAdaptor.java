package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.lib.api.MutationDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

public class MutationHibernateDBAdaptor extends HibernateDBAdaptor implements MutationDBAdaptor{

	public MutationHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public MutationHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MutationPhenotypeAnnotation> getAllByRegion(Region region) {
		Query query = this.openSession().createQuery("select mpa from MutationPhenotypeAnnotation mpa where mpa.chromosome= :CHROMOSOME and mpa.start >= :START and mpa.end <= :END")
				.setParameter("CHROMOSOME", region.getChromosome())
				.setParameter("START", region.getStart())
				.setParameter("END", region.getEnd());
		return (List<MutationPhenotypeAnnotation>) executeAndClose(query);
	}

	@Override
	public List<List<MutationPhenotypeAnnotation>> getAllByRegionList(List<Region> regionList) {
		List<List<MutationPhenotypeAnnotation>> result = new ArrayList<List<MutationPhenotypeAnnotation>>(regionList.size());
		for (Region region : regionList) {
			result.add(this.getAllByRegion(region));
		}
		return result;
	}
	
	@Override
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (g.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from  mutation_phenotype_annotation g where g.chromosome= '"+region.getChromosome()+"' and g.start <= "+region.getEnd()+" and g.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}
	
}
