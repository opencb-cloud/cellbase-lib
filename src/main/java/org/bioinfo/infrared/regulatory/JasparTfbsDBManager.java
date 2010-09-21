package org.bioinfo.infrared.regulatory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.Feature;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.regulatory.JasparTfbs;
import org.bioinfo.infrared.core.variation.SNP;
import org.bioinfo.infrared.variation.SNPDBManager;

public class JasparTfbsDBManager extends DBManager {
	
	private static final String SELECT_FIELDS = " j.tf_factor_name, g.stable_id, j.relative_start, j.relative_end, j.chromosome, j.absolute_start, j.absolute_end, j.strand, j.score, j.sequence ";
	public static final String GET_BY_SNP_ID = "select "+SELECT_FIELDS+" from snp s, snp2jaspar_tfbs s2j, jaspar_tfbs j, gene g where s.name = ? and s.snp_id=s2j.snp_id and s2j.jaspar_tfbs_id=j.jaspar_tfbs_id and j.gene_id=g.gene_id";
	
	public JasparTfbsDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTfbs> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where j.gene_id=g.gene_id", new BeanArrayListHandler(JasparTfbs.class));
	}
	
	public void writeAll(String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
		writeFeatureList("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where j.gene_id=g.gene_id", new File(outfile));
	}
	
	public void writeAllWithSnps(String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
		writeFeatureList("select s.name as 'SNP name', concat(s.chromosome,':',s.start,'(',s.strand,')') as 'SNP Location', s.allele_String, j.tf_factor_name, g.stable_id as 'Gene', j.relative_start, j.relative_end, j.chromosome, j.absolute_start, j.absolute_end, j.strand, j.score, j.sequence  from snp s, jaspar_tfbs j, snp2jaspar_tfbs s2j, gene g where j.jaspar_tfbs_id=s2j.jaspar_tfbs_id and s2j.snp_id=s.snp_id and j.gene_id=g.gene_id", new File(outfile));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTfbs> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where g.stable_id= ? and j.gene_id=g.gene_id", geneId, new BeanArrayListHandler(JasparTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTfbs> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_BY_SNP_ID, snpId, new BeanArrayListHandler(JasparTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<JasparTfbs>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_BY_SNP_ID, snpIds, new BeanArrayListHandler(JasparTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTfbs> getAllByPosition(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureList("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where j.gene_id=g.gene_id and j.chromosome= '"+chromosome+"' and "+position+">=j.absolute_start and "+position+"<=j.absolute_end ", new BeanArrayListHandler(JasparTfbs.class));
		return getFeatureList("select "+SELECT_FIELDS+" from feature_map_jaspar_tfbs fm, jaspar_tfbs j, gene g where fm.chromosome= '"+chromosome+"' and fm.position="+position+" and fm.id=j.jaspar_tfbs_id and j.gene_id=g.gene_id", new BeanArrayListHandler(JasparTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<JasparTfbs>> getAllByLocation(List<SNP> snps) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<FeatureList<JasparTfbs>> featureList = new ArrayList<FeatureList<JasparTfbs>>(snps.size());
//		getFeatureList("select "+SELECT_FIELDS+" from feature_map_jaspar_tfbs fm, jaspar_tfbs j, gene g where fm.chromosome=? and fm.position=? and fm.id=j.jaspar_tfbs_id and j.gene_id=g.gene_id", new BeanArrayListHandler(JasparTfbs.class));
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery("select "+SELECT_FIELDS+" from feature_map_jaspar_tfbs fm, jaspar_tfbs j, gene g where fm.chromosome= ? and fm.position= ? and fm.id=j.jaspar_tfbs_id and j.gene_id=g.gene_id");
		Object queryResult = null;
		FeatureList featList = new FeatureList();
		int cont = 0;
		for(SNP snp: snps) {
			prepQuery.setParams(snp.getChromosome(), ""+snp.getStart());
			
			queryResult = prepQuery.execute(new BeanArrayListHandler(JasparTfbs.class));
			if(queryResult == null) {
//				featList = null;
				featureList.add(null);
			}else {
				featList.clear();
				featList.addAll((List<Feature>)queryResult);
//				featList = new FeatureList((List<Feature>)queryResult);
//				featList.setRosettaDBConnector(dBConnector);

				featureList.add(featList);
			}
//			featureList.add(featList);
			if(cont++ % 10000 == 0) {
				System.out.println("SNPs processed: "+cont);
			}
		}
		prepQuery.close();
		return featureList;
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<SNP>> getSnpsByIds(List<String> oregannoIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select "+SNPDBManager.SELECT_FIELDS+" from jaspar_tfbs j, snp2jaspar_tfbs s2j, snp s where j.tf_factor_name= ? and j.jaspar_tfbs_id=s2j.jaspar_tfbs_id and s2j.snp_id=s.snp_id", oregannoIds, new BeanArrayListHandler(SNP.class));
	}
	
}
