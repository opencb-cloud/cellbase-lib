package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.core.cellbase.Xref;
import org.bioinfo.infrared.lib.api.TfbsDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

class TfbsHibernateDBAdaptor extends HibernateDBAdaptor implements TfbsDBAdaptor {


	public TfbsHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public TfbsHibernateDBAdaptor(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	/** ALL IDs **/
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

	/** TF INFO **/
	@Override
	public List<Protein> getTfInfoByTfGeneName(String tfGeneName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<List<Protein>> getTfInfoByTfGeneNameList(
			List<String> tfGeneNameList) {
		// TODO Auto-generated method stub
		return null;
	}


	/** GET TFBSs **/
	@SuppressWarnings("unchecked")
	@Override
	public List<Tfbs> getAllByTfGeneName(String id, String cellType, int start, int end) {
		XRefsHibernateDBAdaptor xrefsAdaptor = new XRefsHibernateDBAdaptor(this.getSessionFactory());
		// Search for the id in XREF
		long t0 = System.currentTimeMillis();
		List<Xref> xrefs = xrefsAdaptor.getByDBNameList(id, Arrays.asList("ensembl_gene"));
		List<String> ensemblIds = new ArrayList<String>();
		for (Xref xref : xrefs) {
			ensemblIds.add(xref.getDisplayId());
		}
		System.out.println("\t******************* XREF: "+(System.currentTimeMillis()-t0));
		Query query = null;
		
		
//		t0 = System.currentTimeMillis();
//		Criteria criteria = this.openSession().createCriteria(Tfbs.class, "t").setFetchMode("gene", FetchMode.JOIN).setFetchMode("transcript", FetchMode.JOIN);
////				.createCriteria("gene")
////				.add(Restrictions.in("stableId", ensemblIds));
//			
//		if(ensemblIds.size() > 0) {
//			criteria.createCriteria("gene")
//			.add(Restrictions.in("stableId", ensemblIds));
//		}else {
//			criteria.createCriteria("gene")
//			.add(Restrictions.eq("stableId", id));
//		}
//			
//		if(cellType != null) {
//			criteria.add(Restrictions.eq("t.celltype", cellType));
//		}
//		
//		if (start != Integer.MIN_VALUE && end != Integer.MIN_VALUE) {
//			criteria.add(Restrictions.ge("t.relativeStart", start));
//			criteria.add(Restrictions.le("t.relativeEnd", end));
//		}
		System.out.println("\t******************* TFBS: "+(System.currentTimeMillis()-t0));
//		return (List<Tfbs>) executeAndClose(criteria);	
		
		
		String Hquery = "from Tfbs tf left join fetch tf.gene p where p.stableId in :keys";
		// If we DON'T have found anything in XREF we search for the TF Ensembl name at the TFBS table
		if (ensemblIds.size() == 0){
			Hquery = "from Tfbs tf left join fetch tf.gene p where tf.tfName = :keys";
		}

		if (cellType != null) {
			Hquery += " and tf.cellType = :ct";
		}
		
		if (start != Integer.MIN_VALUE && end != Integer.MIN_VALUE) {
			Hquery += " and tf.relativeStart >= :start and tf.relativeEnd <= :end";
		}
		
		query = this.openSession().createQuery(Hquery);
		if(ensemblIds.size() > 0) {
			query.setParameterList("keys", ensemblIds);			
		}else {
			query.setParameter("keys", id);
		}

		if (cellType != null) {
			query.setParameter("ct", cellType);				
		}
		
		if (start != Integer.MIN_VALUE && end != Integer.MIN_VALUE) {
			query.setParameter("start", start);
			query.setParameter("end", end);
		}
		
		return (List<Tfbs>) executeAndClose(query);			
	}

	@Override
	public List<List<Tfbs>> getAllByTfGeneNameList(List<String> ids, String cellType, int start, int end) {
		List<List<Tfbs>> results = null;
		if(ids != null) {
			results = new ArrayList<List<Tfbs>>(ids.size());
			for (String tf : ids) {
				results.add(this.getAllByTfGeneName(tf, cellType, start, end));
			}
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
//			Query query = this.openSession().createQuery("select t from Tfbs t, Transcript tr, Gene g where t.transcript=tr.transcriptId and tr.gene=g.geneId and g.stableId = :GENELIST").setParameter("GENELIST", "ENSG00000200051");
			Criteria criteria = this.openSession().createCriteria(Tfbs.class)
				.createCriteria("transcript")
				.createCriteria("gene")
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
//		String Hquery = "from Tfbs tf left join fetch tf.pwm p where tf.end >= :start and tf.start <= :end and tf.chromosome=:chromosome  group by tf.tfName, tf.cellType, tf.start, p.pwmId order by tf.start, tf.cellType asc";
		String Hquery = "from Tfbs tf left join fetch tf.pwm p where tf.end >= :start and tf.start <= :end and tf.chromosome=:chromosome order by tf.start, tf.cellType asc";
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
//		String Hquery = "from Tfbs tf left join fetch tf.pwm p left join fetch tf.geneByTargetGeneId g where tf.tfbsId=:id group by tf.tfName, tf.cellType, tf.start, p.pwmId order by tf.start, tf.cellType asc";
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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<IntervalFeatureFrequency> getAllTfIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (g.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from tfbs g where g.chromosome= '"+region.getChromosome()+"' and g.start <= "+region.getEnd()+" and g.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList);
		return intervalFreqsList;
	}

	
}
