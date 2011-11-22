package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.core.cellbase.ExonToTranscript;
import org.bioinfo.infrared.core.cellbase.SnpToTranscript;
import org.bioinfo.infrared.lib.db.HibernateDBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;


@Deprecated
public class Exon2TranscriptDBAdapter extends HibernateDBAdaptor {
	
	/** By Gene **/
	@SuppressWarnings("unchecked")
	public List<SnpToTranscript> getByGeneId(String geneId){
		Query query = this.getSession().createQuery("select e from Exon2transcript e JOIN FETCH e.transcript t JOIN FETCH e.exon JOIN FETCH t.gene g where g.stableId in :stable_id").setParameterList("stable_id", StringUtils.toList(geneId, ","));
		return (List<SnpToTranscript>)execute(query);
	}
	
	
	public List<List<SnpToTranscript>> getByGeneIdList(List<String> geneIds) {
		List<List<SnpToTranscript>> result = new ArrayList<List<SnpToTranscript>>(geneIds.size());
		for (String id: geneIds) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
	/** By Transcript **/
	@SuppressWarnings("unchecked")
	public List<ExonToTranscript> getByTranscriptId(String transcriptId){
		Criteria criteria =  this.getSession().createCriteria(ExonToTranscript.class)
		.createCriteria("transcript").add( Restrictions.eq("stableId", transcriptId.trim()));
		return (List<ExonToTranscript>)execute(criteria);
	}
	
	public List<List<ExonToTranscript>> getByTranscriptIdList(List<String> transcripIds) {
		List<List<ExonToTranscript>> result = new ArrayList<List<ExonToTranscript>>(transcripIds.size());
		for (String id: transcripIds) {
			result.add(getByTranscriptId(id));
		}
		return result;
	}
}
