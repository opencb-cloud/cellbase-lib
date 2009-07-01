package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.TransfacTFBS;

public class TransfacTFBSDBManager extends DBManager {
	
	
	public TransfacTFBSDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTFBS> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from transfac_tfbs where gene_stable_id = ? ", geneId, new BeanArrayListHandler(TransfacTFBS.class));
//		return new TransfacTFBSList((List<TransfacTFBS>)getFeatureList("select * from transfac_tfbs where ensembl_gene_id = ? ", geneId, new BeanArrayListHandler(TransfacTFBS.class)).getElements());
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTFBS> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from transfac_tfbs where snp_name = ? ", snpId, new BeanArrayListHandler(TransfacTFBS.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<TransfacTFBS>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select * from transfac_tfbs where snp_name = ? ", snpIds, new BeanArrayListHandler(TransfacTFBS.class));
//		List<FunctionalPropertyList> featList = getListOfFeatureList("select * from transfac_tfbs where snp_id = ? ",snpIds, new BeanArrayListHandler(TransfacTFBS.class));
//		List<TransfacTFBSList> TransfacTFBSList = new ArrayList<TransfacTFBSList>(featList.size());
//		for(FunctionalPropertyList pupas: featList) {
//			TransfacTFBSList.add(new TransfacTFBSList((List<TransfacTFBS>)pupas.getElements()));
//		}
//		return TransfacTFBSList;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTFBS> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select * from transfac_tfbs ", new BeanArrayListHandler(TransfacTFBS.class));
//		return new TransfacTFBSList((List<TransfacTFBS>)getFeatureList("select * from transfac_tfbs", new BeanArrayListHandler(TransfacTFBS.class)).getElements());
	}
	
}
