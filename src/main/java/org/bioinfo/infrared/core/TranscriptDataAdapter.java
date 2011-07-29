package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.dao.Region;
import org.bioinfo.infrared.db.HibernateDataAdapter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class TranscriptDataAdapter extends HibernateDataAdapter {
	
	@SuppressWarnings("unchecked")
	public List<List> getByIds(String ids){
		List<String> identifiers = StringUtils.toList(ids, ",");
		ArrayList result = new ArrayList();
		for (String id : identifiers) {
			result.add(getById(id));
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List getById(String id){
		Criteria criteria = this.getSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return  execute(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List getByRegion(String chregionId){
		List<Region> regions = Region.parseRegions(chregionId);
		ArrayList result = new ArrayList();
		for (Region region : regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List getByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return  execute(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List getByGene(String geneId){
		System.out.println("estoy buscando por: " + geneId);
		Criteria criteria =  this.getSession().createCriteria(Transcript.class).setFetchMode("gene", FetchMode.SELECT)
		.createCriteria("gene").add( Restrictions.eq("stableId", geneId));
		return  execute(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List getByGenes(String geneIds){
		System.out.println("estoy buscando por:2 " + geneIds);
		String[] ids = geneIds.split(",");
		ArrayList<String> result = new ArrayList<String>();
		for (String id : ids) {
			result.add(getByGene(id));
		}
		return  result;
	}
	
	
	
}
