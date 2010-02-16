package org.bioinfo.infrared.variation.dbsql;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.Omega;

public class OmegaDBManager extends DBManager {
	//ENSSNP1002525   ENST00000013125 ENSG00000012983 G/T     P/T     KPG     589-589 0.0000  p>0.05  0.054   M2      no
	
	private static final String SELECT_FIELDS = " s.name, s.chromosome, s.start, s.end, s.strand, s.sequence, s.allele_string, t.stable_id, o.aa_change, o.aa_enviroment, o.aa_relative_position, o.w_slr, o.slr_pval, o.w_bayesian, o.model, o.extrapolated ";
	public static final String GET_ALL_BY_SNP = "select " + SELECT_FIELDS + " from snp s, omega o, transcript t where s.name = ? and s.snp_id=o.snp_id and o.transcript_id=t.transcript_id ";
	
	public OmegaDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	public FeatureList<Omega> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList("SELECT "+SELECT_FIELDS+" FROM omega o, snp s, transcript t WHERE o.snp_id=s.snp_id and o.transcript_id=t.transcript_id", new BeanArrayListHandler(Omega.class));
		return getAllByOmegaRange(0.0, 1.0);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Omega> getAllByOmegaRange(double min, double max) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		return getFeatureList("SELECT "+SELECT_FIELDS+" FROM omega o, snp s, transcript t WHERE o.snp_id=s.snp_id and o.transcript_id=t.transcript_id and o.w_bayesian>="+min+" and o.w_bayesian<="+max, new BeanArrayListHandler(Omega.class));
	}
	
	public void writeAll(String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
		writeAllByOmegaRange(0.0, 1.0, outfile);
	}
	
	public void writeAllByOmegaRange(double min, double max, String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		writeFeatureList("select s.name as 'SNP name', concat(s.chromosome,':',s.start,'(',s.strand,')') as 'SNP Location', s.allele_String, t.stable_id as 'Transcript', o.aa_change, o.aa_enviroment, o.aa_relative_position, o.w_slr, o.slr_pval, o.w_bayesian, o.model, o.extrapolated FROM omega o, snp s, transcript t WHERE o.snp_id=s.snp_id and o.transcript_id=t.transcript_id and o.w_bayesian>="+min+" and o.w_bayesian<="+max, new File(outfile));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Omega> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_ALL_BY_SNP, snpId, new BeanArrayListHandler(Omega.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<Omega>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_ALL_BY_SNP, snpIds, new BeanArrayListHandler(Omega.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<Omega>> getAllBySnpIds(List<String> snpIds, double min, double max) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(min > max) {
			double swap = max;
			max = min;
			min = swap;
		}
		return getListOfFeatureListByIds(GET_ALL_BY_SNP + " and o.w_bayesian>="+min+" and o.w_bayesian<="+max, snpIds, new BeanArrayListHandler(Omega.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Omega> getAllByTranscriptId(String transId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select "+SELECT_FIELDS+" from snp s, omega o, transcript t where t.stable_id = ?  and t.transcript_id=o.transcript_id and o.snp_id=s.snp_id ", transId, new BeanArrayListHandler(Omega.class));
	}
	

	public FeatureList<Omega> getAllByRange(double i, double d) {
		

		return null;
	}
	
//	@SuppressWarnings("unchecked")
//	public FeatureList<Omega> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new OmegaList((List<Omega>)getFeatureList("select * from omega where ensembl_gene_id = ? ", geneId, new BeanArrayListHandler(Omega.class)).getElements());
//	}
//	
//	@SuppressWarnings("unchecked")
//	public FeatureList<Omega> getAllByOmegaValue(double min, double max) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return new OmegaList((List<Omega>)getFeatureList("select * from omega where w_bayesian > "+min+" and w_bayesian < "+max, new BeanArrayListHandler(Omega.class)).getElements());
//	}

	
}
