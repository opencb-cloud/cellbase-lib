package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.JasparTfbs;
import org.bioinfo.infrared.regulatory.OregannoTfbs;

public class OregannoTfbsDBManager extends DBManager {
	
	public static final String GET_BY_SNP_ID = "select o.* from snp s, snp2oreganno s2o, oreganno o where s.name = ? and s.snp_id=s2o.snp_id and s2o.oreganno_id=o.oreganno_id group by o.oreganno_id";
	
	public OregannoTfbsDBManager(DBConnector dBConnector) {
		super(dBConnector);
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
	public FeatureList<OregannoTfbs> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select o.* from oreganno o", new BeanArrayListHandler(OregannoTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<OregannoTfbs> getAllByLocation(String chromosome, int position) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select o.* from oreganno o where o.chromosome= '"+chromosome+"' and "+position+">=o.start and "+position+"<=o.end ", new BeanArrayListHandler(OregannoTfbs.class));
	}
	
}
