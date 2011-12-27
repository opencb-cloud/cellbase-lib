package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.MirnaDisease;
import org.bioinfo.infrared.core.cellbase.MirnaTarget;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.coreold.regulatory.MiRnaTarget;
import org.bioinfo.infrared.lib.api.MirnaDBAdaptor;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

class MirnaHibernateDBAdaptor extends HibernateDBAdaptor implements MirnaDBAdaptor {

	public MirnaHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MiRnaTarget> getAllByMirbaseId(String id) {
		Criteria criteria = this.openSession().createCriteria(MirnaTarget.class)
		.add(Restrictions.eq("mirbaseId", id));
		return (List<MiRnaTarget>) executeAndClose(criteria);
	}

	@Override
	public List<List<MiRnaTarget>> getAllByMirbaseId(List<String> ids) {
		List<List<MiRnaTarget>> result = new ArrayList<List<MiRnaTarget>>();
		for (String id : ids) {
			result.add(this.getAllByMirbaseId(id));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MiRnaTarget> getAllByGeneNameList(String geneName) {
		GeneHibernateDBAdaptor adaptor = new GeneHibernateDBAdaptor(this.getSessionFactory());
		List<Gene> genes = adaptor.getAllByXref(geneName);
		
		List<String> ensemblId = new ArrayList<String>();
		for (Gene gene : genes) {
			ensemblId.add(gene.getStableId());
		}
		
		Criteria criteria = this.openSession().createCriteria(MirnaTarget.class)
		.createCriteria("gene")
		.add(Restrictions.in("stableId", ensemblId));
		
		return (List<MiRnaTarget>) executeAndClose(criteria);
	}

	@Override
	public List<List<MiRnaTarget>> getAllByGeneNameList(List<String> geneNames) {
		 List<List<MiRnaTarget>>  result = new ArrayList<List<MiRnaTarget>>();
		for (String string : geneNames) {
			result.add(this.getAllByGeneNameList(string));
		}
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MirnaDisease> getAllDiseasesByMirnaId(String mirbaseId) {
		Criteria criteria = this.openSession().createCriteria(MirnaDisease.class)
		.add(Restrictions.eq("mirbaseId", mirbaseId));
		return (List<MirnaDisease>) executeAndClose(criteria);
	}
	
	@Override
	public List<List<MirnaDisease>> getAllDiseasesByMirnaIdList(List<String> mirbaseId) {
		List<List<MirnaDisease>> result = new ArrayList<List<MirnaDisease>>();
		for (String	id : mirbaseId) {
			result.add(this.getAllDiseasesByMirnaId(id));
		}
		return result;
		
	}
	
	@Override
	public List<Object> getAnnotation() {
		return this.getAnnotation(null);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAnnotation(List<String> sources) {
		SQLQuery querySQL;
		if (sources != null){
			String query = "select gene_target_name, mirbase_id from mirna_target where source in :source group by gene_target_name, mirbase_id";
			querySQL = this.openSession().createSQLQuery(query);
			querySQL.setParameterList("source", sources);
		}
		else{
			String query = "select gene_target_name, mirbase_id from mirna_target group by gene_target_name, mirbase_id";
			querySQL = this.openSession().createSQLQuery(query);
		}
		
		return (List<Object>) executeAndClose(querySQL);
		
	}
	
	

}
