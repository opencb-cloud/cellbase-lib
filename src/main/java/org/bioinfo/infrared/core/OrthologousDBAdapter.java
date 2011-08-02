package org.bioinfo.infrared.core;

import java.util.ArrayList;
import java.util.List;
import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.db.HibernateDBAdapter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;


public class OrthologousDBAdapter extends HibernateDBAdapter {
	
	public List<List<Orthologous>> getExonByIds(String ids){
		List<String> identifiers = StringUtils.toList(ids, ",");
		ArrayList result = new ArrayList(identifiers.size());
		for (String id : identifiers) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
	public List<Orthologous> getByGeneId(String geneId){
		Criteria criteria =  this.getSession().createCriteria(Orthologous.class)
		.createCriteria("gene").add( Restrictions.eq("stableId", geneId)).setFetchMode("gene", FetchMode.DEFAULT);
		return execute(criteria);
	}
	
	
	public List<List<Orthologous>> getByGeneIdList(List<String> geneIds) {
		List<List<Orthologous>> result = new ArrayList<List<Orthologous>>(geneIds.size());
		for (String id: geneIds) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
}
