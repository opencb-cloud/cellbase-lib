package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.Exon;
import org.bioinfo.infrared.lib.common.Region;
import org.bioinfo.infrared.lib.db.HibernateDBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;


public class ExonHIbernateDBAdaptor extends HibernateDBAdaptor {
	
	/** BY GENE **/
	@SuppressWarnings("unchecked")
	public List<Exon> getByGeneId(String geneId){
		Criteria criteria =  this.getSession().createCriteria(Exon.class).setFetchMode("exon2transcripts", FetchMode.SELECT)
		.createCriteria("exon2transcripts").setFetchMode("transcript", FetchMode.SELECT)
		.createCriteria("transcript").setFetchMode("gene", FetchMode.SELECT)
		.createCriteria("gene").add( Restrictions.eq("stableId", geneId.trim()));
		return (List<Exon>)execute(criteria);
	}
	
	
	public List<List<Exon>> getByGeneIdList(List<String> geneIds) {
		List<List<Exon>> result = new ArrayList<List<Exon>>(geneIds.size());
		for (String id: geneIds) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
	/** BY TRANSCRIPT **/
	@SuppressWarnings("unchecked")
	public List<Exon> getBytranscriptId(String transcriptId){
		Criteria criteria =  this.getSession().createCriteria(Exon.class)
		.createCriteria("exon2transcripts")
		.createCriteria("transcript").add( Restrictions.eq("stableId", transcriptId.trim()));
		return (List<Exon>)execute(criteria);
	}
	
	public List<List<Exon>> getByTranscriptIdList(List<String> transcriptIds) {
		List<List<Exon>> result = new ArrayList<List<Exon>>(transcriptIds.size());
		for (String id: transcriptIds) {
			result.add(getBytranscriptId(id));
		}
		return result;
	}
	
	/** BY REGION **/
	@SuppressWarnings("unchecked")
	public List<Exon> getExonByRegion(String chromosome, int start, int end){
		Criteria criteria =  this.getSession().createCriteria(Exon.class);
		criteria.add(Restrictions.eq("chromosome", chromosome)).add( Restrictions.ge("start", start)).add(Restrictions.le("end", end));
		return  (List<Exon>)execute(criteria);
	}
	
	public List<List<Exon>> getExonByRegionList(String chregionId){
		List<Region> regions = Region.parseRegions(chregionId);
		List<List<Exon>> result = new ArrayList<List<Exon>>(regions.size());
		for (Region region : regions) {
			result.add(getExonByRegion(region.getChromosome(), region.getStart(), region.getEnd()));
		}
		return result;
	}
}
