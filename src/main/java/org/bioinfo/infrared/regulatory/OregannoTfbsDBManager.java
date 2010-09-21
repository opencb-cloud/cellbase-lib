package org.bioinfo.infrared.regulatory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.regulatory.OregannoTfbs;
import org.bioinfo.infrared.core.variation.SNP;
import org.bioinfo.infrared.variation.SNPDBManager;

public class OregannoTfbsDBManager extends DBManager {
	
	public static final String GET_BY_SNP_ID = "select o.* from snp s, snp2oreganno s2o, oreganno o where s.name = ? and s.snp_id=s2o.snp_id and s2o.oreganno_id=o.oreganno_id group by o.oreganno_id";
	
	public OregannoTfbsDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<OregannoTfbs> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select o.* from oreganno o", new BeanArrayListHandler(OregannoTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<OregannoTfbs> getAllByGeneId(String geneStableId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select o.* from oreganno o where o.gene_name= '"+geneStableId+"' or o.gene_id= '"+geneStableId+"' ", new BeanArrayListHandler(OregannoTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<OregannoTfbs> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_BY_SNP_ID, snpId, new BeanArrayListHandler(OregannoTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<OregannoTfbs>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_BY_SNP_ID, snpIds, new BeanArrayListHandler(OregannoTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<OregannoTfbs> getAllByPosition(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select o.* from oreganno o where o.chromosome= '"+chromosome+"' and "+position+">=o.start and "+position+"<=o.end ", new BeanArrayListHandler(OregannoTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<SNP>> getSnpsByIds(List<String> oregannoIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select "+SNPDBManager.SELECT_FIELDS+" from oreganno o, snp2oreganno s2o, snp s where o.land_mark_id= ? and o.oreganno_id=s2o.oreganno_id and s2o.snp_id=s.snp_id", oregannoIds, new BeanArrayListHandler(SNP.class));
	}
	
	public void writeAllWithSnps(String outfile) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
		writeFeatureList("select s.name as 'SNP name', concat(s.chromosome,':',s.start,'(',s.strand,')') as 'SNP Location', s.allele_String, o.land_mark_id, o.name, o.chromosome, o.start, o.end, o.strand, o.land_mark_type, o.gene_name, o.gene_id, o.gene_source, o.tf_name, o.tf_id, o.tf_source, o.pubmed_id from snp s, snp2oreganno s2o, oreganno o where  s.snp_id=s2o.snp_id and s2o.oreganno_id=o.oreganno_id group by o.oreganno_id", new File(outfile));
	}
	
}
