package org.bioinfo.infrared.lib.impl.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.Metainfo;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.SnpToTranscript;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

class SnpHibernateDBAdapator extends HibernateDBAdaptor implements SnpDBAdaptor {

	private int MAX_BATCH_QUERIES_LIST = 50;
	//	private int FEATURE_MAP_CHUNK_SIZE = 400;


	public SnpHibernateDBAdapator(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public SnpHibernateDBAdapator(SessionFactory sessionFactory, String species, String version) {
		super(sessionFactory, species, version);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAll(){
		Criteria criteria = this.openSession().createCriteria(Snp.class);
		return (List<Snp>) this.executeAndClose(criteria);
	}

	//	@Override
	//	public List<Snp> getAllByDbSnpId(String id){
	//		Session session = this.openSession();
	//		List<Snp> snps = this.getByDbSnpId(id, session);
	//		session.close();
	//		return snps;
	//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllBySnpId(String name) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.add(Restrictions.eq("name", name));
		return (List<Snp>) executeAndClose(criteria);
	}

	@Override
	public List<List<Snp>> getAllBySnpIdList(List<String> idList){
		List<List<Snp>> result = new ArrayList<List<Snp>>(idList.size());
		for(String id: idList) {
			result.add(this.getAllBySnpId(id));
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Snp> getAllByGeneId(String geneId) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.createCriteria("snpToTranscripts")
				.createCriteria("transcript")
				.createCriteria("transcriptToXrefs")
				.createCriteria("xref")
				.add(Restrictions.eq("displayId", geneId));

		//	GeneHibernateDBAdaptor geneHibernateDBAdaptor = new GeneHibernateDBAdaptor(this.getSessionFactory());
		//	Gene gene = geneHibernateDBAdaptor.getByEnsemblId(ensemblId);
		//	return this.getAllByRegion(gene.getChromosome(), gene.getStart(), gene.getEnd());
		return (List<Snp>) executeAndClose(criteria);
	}

	@Override
	public List<List<Snp>> getAllByGeneIdList(List<String> geneIds) {
		List<List<Snp>> result = new ArrayList<List<Snp>>(geneIds.size());
		for(String id: geneIds) {
			result.add(this.getAllByGeneId(id));
		}
		return result;
		//	GeneHibernateDBAdaptor geneHibernateDBAdaptor = new GeneHibernateDBAdaptor(this.getSessionFactory());
		//	List<Gene> genes = geneHibernateDBAdaptor.getAllByEnsemblIdList(ensemblIds);
		//	List<List<Snp>> result = new ArrayList<List<Snp>>();
		//	for (Gene gene : genes) {
		//		result.add(this.getAllByGeneId(gene.getStableId()));
		//	}
		//	return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByEnsemblGeneId(String ensemblGeneId) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.createCriteria("snpToTranscripts")
				.createCriteria("transcript")
				.createCriteria("gene")
				.add(Restrictions.eq("stableId", ensemblGeneId));
		return (List<Snp>) executeAndClose(criteria);
	}

	@Override
	public List<List<Snp>> getAllByEnsemblGeneIdList(List<String> ensemblGeneList) {
		List<List<Snp>> result = new ArrayList<List<Snp>>(ensemblGeneList.size());
		for(String id: ensemblGeneList) {
			result.add(this.getAllByEnsemblGeneId(id));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByEnsemblTranscriptId(String ensemblTranscriptId) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.createCriteria("snpToTranscripts")
				.createCriteria("transcript")
				.add(Restrictions.eq("stableId", ensemblTranscriptId));
		return (List<Snp>) executeAndClose(criteria);
	}

	@Override
	public List<List<Snp>> getAllByEnsemblTranscriptIdList(List<String> ensemblTranscriptList) {
		List<List<Snp>> result = new ArrayList<List<Snp>>(ensemblTranscriptList.size());
		for(String id: ensemblTranscriptList) {
			result.add(this.getAllByEnsemblTranscriptId(id));
		}
		return result;
	}	




	@SuppressWarnings("unchecked")
	@Override
	public List<ConsequenceType> getAllConsequenceTypes() {
		Criteria criteria = this.openSession().createCriteria(ConsequenceType.class);
		return (List<ConsequenceType>) executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ConsequenceType> getAllConsequenceTypesBySnpId(String snpId) {
		Criteria criteria = this.openSession().createCriteria(ConsequenceType.class)
				.createCriteria("snpToTranscripts")
				.createCriteria("snp")
				.add(Restrictions.eq("name", snpId));
		return (List<ConsequenceType>) executeAndClose(criteria);
	}

	@Override
	public List<SnpToTranscript> getAllSnpToTranscriptsBySnpId(String snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnpToTranscript> getAllSnpToTranscriptsByTranscriptId(String transcriptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<ConsequenceType>> getAllConsequenceTypesBySnpIdList(List<String> snpId) {
		// TODO Auto-generated method stub
		return null;
	}

	//XXX
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllIdsBySOConsequenceType(String consequenceType) {
		Query query = this.openSession().createQuery("select s.name from Snp s where s.displaySoConsequence= :CONSQ_TYPE").setParameter("CONSQ_TYPE", consequenceType).setMaxResults(500000);
		return (List<String>) executeAndClose(query);
	}

	//XXX
	@Override
	public List<List<String>> getAllIdsBySOConsequenceTypeList(List<String> consequenceTypeList) {
		List<List<String>> results = new ArrayList<List<String>>(consequenceTypeList.size());
		for (String consequenceType : consequenceTypeList) {
			List<String> idsList = this.getAllIdsBySOConsequenceType(consequenceType);
			results.add(idsList);
		}
		return results;
	}

	//XXX
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllIdsByRegion(String chromosome, int start, int end) {

		//		int chunk_size = applicationProperties.getIntProperty("CHUNK_SIZE", 400);
		//		int start_chunk = start / chunk_size;
		//		int end_chunk = end / chunk_size;
		//		Query query = this.openSession().createQuery("select distinct fm.featureName from FeatureMap fm where fm.featureType='snp' and fm.chromosome= :CHROMOSOME and fm.chunkId >= :START and fm.chunkId <= :END")
		//										.setParameter("CHROMOSOME", chromosome)
		//										.setParameter("START", start_chunk)
		//										.setParameter("END", end_chunk);
		/*
		 * Accessing to snp table gives an 6x of speed up when compared to FeatureMap
		 */
		Query query = this.openSession().createQuery("select distinct(s.name) from Snp s where s.chromosome= :CHROMOSOME and s.start >= :START and s.end <= :END")
				.setParameter("CHROMOSOME", chromosome)
				.setParameter("START", start)
				.setParameter("END", end);

		return (List<String>) executeAndClose(query);

	}




	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllBySOConsequenceType(String consequenceType) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.add(Restrictions.eq("displaySoConsequence", consequenceType.trim())).setMaxResults(200000);
		return (List<Snp>)executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllBySOConsequenceTypeList(List<String> consequenceTypeList) {
		Criteria criteria = this.openSession().createCriteria(Snp.class).setCacheable(true).setFetchMode("SnpToTranscript", FetchMode.JOIN);
		for(String consequenceType : consequenceTypeList) {
			criteria.add(Restrictions.disjunction().add(Restrictions.eq("displaySoConsequence", consequenceType)));
		}
		return (List<Snp>)executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByEnsemblConsequenceType(String ensemblConsequenceType) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.add(Restrictions.eq("displayConsequence", ensemblConsequenceType.trim()));
		return (List<Snp>)executeAndClose(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByEnsemblConsequenceTypeList(List<String> ensemblConsequenceTypeList) {
		Criteria criteria = this.openSession().createCriteria(Snp.class).setCacheable(true).setFetchMode("SnpToTranscript", FetchMode.JOIN);
		for(String consequenceType : ensemblConsequenceTypeList) {
			criteria.add(Restrictions.disjunction().add(Restrictions.eq("displayConsequence", consequenceType)));
		}
		return (List<Snp>)executeAndClose(criteria);
	}




	@Override
	public List<Snp> getAllByPosition(String chromosome, int position) {
		return this.getAllByRegion(new Region(chromosome, position, position));
	}

	@Override
	public List<Snp> getAllByPosition(Position position) {
		return this.getAllByRegion(position.getChromosome(), position.getPosition(), position.getPosition());
	}

	@Override
	public List<List<Snp>> getAllByPositionList(List<Position> positionList) {
		List<List<Snp>> results = new ArrayList<List<Snp>>(positionList.size());
		for (Position position : positionList) {
			results.add(this.getAllByPosition(position));
		}
		return results;
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByRegion(String chromosome) {
		Criteria criteria = this.openSession().createCriteria(Snp.class)
				.add(Restrictions.eq("chromosome", chromosome));
		return (List<Snp>) this.executeAndClose(criteria);
	}


	@Override
	public List<Snp> getAllByRegion(String chromosome, int start) {
		return this.getAllByRegion(chromosome, start, Integer.MAX_VALUE);
	}

	@Override
	public List<Snp> getAllByRegion(String chromosome, int start, int end) {
		GenomicRegionFeatureDBAdaptor genomicRegionFeatureDBAdaptor = new GenomicRegionFeatureHibernateDBAdaptor(this.getSessionFactory());
		GenomicRegionFeatures genomicRegionFeatures = genomicRegionFeatureDBAdaptor.getByRegion(new Region(chromosome, start, end), Arrays.asList("snp"));
		return genomicRegionFeatures.getSnp();
	}

	//XXX
	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByRegion(String chromosome, int start, int end, List<String> consequenceTypeList) {
		Criteria criteria =  this.openSession().createCriteria(Snp.class);
		criteria.add(Restrictions.eq("chromosome", chromosome))
		.add(Restrictions.ge("end", start))
		.add(Restrictions.le("start", end))
		.add(Restrictions.in("displaySoConsequence", consequenceTypeList))
		.addOrder(Order.asc("chromosome"))
		.addOrder(Order.asc("start"));

		return (List<Snp>) executeAndClose(criteria);
	}

	@Override
	public List<Snp> getAllByRegion(Region region) {
		return this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd());
	}

	@Override
	public List<Snp> getAllByRegion(Region region, List<String> consequenceTypeList) {
		return this.getAllByRegion(region.getChromosome(), region.getStart(), region.getEnd(), consequenceTypeList);
	}

	@Override
	public List<List<Snp>> getAllByRegionList(List<Region> regionList) {
		List<List<Snp>> results = new ArrayList<List<Snp>>();
		for (Region region : regionList) {
			results.add(this.getAllByRegion(region));
		}
		return results;
	}

	@Override
	public List<List<Snp>> getAllByRegionList(List<Region> regionList, List<String> consequenceTypeList) {
		return null;
	}

	@Override
	public List<Snp> getAllByCytoband(String chromosome, String cytoband) {
		// TODO Auto-generated method stub
		return null;
	}



	//XXX
	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, String consequence) {
		Criteria criteria =  this.openSession().createCriteria(Snp.class);
		criteria.add(Restrictions.in("name", snpIds))
		.add(Restrictions.eq("displaySoConsequence", consequence))
		.addOrder(Order.asc("name"));

		return (List<Snp>) executeAndClose(criteria);
	}

	//XXX
	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, List<String> consequenceTypes) {
		Criteria criteria =  this.openSession().createCriteria(Snp.class);
		criteria.add(Restrictions.in("name", snpIds))
		.add(Restrictions.in("displaySoConsequence", consequenceTypes))
		.addOrder(Order.asc("name"));

		return (List<Snp>) executeAndClose(criteria);
	}

	@Override
	public void writeAllFilteredByConsequenceType(String consequence,String outfile) {
		// TODO Auto-generated method stub

	}



	//XXX
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllIds() {
		Query query = this.openSession().createQuery("select s.name from Snp s").setMaxResults(500000);
		return (List<String>) executeAndClose(query);
	}

	//XXX
	@Override
	public List<Region> getAllRegionsByIdList(List<String> idList) {
		List<Region> results = new ArrayList<Region>();
		for (String id : idList) {
			results.add(this.getRegionById(id));
		}
		return results;
	}

	//XXX
	@Override
	public List<String> getAllSequencesByIdList(List<String> idList) {
		List<String> results = new ArrayList<String>();
		for (String id : idList) {
			results.add(this.getSequenceById(id));
		}
		return results;
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

	//XXX
	@Override
	public Region getRegionById(String id) {
		List<Snp> snp =  this.getAllBySnpId(id);
		return new Region(snp.get(0).getChromosome(), snp.get(0).getStart(), snp.get(0).getEnd());
	}

	//XXX
	@Override
	public String getSequenceById(String id) {
		Query query = this.openSession().createQuery("select sequence from Snp snp where snp.name= :SNPID")
				.setParameter("SNPID", id);
		return executeAndClose(query).toString();
	}

	@SuppressWarnings("unchecked")
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval) {
		SQLQuery sqlquery = this.openSession().createSQLQuery("select (cr.start - "+region.getStart()+") DIV "+interval+" as inter, count(*) from snp cr where cr.chromosome= '"+region.getChromosome()+"' and cr.start <= "+region.getEnd()+" and cr.end >= "+region.getStart()+" group by inter");
		List<Object[]> objectList =  (List<Object[]>) executeAndClose(sqlquery);

		int CHUNK_SIZE = 50;
		if(interval > 10000) {
			CHUNK_SIZE = 200;
		}
		interval = (interval / CHUNK_SIZE) * CHUNK_SIZE;
		interval = Math.max(interval, 200);

		int numSnps = -1;
		long t1 = System.currentTimeMillis();
		
		String value = getDatabaseQueryCache(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase());
		if(value == null || value.equals("")) {
			sqlquery = this.openSession().createSQLQuery("select count(*) from snp where chromosome= '"+region.getChromosome()+"' ");
			List<BigInteger> integerList =  (List<BigInteger>) executeAndClose(sqlquery);
			putDatabaseQueryCache(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase(), ""+integerList.get(0).intValue());
			value = ""+integerList.get(0).intValue();
		}
		numSnps = Integer.parseInt(value);
		System.out.println("num snps db cached: "+numSnps+", species: "+species+", time: "+(System.currentTimeMillis()-t1));

		t1 = System.currentTimeMillis();
		double maxSnpsInterval = 1;
		value = getDatabaseQueryCache(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase()+".INTERVAL."+interval);
		if(value == null || value.equals("")) {
			sqlquery = this.openSession().createSQLQuery("select (cr.start - 1) DIV "+interval+" as inter, LOG(count(*)) as t from snp cr where cr.chromosome= '"+region.getChromosome()+"' and cr.start <= "+Integer.MAX_VALUE+" and cr.end >= 1 group by inter order by t DESC limit 1 ");
			List<Object[]> integerList =  (List<Object[]>) executeAndClose(sqlquery);
			if(integerList != null && integerList.size() > 0) {
				System.out.println(">>Cached: "+integerList.get(0)[1]+", interval: "+interval);
				maxSnpsInterval = ((Double)integerList.get(0)[1]).doubleValue();				
			}
			putDatabaseQueryCache(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase()+".INTERVAL."+interval, ""+maxSnpsInterval);
			value = ""+maxSnpsInterval;
		}
		maxSnpsInterval = Double.parseDouble(value);
		System.out.println("max num snps db cached per interval: "+maxSnpsInterval+", species: "+species+", time: "+(System.currentTimeMillis()-t1));
		
//		if(!cachedQuerySizes.containsKey(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase())) {
//			sqlquery = this.openSession().createSQLQuery("select count(*) from snp where chromosome= '"+region.getChromosome()+"' ");
//			List<BigInteger> integerList =  (List<BigInteger>) executeAndClose(sqlquery);
//			cachedQuerySizes.put(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase(), integerList.get(0).intValue());
//		}
//		numSnps = (Integer) cachedQuerySizes.get(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase());
//		System.out.println("num snps: "+numSnps+", time: "+(System.currentTimeMillis()-t1));

//		t1 = System.currentTimeMillis();
//		double maxSnpsInterval = 1;
//		if(!cachedQuerySizes.containsKey(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase()+".INTERVAL."+interval)) {
//			sqlquery = this.openSession().createSQLQuery("select (cr.start - 1) DIV "+interval+" as inter, LOG(count(*)) as t from snp cr where cr.chromosome= '"+region.getChromosome()+"' and cr.start <= "+Integer.MAX_VALUE+" and cr.end >= 1 group by inter order by t DESC limit 1 ");
//			List<Object[]> integerList =  (List<Object[]>) executeAndClose(sqlquery);
//			if(integerList != null && integerList.size() > 0) {
//				System.out.println(">>Cached: "+integerList.get(0)[1]+", interval: "+interval);
//				maxSnpsInterval = ((Double)integerList.get(0)[1]).doubleValue();				
//			}
//			cachedQuerySizes.put(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase()+".INTERVAL."+interval, maxSnpsInterval);
//		}
//		maxSnpsInterval  =(Double) cachedQuerySizes.get(species.toUpperCase()+".NUM.SNP.CHR."+region.getChromosome().toUpperCase()+".INTERVAL."+interval);
//		System.out.println("max num snps per interval: "+maxSnpsInterval+", time: "+(System.currentTimeMillis()-t1));
		
		List<IntervalFeatureFrequency> intervalFreqsList = getIntervalFeatureFrequencies(region , interval, objectList, numSnps, maxSnpsInterval);//, numSnps, maxSnpsInterval);
		return intervalFreqsList;
	}


	@Deprecated
	private List<List<Snp>> Reorder(List<String> idList){
		String query = "select snp from Snp as snp left join fetch snp.snpToTranscripts as stt  left join fetch snp.snpXrefs as sxr  left join fetch snp.snp2functionals as s2f left join fetch stt.consequenceType as consequenceType where snp.name in :name";
		List<Snp> result = query(query, idList);
		List<List<Snp>> cleanResult = new ArrayList<List<Snp>>();
		if(result.size() != idList.size()) {
			String resultId = new String(); 
			String prevResultId = new String();
			for(int i=0,j=0; i<idList.size();) {
				if (j < result.size()){
					resultId = result.get(j).getName();
					if (resultId.equals(prevResultId)){
						// REPETIDO 
						cleanResult.get(cleanResult.size() -1).add(result.get(j));
						prevResultId = resultId;
						j++;
					}
					else{
						if( idList.get(i).equals(result.get(j).getName())) {
							List<Snp> list = new ArrayList<Snp>();
							list.add(result.get(j));
							cleanResult.add(list);
							prevResultId = resultId;
							i++;
							j++;
						}else{
							cleanResult.add(null);
							i++;
						}
					}
				}
				else{
					cleanResult.add(null);
					i++;
				}
			}	
		}
		System.out.println("cleanResult " + cleanResult.size());
		return cleanResult;
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	private List<Snp> query(String queryHQL, List<String> idList){
		Session session = this.openSession();
		Query query = session.createQuery(queryHQL);

		List<Snp> result = new ArrayList<Snp>();
		if (idList.size() > MAX_BATCH_QUERIES_LIST){
			for (int i = 0; i < (idList.size()/MAX_BATCH_QUERIES_LIST); i++) {
				int start = (i * MAX_BATCH_QUERIES_LIST );
				int end = start + MAX_BATCH_QUERIES_LIST;

				query.setParameterList("name", idList.subList(start, end));
				result.addAll((Collection<? extends Snp>) this.execute(query));

			}

			if ( (idList.size() % MAX_BATCH_QUERIES_LIST) != 0){
				int start = ( idList.size() /MAX_BATCH_QUERIES_LIST) * MAX_BATCH_QUERIES_LIST;
				int end = idList.size();

				query.setParameterList("name", idList.subList(start, end));
				result.addAll((Collection<? extends Snp>) this.execute(query));
			}
		}
		else{
			query.setParameterList("name", idList);
			result.addAll((Collection<? extends Snp>) this.execute(query));
		}
		closeSession();
		return result;
	}

}
