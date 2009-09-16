package org.bioinfo.infrared.funcannot.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.MiRNAInfo;

public class MiRNAInfoDBManager extends DBManager {

	public static final String GET_ALL_BY_MATURE_ID = "select * from mirna_info where mirna_mature_id = ?";
	public static final String GET_ALL_BY_GENE_ID = "select * from mirna_info where mirna_gene_id = ?";
	public static final String GET_ALL_POSITION = "select mi.* from mirna_info mi, dbname db, transcript2xref tx, xref x, transcript t where t.biotype='miRNA' and t.transcript_id=tx.transcript_id and tx.xref_id=x.xref_id and x.dbname_id=db.dbname_id and db.dbname='mirna' and x.display_id=mi.mirna_gene_id  and x.display_id=mi.mirna_gene_id and x.display_id=mi.mirna_gene_id ";
	
	public MiRNAInfoDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRNAInfo> getAllByMatureId(String matureId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
//		return new MiRNAInfoList((List<MiRNAInfo>)getFeatureList(GET_ALL_BY_MATURE_ID, matureId, new BeanArrayListHandler(MiRNAInfo.class)).getElements());
		return getFeatureListById(GET_ALL_BY_MATURE_ID, matureId, new BeanArrayListHandler(MiRNAInfo.class));
	}

	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRNAInfo>> getAllByMatureIds(List<String> matureIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		List<FeatureList<MiRNAInfo>> featList = getListOfFeatureListByIds(GET_ALL_BY_MATURE_ID, matureIds, new BeanArrayListHandler(MiRNAInfo.class));
//		List<MiRNAInfoList> miRNAInfoList = new ArrayList<MiRNAInfoList>(featList.size());
//		for(RosettaFeatureList ros: featList) {
//			miRNAInfoList.add(new MiRNAInfoList((List<MiRNAInfo>)ros.getElements()));
//		}
		return featList;		
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRNAInfo> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
//		return new MiRNAInfoList((List<MiRNAInfo>)getFeatureList(GET_ALL_BY_GENE_ID, geneId, new BeanArrayListHandler(MiRNAInfo.class)).getElements());
		return getFeatureListById(GET_ALL_BY_GENE_ID, geneId, new BeanArrayListHandler(MiRNAInfo.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRNAInfo>> getAllByGeneIds(List<String> geneIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		List<FeatureList<MiRNAInfo>> featList = getListOfFeatureListByIds(GET_ALL_BY_GENE_ID, geneIds, new BeanArrayListHandler(MiRNAInfo.class));
//		List<MiRNAInfoList> miRNAInfoList = new ArrayList<MiRNAInfoList>(featList.size());
//		for(RosettaFeatureList ros: featList) {
//			miRNAInfoList.add(new MiRNAInfoList((List<MiRNAInfo>)ros.getElements()));
//		}
		return featList;		
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRNAInfo> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new MiRNAInfoList((List<MiRNAInfo>)getFeatureList(GET_ALL_POSITION+" and t.chromosome = '"+chromosome+"' and t.start <= "+position +" and t.end >= " + position, new BeanArrayListHandler(MiRNAInfo.class)).getElements());		
		return getFeatureList(GET_ALL_POSITION+" and t.chromosome = '"+chromosome+"' and t.start <= "+position +" and t.end >= " + position, new BeanArrayListHandler(MiRNAInfo.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRNAInfo> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new MiRNAInfoList((List<MiRNAInfo>)getFeatureList(GET_ALL_POSITION+" and t.chromosome = '"+chromosome+"' and t.start >= "+start +" and t.end <= " + end, new BeanArrayListHandler(MiRNAInfo.class)).getElements());
		return getFeatureList(GET_ALL_POSITION+" and t.chromosome = '"+chromosome+"' and t.start >= "+start +" and t.end <= " + end, new BeanArrayListHandler(MiRNAInfo.class));
	}
	
}
