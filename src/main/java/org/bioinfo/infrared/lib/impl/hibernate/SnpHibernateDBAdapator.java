package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.FeatureMap;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.common.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class SnpHibernateDBAdapator extends HibernateDBAdaptor implements SnpDBAdaptor {
	private int MAX_BATCH_QUERIES_LIST = 50;
	private int FEATURE_MAP_CHUNK_SIZE = 400;
	
	public SnpHibernateDBAdapator(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAll(){
		Criteria criteria = this.openSession().createCriteria(Snp.class);
		return (List<Snp>) this.executeAndClose(criteria);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Snp> getByDbSnpId(String id){
		Session session = this.openSession();
		List<Snp> snps = this.getByDbSnpId(id, session);
		session.close();
		return snps;
	}
	
	@SuppressWarnings("unchecked")
	private List<Snp> getByDbSnpId(String name, Session session){
		Criteria criteria = session.createCriteria(Snp.class)
		.add(Restrictions.eq("name", name));
		return (List<Snp>)criteria.list();
	}
	
	@Override
	public List<List<Snp>> getByDbSnpIdList(List<String> idList){
		Session session = this.openSession();
		 List<List<Snp>> result = new ArrayList<List<Snp>>();
		for (String id : idList) {
			result.add(this.getByDbSnpId(id,session));
		}
		session.close();
		return result;
	}
	


	@Override
	public List<Snp> getAllByGeneId(String ensemblId) {
		GeneHibernateDBAdaptor geneHibernateDBAdaptor = new GeneHibernateDBAdaptor(this.getSessionFactory());
		Gene gene = geneHibernateDBAdaptor.getByEnsemblId(ensemblId);
		return this.getAllByRegion(gene.getChromosome(), gene.getStart(), gene.getEnd());
	}


	@Override
	public List<List<Snp>> getAllByGeneIdList(List<String> ensemblIds) {
		GeneHibernateDBAdaptor geneHibernateDBAdaptor = new GeneHibernateDBAdaptor(this.getSessionFactory());
		List<Gene> genes = geneHibernateDBAdaptor.getAllByEnsemblIdList(ensemblIds);
		List<List<Snp>> result = new ArrayList<List<Snp>>();
		for (Gene gene : genes) {
			result.add(this.getAllByGeneId(gene.getStableId()));
		}
		return result;
	}
	
	@Override
	public List<Snp> getAllByEnsemblGeneId(String externalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Snp>> getAllByEnsemblGeneList(List<String> externalIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/****/
	@Override
	public List<Snp> getAllByEnsemblTranscriptId(String transcriptId) {
		Session session =  this.openSession();
		List<Snp> snps = this.getAllByEnsemblTranscriptId(transcriptId,session);
		session.close();
		return snps;
	}
	@SuppressWarnings("unchecked")
	private List<Snp> getAllByEnsemblTranscriptId(String transcriptId, Session session) {		
		Criteria criteria = session
				.createCriteria(Snp.class)
				.createCriteria("snpToTranscripts")
				.createCriteria("transcript").add( Restrictions.eq("stableId",transcriptId.trim()));
		return (List<Snp>)criteria.list();
	}
	@Override
	public List<List<Snp>> getAllByEnsemblTranscriptList(List<String> transcriptIds) {
		Session session =  this.openSession();
		List<List<Snp>> snpList = new ArrayList<List<Snp>>(transcriptIds.size());
		for(String transcriptId: transcriptIds) {
			snpList.add(this.getAllByEnsemblTranscriptId(transcriptId, session));
		}
		session.close();
		return snpList;
	}
	/****/
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByConsequenceType(String consequenceType) {
		Criteria criteria = this.openSession().createCriteria(Snp.class);
		criteria.add(Restrictions.eq("displayConsequence", consequenceType.trim()));
		return (List<Snp>)executeAndClose(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByConsequenceTypeList(List<String> consequenceTypeList) {
		Criteria criteria = this.openSession().createCriteria(Snp.class).setCacheable(true).setFetchMode("SnpToTranscript", FetchMode.JOIN);
		for (String consequenceType : consequenceTypeList) {
			criteria.add(Restrictions.disjunction().add(Restrictions.eq("displayConsequence", consequenceType)));
		}
		return (List<Snp>)executeAndClose(criteria);
	}

	@Override
	public List<Snp> getAllByCytoband(String chromosome, String cytoband) {
		// TODO Auto-generated method stub
		return null;
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
		List<List<Snp>> results = new ArrayList<List<Snp>>();
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
		return this.getAllByRegion(chromosome, start, start);
	}

	@Override
	public List<Snp> getAllByRegion(String chromosome, int start, int end) {
		GenomicRegionFeatureDBAdaptor genomicRegionFeatureDBAdaptor = new GenomicRegionFeatureHibernateDBAdaptor(this.getSessionFactory());
		GenomicRegionFeatures genomicRegionFeatures = genomicRegionFeatureDBAdaptor.getByRegion(new Region(chromosome, start, end), Arrays.asList("snp"));
		return genomicRegionFeatures.getSnp();
	}
	


	@Override
	public List<Snp> getAllByRegion(String chromosome, int start, int end, List<String> consequenceTypeList) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllConsequenceTypes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, String consequence) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds, List<String> consequenceTypes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllIdsByConsequenceType(String consequenceType) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<String>> getAllIdsByConsequenceTypeList(List<String> consequenceTypeList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllIdsByRegion(String chromosome, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeAllFilteredByConsequenceType(String consequence,String outfile) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<String> getAllIds() {
		// TODO Auto-generated method stub
		return null;
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
