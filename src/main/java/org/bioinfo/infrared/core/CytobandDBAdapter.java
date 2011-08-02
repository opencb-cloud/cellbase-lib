package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.common.dao.Region;
import org.bioinfo.infrared.db.HibernateDBAdapter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CytobandDBAdapter extends HibernateDBAdapter {
	
	/** BY REGION **/
	public List<Gene> getByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Cytoband.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return  execute(criteria);
	}
	
	public List<List<Gene>> getByRegionList(List<Region> regions){
		List<List<Gene>> result = new ArrayList<List<Gene>>(regions.size());
		for (Region region : regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}

}
