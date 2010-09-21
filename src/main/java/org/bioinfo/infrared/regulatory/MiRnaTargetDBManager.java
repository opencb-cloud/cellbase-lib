package org.bioinfo.infrared.regulatory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.regulatory.MiRnaTarget;

public class MiRnaTargetDBManager extends DBManager {
	
	public static final String SELECT_FIELDS= " mt.mirna_id, t.stable_id, mt.chromosome, mt.start, mt.end, mt.strand, mt.score, mt.pvalue ";
	public static final String GET_ALL_BY_SNP = "select "+SELECT_FIELDS+" from snp s, snp2mirna_target s2mt, mirna_target mt, transcript t where s.name = ? and s.snp_id=s2mt.snp_id and s2mt.mirna_target_id=mt.mirna_target_id and mt.transcript_id=t.transcript_id";
	public static final String GET_BY_MATURE_ID = "select "+SELECT_FIELDS+" from mirna_target mt, transcript t where mt.mirna_id = ? and mt.transcript_id=t.transcript_id";
	public static final String GET_ALL_BY_TRANSCRIPT_ID = "select "+SELECT_FIELDS+" from mirna_target mt, transcript t where t.stable_id= ? and t.transcript_id = mt.transcript_id";
	public static final String GET_ALL = "select "+SELECT_FIELDS+" from mirna_target mt, transcript t where mt.transcript_id=t.transcript_id ";
	public static final String GET_ALL_BY_SCORE = "select "+SELECT_FIELDS+" from mirna_target mt, transcript t where mt.transcript_id=t.transcript_id and mt.score >= ";
	public static final String GET_ALL_BY_PVALUE = "select "+SELECT_FIELDS+" from mirna_target mt, transcript t where mt.transcript_id=t.transcript_id and pvalue <= ";
	
	
	public MiRnaTargetDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByMatureId(String matureId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{		
		return getFeatureListById(GET_BY_MATURE_ID, matureId, new BeanArrayListHandler(MiRnaTarget.class));
	}

	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRnaTarget>> getAllByMatureIds(List<String> matureIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		List<FeatureList<MiRnaTarget>> featList = getListOfFeatureListByIds(GET_BY_MATURE_ID, matureIds, new BeanArrayListHandler(MiRnaTarget.class));
		return featList;	
	}

	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByTranscriptId(String transcriptId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		return getFeatureListById(GET_ALL_BY_TRANSCRIPT_ID, transcriptId, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRnaTarget>> getAllByTranscriptIds(List<String> transcriptIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		List<FeatureList<MiRnaTarget>> featList = getListOfFeatureListByIds(GET_ALL_BY_TRANSCRIPT_ID, transcriptIds, new BeanArrayListHandler(MiRnaTarget.class));
		return featList;	
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByPosition(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL+" and mt.chromosome = '"+chromosome+"' and mt.start <= "+position +" and mt.end >= " + position, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByRegion(String chromosome, int start, int end) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList(GET_ALL+" and mt.chromosome = '"+chromosome+"' and mt.start >= "+start +" and mt.end <= " + end, new BeanArrayListHandler(MiRnaTarget.class));
	}
		
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByScore(double score) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		return getFeatureList(GET_ALL_BY_SCORE + score, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByPValue(double pvalue) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		return getFeatureList(GET_ALL_BY_PVALUE + pvalue, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select "+SELECT_FIELDS+" from mirna_target mt, trancript t where t.stable_id= ? and t.transcript_id = mt.transcript_id ", geneId, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<MiRnaTarget> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_ALL_BY_SNP, snpId, new BeanArrayListHandler(MiRnaTarget.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<MiRnaTarget>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_ALL_BY_SNP, snpIds, new BeanArrayListHandler(MiRnaTarget.class));
	}

	public void writeAllWithSnps(String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
		writeFeatureList("select s.name as 'SNP name', concat(s.chromosome,':',s.start,'(',s.strand,')') as 'SNP Location', mt.mirna_id, t.stable_id as 'Transcript', mt.chromosome, mt.start, mt.end, mt.strand, mt.score, mt.pvalue from snp2mirna_target s2t, mirna_target mt, snp s, transcript t where mt.mirna_target_id=s2t.mirna_target_id and s2t.snp_id=s.snp_id and mt.transcript_id=t.transcript_id", new File(outfile));
	}
	
}
