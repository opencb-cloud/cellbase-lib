package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.TransfacTfbs;

public class TransfacTfbsDBManager extends DBManager {
	
	
	public TransfacTfbsDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTfbs> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from transfac_tfbs tf, gene g where g.stable_id = ? and g.gene_id=tf.gene_id", geneId, new BeanArrayListHandler(TransfacTfbs.class));
//		return new TransfacTFBSList((List<TransfacTfbs>)getFeatureList("select * from transfac_tfbs where ensembl_gene_id = ? ", geneId, new BeanArrayListHandler(TransfacTfbs.class)).getElements());
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTfbs> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select tf.* from transfac_tfbs tf, snp s, snp2transfac_tfbs s2t where snp_name = ? and s.snp_id=s2t.snp_id and s2t.transfac_tfbs_id=tf.transfac_tfbs_id", snpId, new BeanArrayListHandler(TransfacTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<TransfacTfbs>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select tf.* from transfac_tfbs tf, snp s, snp2transfac_tfbs s2t where snp_name = ? and s.snp_id=s2t.snp_id and s2t.transfac_tfbs_id=tf.transfac_tfbs_id", snpIds, new BeanArrayListHandler(TransfacTfbs.class));
//		List<FunctionalPropertyList> featList = getListOfFeatureList("select * from transfac_tfbs where snp_id = ? ",snpIds, new BeanArrayListHandler(TransfacTfbs.class));
//		List<TransfacTFBSList> TransfacTFBSList = new ArrayList<TransfacTFBSList>(featList.size());
//		for(FunctionalPropertyList pupas: featList) {
//			TransfacTFBSList.add(new TransfacTFBSList((List<TransfacTfbs>)pupas.getElements()));
//		}
//		return TransfacTFBSList;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTfbs> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select * from transfac_tfbs ", new BeanArrayListHandler(TransfacTfbs.class));
	}
	
}
