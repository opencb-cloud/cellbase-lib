package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Xref;
import org.bioinfo.infrared.lib.api.TfbsDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

class TfbsHibernateDBAdaptor extends HibernateDBAdaptor implements TfbsDBAdaptor {


	public TfbsHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public TfbsHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@Override
	public List<? extends Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<Tfbs> getAllByTfGeneName(String id) {
		XRefsHibernateDBAdaptor xrefsAdaptor = new XRefsHibernateDBAdaptor(this.getSessionFactory());

		List<Xref> xrefs = xrefsAdaptor.getByDBNameList(id, Arrays.asList("ensembl_gene"));
		List<String> ensemblIds = new ArrayList<String>();
		for (Xref xref : xrefs) {
			ensemblIds.add(xref.getDisplayId());
		}

		if (ensemblIds.size() == 0){
			/** No he conseguido nada desde xRef asi que intento buscarlo con el External name **/
			String Hquery = "from Tfbs tf left join fetch tf.geneByTfGeneId p where tf.tfName = :key";
		    Query query = this.openSession().createQuery(Hquery);
		    query.setParameter("key", id);
			return query.list();
		}
		
		String Hquery = "from Tfbs tf left join fetch tf.geneByTfGeneId p where p.stableId in :keys";
		Query query = this.openSession().createQuery(Hquery);
		query.setParameterList("keys", ensemblIds);

		return query.list();//(List<Tfbs>) executeAndClose(query); ////
	}

	@Override
	public List<List<Tfbs>> getAllByTfGeneNameList(List<String> ids) {
		List<List<Tfbs>> results = new ArrayList<List<Tfbs>>();
		for (String id : ids) {
			results.add(this.getAllByTfGeneName(id));
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tfbs> getAllByTargetGeneName(String id) {
		XRefsHibernateDBAdaptor xrefsAdaptor = new XRefsHibernateDBAdaptor(this.getSessionFactory());
		List<Xref> xrefs = xrefsAdaptor.getByDBNameList(id, Arrays.asList("ensembl_gene"));
		List<String> ensemblIds = new ArrayList<String>();
		for (Xref xref : xrefs) {
			ensemblIds.add(xref.getDisplayId());
		}

		if (ensemblIds.size() > 0){
			Criteria criteria = this.openSession().createCriteria(Tfbs.class)
			.createCriteria("geneByTargetGeneId")
			.add(Restrictions.in("stableId", ensemblIds));
			return (List<Tfbs>) executeAndClose(criteria);
		}
		else{
			return new ArrayList<Tfbs>();
		}
	
	}

	@Override
	public List<List<Tfbs>> getAllByTargetGeneNameList(List<String> ids) {
		List<List<Tfbs>> results = new ArrayList<List<Tfbs>>();
		for (String id : ids) {
			results.add(this.getAllByTargetGeneName(id));
		}
		return results;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<Pwm> getAllPwmByTfGeneName(String geneName) {
		Criteria criteria = this.openSession().createCriteria(Pwm.class)
				.add(Restrictions.eq("tfName", geneName));
		List<Pwm> pwmList = (List<Pwm>) executeAndClose(criteria);
		
		if(pwmList == null || pwmList.size() == 0) {
			Query query = this.openSession().createQuery("select p from Gene g, Tfbs t, Pwm p where (g.stableId= :GENENAME or g.externalName= :GENENAME) and g.geneId=t.geneByTfGeneId and t.pwm=p.pwmId group by p.accession").setParameter("GENENAME", geneName);
			
//			Criterion stableId = Restrictions.eq("stableId", geneName.trim());
//			Criterion externalName = Restrictions.eq("externalName", geneName.trim());
//			LogicalExpression logExpression = Restrictions.or(stableId, externalName);
//			criteria = this.openSession().createCriteria(Pwm.class)
//					.createCriteria("tfbses")
////					.createCriteria("tfbs")
//					.createCriteria("geneByTfGeneId")
//					.add(logExpression);
			
			pwmList = (List<Pwm>) executeAndClose(query);
	
		}				
		return pwmList ;
	}

	public List<List<Pwm>> getAllPwmByTfGeneNameList(List<String> tfNames){
		List<List<Pwm>> result = new ArrayList<List<Pwm>>(tfNames.size());
		for (String tfName : tfNames) {
			result.add(this.getAllPwmByTfGeneName(tfName));
		}
		return result;
	}



	@Override
	public List<Tfbs> getAllByRegion(String chromosome) {
		return getAllByRegion(chromosome, 0, Integer.MAX_VALUE);
	}

	@Override
	public List<Tfbs> getAllByRegion(String chromosome, int start) {
		return getAllByRegion(chromosome, start, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tfbs> getAllByRegion(String chromosome, int start, int end) {

		String Hquery = "from Tfbs tf left join fetch tf.pwm p where tf.end >= :start and tf.start <= :end and tf.chromosome=:chromosome  group by tf.tfName, tf.cellType, tf.start, p.pwmId order by tf.start, tf.cellType asc";
		Query query = this.openSession().createQuery(Hquery);
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("chromosome", chromosome);

		return (List<Tfbs>) this.executeAndClose(query);
	}


	@Override
	public List<Tfbs> getAllByRegion(Region region) {
		return getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());
	}

	@Override
	public List<List<Tfbs>> getAllByRegionList(List<Region> regionList) {
		List<List<Tfbs>> result = new ArrayList<List<Tfbs>>();

		for(Region region: regionList) {
			result.add(this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd()));		 
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tfbs> getAllByInternalId(String id) {
		String Hquery = "from Tfbs tf left join fetch tf.pwm p left join fetch tf.geneByTargetGeneId g where tf.tfbsId=:id group by tf.tfName, tf.cellType, tf.start, p.pwmId order by tf.start, tf.cellType asc";
		Query query = this.openSession().createQuery(Hquery);
		query.setParameter("id", Integer.valueOf(id));
		return (List<Tfbs>) executeAndClose(query);
	}

	@Override
	public List<Tfbs> getAllByInternalIdList(List<String> idList) {
		List<Tfbs> result = new ArrayList<Tfbs>();
		for(String id: idList) {
			result.addAll(this.getAllByInternalId(id));
		}
		return result;

	}



	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getFullInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getInfoByIdList(List<String> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegionById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSequenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllAnnotation() {
		String query = "select target_gene_name, tf_name from tfbs t group by target_gene_name,tf_name ";
		SQLQuery querySQL = this.openSession().createSQLQuery(query);
		return (List<Object>) executeAndClose(querySQL);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllAnnotationByCellTypeList(List<String> cellTypes) {
		SQLQuery querySQL;
		if (cellTypes != null){
			String query = "select target_gene_name, tf_name from tfbs t where cell_type in :cellTypes group by target_gene_name,tf_name ";
			querySQL = this.openSession().createSQLQuery(query);
			querySQL.setParameterList("cellTypes", cellTypes);
		}
		else{
			String query = "select target_gene_name, tf_name from tfbs t group by target_gene_name,tf_name ";
			querySQL = this.openSession().createSQLQuery(query);
		}

		return (List<Object>) executeAndClose(querySQL);
	}

	
	@Override
	public List<IntervalFeatureFrequency> getAllTfIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (g.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from tfbs g where g.chromosome= '"+region.getChromosome()+"' and g.start <= "+region.getEnd()+" and g.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}


}
