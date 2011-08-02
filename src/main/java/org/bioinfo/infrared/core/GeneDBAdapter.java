package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.common.dao.Region;
import org.bioinfo.infrared.db.HibernateDBAdapter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class GeneDBAdapter extends HibernateDBAdapter {
	
	/** BY ID **/
	public List<Gene> getGeneById(String id){
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return  execute(criteria);
	}
	
	public List<List<Gene>> getGeneByIdList(List<String> identifiers){
		List<List<Gene>> result = new ArrayList<List<Gene>>(identifiers.size());
		for (String id : identifiers) {
			result.add(getGeneById(id));
		}
		return result;
	}
	
	/** BY REGION **/
	public List<Gene> getGeneByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return  execute(criteria);
	}
	
	public List<List<Gene>> getGeneByRegion(String chregionId){
		List<Region> regions = Region.parseRegions(chregionId);
		List<List<Gene>> result = new ArrayList<List<Gene>>(regions.size());
		for (Region region : regions) {
			result.add(getGeneByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	
	
	/** BY TRANSCRIPT **/
	public List<Gene> getGeneByTranscript(String id){
		Criteria criteria = this.getSession().createCriteria(Gene.class)
		.createCriteria("transcripts")
		.add(Restrictions.eq("stableId", id.trim()));
		return  execute(criteria);
	}
	
	public List<List<Gene>> getGeneByTranscriptList(List<String> identifiers){
		List<List<Gene>> result = new ArrayList<List<Gene>>(identifiers.size());
		for (String id : identifiers) {
			result.add(getGeneByTranscript(id));
		}
		return result;
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
