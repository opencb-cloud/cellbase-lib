package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.core.cellbase.Orthologous;
import org.bioinfo.infrared.lib.db.HibernateDBAdaptor;
import org.bioinfo.infrared.lib.db.HibernateDBConnector;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;


public class OrthologousDBAdaptor extends HibernateDBAdaptor {
	
	public OrthologousDBAdaptor(HibernateDBConnector hibernateDBConnector) {
		super(hibernateDBConnector);
	}

	public List<List<Orthologous>> getExonByIds(String ids){
		List<String> identifiers = StringUtils.toList(ids, ",");
		List<List<Orthologous>> result = new ArrayList<List<Orthologous>>(identifiers.size());
		for (String id : identifiers) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Orthologous> getByGeneId(String geneId){
		Criteria criteria =  this.getSession().createCriteria(Orthologous.class)
		.createCriteria("gene").add( Restrictions.eq("stableId", geneId)).setFetchMode("gene", FetchMode.DEFAULT);
		return (List<Orthologous>) execute(criteria);
	}
	
	
	public List<List<Orthologous>> getByGeneIdList(List<String> geneIds) {
		List<List<Orthologous>> result = new ArrayList<List<Orthologous>>(geneIds.size());
		for (String id: geneIds) {
			result.add(getByGeneId(id));
		}
		return result;
	}
	
}
