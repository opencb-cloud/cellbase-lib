package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.core.Exon2transcript;
import org.bioinfo.infrared.core.Snp2transcript;
import org.bioinfo.infrared.lib.db.HibernateDBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;


public class Exon2TranscriptDBAdapter extends HibernateDBAdaptor {
	
	/** By Gene **/
	@SuppressWarnings("unchecked")
	public List<Snp2transcript> getByGeneId(String geneId){
		Query query = this.getSession().createQuery("select e from Exon2transcript e JOIN FETCH e.transcript t JOIN FETCH e.exon JOIN FETCH t.gene g where g.stableId in :stable_id").setParameterList("stable_id", StringUtils.toList(geneId, ","));
		return (List<Snp2transcript>)execute(query);
	}
	
	
	public List<List<Snp2transcript>> getByGeneIdList(List<String> geneIds) {
		List<List<Snp2transcript>> result = new ArrayList<List<Snp2transcript>>(geneIds.size());
		for (String id: geneIds) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
	/** By Transcript **/
	@SuppressWarnings("unchecked")
	public List<Exon2transcript> getByTranscriptId(String transcriptId){
		Criteria criteria =  this.getSession().createCriteria(Exon2transcript.class)
		.createCriteria("transcript").add( Restrictions.eq("stableId", transcriptId.trim()));
		return (List<Exon2transcript>)execute(criteria);
	}
	
	public List<List<Exon2transcript>> getByTranscriptIdList(List<String> transcripIds) {
		List<List<Exon2transcript>> result = new ArrayList<List<Exon2transcript>>(transcripIds.size());
		for (String id: transcripIds) {
			result.add(getByTranscriptId(id));
		}
		return result;
	}
}
