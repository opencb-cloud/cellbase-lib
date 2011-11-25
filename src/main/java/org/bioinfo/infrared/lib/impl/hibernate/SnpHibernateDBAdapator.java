package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.JoinType;

import org.bioinfo.commons.utils.StringUtils;
import org.bioinfo.infrared.core.cellbase.ConsequenceType;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Snp;
import org.bioinfo.infrared.core.cellbase.SnpToTranscript;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

public class SnpHibernateDBAdapator extends HibernateDBAdaptor implements SnpDBAdaptor {
	private int MAX_BATCH_QUERIES_LIST = 50;
	public SnpHibernateDBAdapator(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAll(){
		Criteria criteria = this.openSession().createCriteria(Snp.class);
		return (List<Snp>) this.executeAndClose(criteria);
	}
	
	
	private List<Snp> queryByIdList(List<String> idList){
		Query query = this.openSession().createQuery("select snp from Snp as snp left join fetch snp.snpToTranscripts as stt  left join fetch snp.snpXrefs as sxr  left join fetch snp.snp2functionals as s2f left join fetch stt.consequenceType as consequenceType where snp.name in :name ");
		query.setParameterList("name", idList);
		return (List<Snp>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getByIdList(List<String> idList){
		List<Snp> result = new ArrayList<Snp>();
		
		if (idList.size() > MAX_BATCH_QUERIES_LIST){
			for (int i = 0; i < idList.size()/MAX_BATCH_QUERIES_LIST; i = i + MAX_BATCH_QUERIES_LIST) {
				result.addAll(queryByIdList((ArrayList)idList).subList(i, i + MAX_BATCH_QUERIES_LIST -1));
			}
			//result.addAll(queryByIdList((ArrayList)idList).subList( (idList.size()/MAX_BATCH_QUERIES_LIST)*MAX_BATCH_QUERIES_LIST , idList.size()));
		}
		else{
			
			
		}
		
	
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getById(String name){
		List<String> ids = new ArrayList<String>();
		ids.add(name);
		return getByIdList(ids);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Snp> getAllByConsequenceType(String consequenceType) {
		Criteria criteria = this.openSession().createCriteria(Snp.class).setFetchMode("SnpToTranscript", FetchMode.JOIN);
		criteria.add(Restrictions.eq("displayConsequence", consequenceType.trim()));
		return (List<Snp>)executeAndClose(criteria);
	}


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
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllByPosition(Position position) {
		// TODO Auto-generated method stub
		
		return null;
	}


	@Override
	public List<List<Snp>> getAllByPositionList(List<Position> positionList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllByRegion(String chromosome) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllByRegion(String chromosome, int start) {
		return null;
	}


	@Override
	public List<Snp> getAllByRegion(String chromosome, int start, int end) {
		return null;
	}


	@Override
	public List<Snp> getAllByRegion(String chromosome, int start, int end,
			List<String> consequenceTypeList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllByRegion(Region region) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllByRegion(Region region,
			List<String> consequenceTypeList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<Snp>> getAllByRegionList(List<Region> regionList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<Snp>> getAllByRegionList(List<Region> regionList,
			List<String> consequenceTypeList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllConsequenceTypes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds,
			String consequence) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getAllFilteredByConsequenceType(List<String> snpIds,
			List<String> consequenceTypes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllIdsByConsequenceType(String consequenceType) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<String>> getAllIdsByConsequenceTypeList(
			List<String> consequenceTypeList) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllIdsByRegion(String chromosome, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Snp> getByGeneId(String externalId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<Snp>> getByGeneIdList(List<String> externalIds) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void writeAllFilteredByConsequenceType(String consequence,
			String outfile) {
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

	
	
//	@SuppressWarnings("unchecked")
//	public String getAllNames() {
//		
//		return HibernateDBUtils.getStaticTest();
//	}

//	@SuppressWarnings("unchecked")
//	public List<String> getAllNamesByRegion(String chromosome) {
//		criteria = this.getSession().createCriteria(Transcript.class);
//		criteria.add(Restrictions.eq("chromosome", chromosome));
//		return (List<String>) execute(criteria);
//	}

//	public List<String> getAllNamesByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getStringList(GET_ALL_SNP_NAMES + " where s.chromosome = '"+chromosome+"' and s.start >= "+start +" and s.end <= "+end+" ");
//	}
//
//	public List<String> getAllNamesByConsequenceType(String consequenceType) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getStringList("select s.name from snp2transcript st, snp s, consequence_type cq where cq.consequence_type_name= '"+consequenceType+"' and cq.consequence_type_id=st.consequence_type
//	}
//
//	public List<String> getAllNamesByConsequenceTypes(List<String> consequenceTypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		List<String> snpNames = null;
//		if(consequenceTypes != null) {
//			snpNames = new ArrayList<String>();
//			for(String consequenceType: consequenceTypes) {
//				if(consequenceType != null) {
//					snpNames.addAll(getAllNamesByConsequenceType(consequenceType));
//				}
//			}
//		}
//		return snpNames;
//	}
//
//	public List<String> getAllConsequenceTypes() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getStringList("select consequence_type_name from consequence_type");
//	}
//
//	@Deprecated
//	public List<String> getAllNamesByLocation(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getStringList(GET_ALL_SNP_NAMES + " where s.chromosome = '"+chromosome+"' and s.start > "+start +" and s.end < "+end+" ");
//	}
//
//
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList(GET_ALL_SNPS, new BeanArrayListHandler(SNP.class));
//	}
//
//	public SNP getByName(String name) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return (SNP)getFeatureById(GET_SNP_BY_NAME, name, new BeanHandler(SNP.class));
//	}
//
//	public FeatureList<SNP> getByNames(List<String> names) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureListByIds(GET_SNP_BY_NAME, names, new BeanHandler(SNP.class));
//	}
//
//	@SuppressWarnings("unchecked")
//	@Deprecated
//	public FeatureList<SNP> getSNPListByIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureListByIds("SELECT s.* FROM snp s WHERE s.name = ? ", snpIds, new BeanHandler(SNP.class));
//	}
//
//
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllByPosition(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList(GET_ALL_SNPS  + " where s.chromosome = '"+chromosome+"' and s.start = "+position, new BeanArrayListHandler(SNP.class));
//	}
//
//	public FeatureList<SNP> getAllByPosition(Position position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(position != null) {
//			return getAllByPosition(position.getChromosome(), position.getPosition());      
//		}else {
//			return null;
//		}
//	}
//
//	public List<FeatureList<SNP>> getAllByPositions(List<Position> positions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		List<FeatureList<SNP>> snpList = null;
//		if(positions != null) {
//			snpList = new ArrayList<FeatureList<SNP>>(positions.size());
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_SNPS  + " where s.chromosome = ? and s.start = ? ");
//			FeatureList<SNP> snpFeatureList;
//			for(Position position: positions) {
//				if(position != null) {
//					prepQuery.setParams(position.getChromosome(), ""+position.getPosition());
//					snpFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(SNP.class)));
//					if(snpFeatureList != null && snpFeatureList.size() > 0) {
//						snpList.add(snpFeatureList);
//					}else {
//						snpList.add(null);
//					}
//				}else {
//					snpList.add(null);
//				}
//			}
//			prepQuery.close();
//		}
//		return snpList;
//	}
//
//
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllByRegion(String chromosome) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList(GET_ALL_SNPS  + " where s.chromosome = '"+chromosome+"' ", new BeanArrayListHandler(SNP.class));
//	}
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllByRegion(String chromosome, int start) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList(GET_ALL_SNPS  + " where s.chromosome = '"+chromosome+"' and s.start >= "+start +" ", new BeanArrayListHandler(SNP.class));
//	}
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList(GET_ALL_SNPS  + " where s.chromosome = '"+chromosome+"' and s.start >= "+start +" and s.end <= "+end+" ", new BeanArrayListHandler(SNP.class));
//	}
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllByRegion(Region region) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(region != null) {
//			return getFeatureList(GET_ALL_SNPS  + " where s.chromosome = '"+region.getChromosome()+"' and s.start >= "+region.getStart()+" and s.end <= "+region.getEnd()+" ", new BeanArrayLis
//		}else {
//			return null;
//		}
//	}
//
//	public List<FeatureList<SNP>> getAllByRegions(List<Region> regions) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		List<FeatureList<SNP>> snpList = null;
//		if(regions != null) {
//			snpList = new ArrayList<FeatureList<SNP>>(regions.size());
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_SNPS  + " where s.chromosome = ? and s.start >= ? and s.end <= ?");
//			FeatureList<SNP> snpFeatureList;
//			for(Region region: regions) {
//				if(region != null) {
//					prepQuery.setParams(region.getChromosome(), ""+region.getStart(), ""+region.getEnd());
//					snpFeatureList = new FeatureList((List<Feature>)prepQuery.execute(new BeanArrayListHandler(SNP.class)));
//					if(snpFeatureList != null && snpFeatureList.size() > 0) {
//						snpList.add(snpFeatureList);
//					}else {
//						snpList.add(null);
//					}
//				}else {
//					snpList.add(null);
//				}
//			}
//			prepQuery.close();
//		}
//		return snpList;
//	}
//
//
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllByConsequenceType(String consequence) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureListById(GET_SNPS_BY_CONSEQUENCE_TYPE, consequence, new BeanArrayListHandler(SNP.class));
//	}
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getAllFilteredByConsequenceType(List<String> snpIds, String consequence) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		FeatureList<SNP> f = getFeatureListByIds(GET_SNPS_FILTERED_BY_CONSEQUENCE_TYPE+consequence+"' group by s.name", snpIds, new BeanHandler(SNP.class));
//		//              f.removeNullElements();
//		return f;
//	}
//
//	public FeatureList<SNP> getAllFilteredByConsequenceType(List<String> snpIds, List<String> consequenceTypes) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		FeatureList<SNP> snpFeatureList = new FeatureList<SNP>();
//		for(String consequenceType: consequenceTypes) {
//			snpFeatureList.addAll(getAllFilteredByConsequenceType(snpIds, consequenceType));
//		}
//		return snpFeatureList;
//	}
//
//	public void writeAllFilteredByConsequenceType(String consequence, String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
//		writeFeatureList("select "+SELECT_FIELDS+" from snp2transcript st, snp s, consequence_type cq where cq.consequence_type_name='"+consequence+"' and cq.consequence_type_id=st.consequence_ty
//	}
//
//	@SuppressWarnings("unchecked")
//	public FeatureList<SNP> getByExternalId(String externalId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureListById(GET_SNPS_BY_EXTERNAL_ID, externalId, new BeanArrayListHandler(SNP.class));
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<FeatureList<SNP>> getByExternalId(List<String> externalIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getListOfFeatureListByIds(GET_SNPS_BY_EXTERNAL_ID, externalIds, new BeanArrayListHandler(SNP.class));
//	}

}
