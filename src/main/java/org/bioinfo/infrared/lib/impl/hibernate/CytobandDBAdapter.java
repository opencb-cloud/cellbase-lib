package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.db.HibernateDBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class CytobandDBAdapter extends HibernateDBAdaptor {
	
	/** BY REGION **/
	@SuppressWarnings("unchecked")
	public List<Gene> getByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Cytoband.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return (List<Gene>)execute(criteria);
	}
	
	public List<List<Gene>> getByRegionList(List<Region> regions){
		List<List<Gene>> result = new ArrayList<List<Gene>>(regions.size());
		for (Region region : regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}

}
