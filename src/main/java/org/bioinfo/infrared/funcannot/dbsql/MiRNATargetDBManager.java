package org.bioinfo.infrared.funcannot.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.MiRnaTarget;

@Deprecated
public class MiRNATargetDBManager extends DBManager {
		
	public static final String GET_BY_MATURE_ID = "select mt.mirna_id,t.stable_id,mt.chromosome,mt.start,mt.end,mt.strand,mt.score,mt.pvalue from mirna_target mt, transcript t where mt.mirna_id = ? and mt.transcript_id=t.transcript_id";
	public static final String GET_ALL_BY_TRANSCRIPT_ID = "select mt.mirna_id,t.stable_id,mt.chromosome, mt.start,mt.end,mt.strand,mt.score,mt.pvalue from mirna_target mt, transcript t where t.stable_id= ? and t.transcript_id = mt.transcript_id";
	public static final String GET_ALL = "select mt.mirna_id,t.stable_id,mt.chromosome,mt.start,mt.end,mt.strand,mt.score,mt.pvalue from mirna_target mt, transcript t where mt.transcript_id=t.transcript_id ";
	public static final String GET_ALL_BY_SCORE = "select mt.mirna_id,t.stable_id,mt.chromosome,mt.start,mt.end,mt.strand,mt.score,mt.pvalue from mirna_target mt, transcript t where mt.transcript_id=t.transcript_id and mt.score >= ";
	public static final String GET_ALL_BY_PVALUE = "select mt.mirna_id,t.stable_id,mt.chromosome,mt.start,mt.end,mt.strand,mt.score,mt.pvalue from mirna_target mt, transcript t where mt.transcript_id=t.transcript_id and pvalue <= ";
	
	public MiRNATargetDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByMatureId(String matureId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{		
//		return new MiRNATargetList((List<MiRnaTarget>)getFeatureList(GET_BY_MATURE_ID, matureId, new BeanArrayListHandler(MiRnaTarget.class)).getElements());
		return getFeatureListById(GET_BY_MATURE_ID, matureId, new BeanArrayListHandler(MiRnaTarget.class));
	}

	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRnaTarget>> getAllByMatureIds(List<String> matureIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		List<FeatureList<MiRnaTarget>> featList = getListOfFeatureListByIds(GET_BY_MATURE_ID, matureIds, new BeanArrayListHandler(MiRnaTarget.class));
//		List<MiRNATargetList> miRNATargetList = new ArrayList<MiRNATargetList>(featList.size());
//		for(RosettaFeatureList ros: featList) {
//			miRNATargetList.add(new MiRNATargetList((List<MiRnaTarget>)ros.getElements()));
//		}
		return featList;	
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByTranscriptId(String transcriptId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
//		return new MiRNATargetList((List<MiRnaTarget>)getFeatureList(GET_ALL_BY_TRANSCRIPT_ID, transcriptId, new BeanArrayListHandler(MiRnaTarget.class)).getElements());
		return getFeatureListById(GET_ALL_BY_TRANSCRIPT_ID, transcriptId, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRnaTarget>> getAllByTranscriptIds(List<String> transcriptIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		List<FeatureList<MiRnaTarget>> featList = getListOfFeatureListByIds(GET_ALL_BY_TRANSCRIPT_ID, transcriptIds, new BeanArrayListHandler(MiRnaTarget.class));
//		List<MiRNATargetList> miRNATargetList = new ArrayList<MiRNATargetList>(featList.size());
//		for(RosettaFeatureList ros: featList) {
//			miRNATargetList.add(new MiRNATargetList((List<MiRnaTarget>)ros.getElements()));
//		}
		return featList;	
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new MiRNATargetList((List<MiRnaTarget>)getFeatureList(GET_ALL_BY+" and mt.chromosome = '"+chromosome+"' and mt.start <= "+position +" and mt.end >= " + position, new BeanArrayListHandler(MiRnaTarget.class)).getElements());		
		return getFeatureList(GET_ALL+" and mt.chromosome = '"+chromosome+"' and mt.start <= "+position +" and mt.end >= " + position, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new MiRNATargetList((List<MiRnaTarget>)getFeatureList(GET_ALL_BY+" and mt.chromosome = '"+chromosome+"' and mt.start >= "+start +" and mt.end <= " + end, new BeanArrayListHandler(MiRnaTarget.class)).getElements());
		return getFeatureList(GET_ALL+" and mt.chromosome = '"+chromosome+"' and mt.start >= "+start +" and mt.end <= " + end, new BeanArrayListHandler(MiRnaTarget.class));
	}
		
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByScore(double score) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
//		return new MiRNATargetList((List<MiRnaTarget>)getFeatureList(GET_ALL_BY_SCORE, ""+score, new BeanArrayListHandler(MiRnaTarget.class)).getElements());
		return getFeatureList(GET_ALL_BY_SCORE + score, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByPValue(double pvalue) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
//		return new MiRNATargetList((List<MiRnaTarget>)getFeatureList(GET_ALL_BY_PVALUE + pvalue, new BeanArrayListHandler(MiRnaTarget.class)).getElements());
		return getFeatureList(GET_ALL_BY_PVALUE + pvalue, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
}
