package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.JasparTFBS;

public class JasparTFBSDBManager extends DBManager {
	
	private static final String SELECT_FIELDS = " j.factor_name, g.stable_id, g.chromosome, j.start, j.end, j.score ";
	public static final String GET_BY_SNP_ID = "select "+SELECT_FIELDS+" from snp s, snp2jaspar_tfbs s2j, jaspar_tfbs j, gene g where s.name = ? and s.snp_id=s2j.snp_id and s2j.jaspar_id=j.jaspar_tfbs_id and j.gene_id=g.gene_id";
	
	public JasparTFBSDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTFBS> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where g.stable_id= ? and j.gene_id=g.gene_id", geneId, new BeanArrayListHandler(JasparTFBS.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTFBS> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_BY_SNP_ID, snpId, new BeanArrayListHandler(JasparTFBS.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<JasparTFBS>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(GET_BY_SNP_ID, snpIds, new BeanArrayListHandler(JasparTFBS.class));
	}
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTFBS> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where j.gene_id=g.gene_id", new BeanArrayListHandler(JasparTFBS.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<JasparTFBS> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select "+SELECT_FIELDS+" from jaspar_tfbs j, gene g where j.gene_id=g.gene_id and g.chromosome= '"+chromosome+"' and "+position+">g.start and "+position+"<g.end ", new BeanArrayListHandler(JasparTFBS.class));
	}
}
