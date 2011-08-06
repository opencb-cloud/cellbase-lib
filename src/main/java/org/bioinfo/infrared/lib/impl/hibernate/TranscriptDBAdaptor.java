package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.Transcript;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.db.HibernateDBAdaptor;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class TranscriptDBAdaptor extends HibernateDBAdaptor {
	
	/** BY ID **/
	public List<List<Transcript>> getByIdList(List<String> identifiers){
		ArrayList<List<Transcript>> result = new ArrayList<List<Transcript>>(identifiers.size());
		for (String id : identifiers) {
			result.add(getById(id));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transcript> getById(String id){
		criteria = this.getSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("stableId", id.trim()));
		return (List<Transcript>) execute(criteria);
	}
	
	
	/** BY REGION **/
	public List<List<Transcript>> getByRegionList(String chregionId){
		List<Region> regions = Region.parseRegions(chregionId);
		List<List<Transcript>> result = new ArrayList<List<Transcript>>(regions.size());
		for (Region region : regions) {
			result.add(getByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transcript> getByRegion(String chromosome, int start, int end){
		criteria =  this.getSession().createCriteria(Transcript.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add(Restrictions.ge("start", Math.min(start, end))).add(Restrictions.le("end", Math.max(start, end)));
		return  (List<Transcript>) execute(criteria);
	}
	
	
	/** BY GENE **/
	@SuppressWarnings("unchecked")
	public List<Transcript> getByGeneId(String geneId){
		criteria =  this.getSession().createCriteria(Transcript.class).setFetchMode("gene", FetchMode.SELECT)
		.createCriteria("gene").add(Restrictions.eq("stableId", geneId));
		return (List<Transcript>) execute(criteria);
	}
	
	public List<List<Transcript>> getByGeneIdList(List<String> geneIds) {
		List<List<Transcript>> result = new ArrayList<List<Transcript>>(geneIds.size());
		for (String id: geneIds) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
	
	
}
