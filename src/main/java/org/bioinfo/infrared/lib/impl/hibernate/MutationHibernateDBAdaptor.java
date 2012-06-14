package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.core.cellbase.SnpPhenotypeAnnotation;
import org.bioinfo.infrared.lib.api.MutationDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class MutationHibernateDBAdaptor extends HibernateDBAdaptor implements MutationDBAdaptor{

	public MutationHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public MutationHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByPosition(Position position) {
		Criteria criteria = this.openSession().createCriteria(SnpPhenotypeAnnotation.class)
				.add(Restrictions.eq("chromosome", position.getChromosome()))
				.add(Restrictions.le("start", position.getPosition()))
				.add(Restrictions.ge("end", position.getPosition()));
			return (List<MutationPhenotypeAnnotation>) executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByPositionList(List<Position> position) {
		List<List<MutationPhenotypeAnnotation>> result = new ArrayList<List<MutationPhenotypeAnnotation>>(position.size());
		Criteria criteria;
		// To optimize number of sessions
		Session session =  this.openSession();
		for(Position pos: position) {
			criteria = session.createCriteria(MutationPhenotypeAnnotation.class)
				.add(Restrictions.eq("chromosome", pos.getChromosome()))
				.add(Restrictions.le("start", pos.getPosition()))
				.add(Restrictions.ge("end", pos.getPosition()));
			result.add((List<MutationPhenotypeAnnotation>) execute(criteria));
		}
		session.close();
		return result;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (g.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from  mutation_phenotype_annotation g where g.chromosome= '"+region.getChromosome()+"' and g.start <= "+region.getEnd()+" and g.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}

}
