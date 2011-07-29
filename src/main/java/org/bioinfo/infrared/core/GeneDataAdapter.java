package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.dao.Region;
import org.bioinfo.infrared.db.HibernateDataAdapter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

public class GeneDataAdapter extends HibernateDataAdapter {
	
	@SuppressWarnings("unchecked")
	public List<List> getGeneByIds(String ids){
		List<String> identifiers = StringUtils.toList(ids, ",");
		ArrayList result = new ArrayList();
		for (String id : identifiers) {
			result.add(getGeneById(id));
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List getGeneById(String id){
		Criteria criteria = this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return  execute(criteria);
	}
	

	
	@SuppressWarnings("unchecked")
	public List getGeneByRegion(String chregionId){
		List<Region> regions = Region.parseRegions(chregionId);
		ArrayList result = new ArrayList();
		for (Region region : regions) {
			result.add(getGeneByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
		
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
	
	@SuppressWarnings("unchecked")
	public List getGeneByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Gene.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return  execute(criteria);
	}
}
