package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

class GeneHibernateDBAdaptor extends HibernateDBAdaptor implements GeneDBAdaptor {
	
	
	public GeneHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Gene> getGeneById(String geneId){
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", geneId.trim()));
		return (List<Gene>)execute(criteria);
	}
	
	public List<List<Gene>> getGeneByIdList(List<String> geneIdList){
		List<List<Gene>> result = new ArrayList<List<Gene>>(geneIdList.size());
		for(String id: geneIdList) {
			result.add(getGeneById(id));
		}
		return result;
	}
	
	/** BY REGION **/
	@SuppressWarnings("unchecked")
	public List<Gene> getGeneByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return (List<Gene>)execute(criteria);
	}
	
	public List<List<Gene>> getGeneByRegion(String chregionId){
		List<Region> regions = Region.parseRegions(chregionId);
		List<List<Gene>> result = new ArrayList<List<Gene>>(regions.size());
		for(Region region: regions) {
			result.add(getGeneByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	
	
	/** BY TRANSCRIPT **/
	@SuppressWarnings("unchecked")
	public List<Gene> getGeneByTranscript(String id){
		Criteria criteria = this.getSession().createCriteria(Gene.class)
		.createCriteria("transcripts")
		.add(Restrictions.eq("stableId", id.trim()));
		return (List<Gene>)execute(criteria);
	}
	
	public List<List<Gene>> getGeneByTranscriptList(List<String> identifiers){
		List<List<Gene>> result = new ArrayList<List<Gene>>(identifiers.size());
		for (String id : identifiers) {
			result.add(getGeneByTranscript(id));
		}
		return result;
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequence(String strand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegion(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
//		Criteria criteria =  this.getSession().createCriteria(Gene.class);
//		Disjunction disjunction = Restrictions.disjunction();
//		for (Region region : regions) {
//			Conjunction disjunctionRegion = Restrictions.conjunction();
//			disjunctionRegion.add(Restrictions.eq("chromosome", region.getChromosome())).add( Restrictions.ge("start", region.getStart())).add(Restrictions.le("end", region.getEnd()));
//			disjunction.add(disjunctionRegion);
//		}
//		criteria.add(disjunction);
//		return  execute(criteria);
	
	

}
