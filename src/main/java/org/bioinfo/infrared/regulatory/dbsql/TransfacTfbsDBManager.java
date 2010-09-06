package org.bioinfo.infrared.regulatory.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.regulatory.TransfacTfbs;
import org.bioinfo.infrared.variation.SNP;
import org.bioinfo.infrared.variation.dbsql.SNPDBManager;

@Deprecated
public class TransfacTfbsDBManager extends DBManager {
	
	private static final String SELECT_FIELDS = " tf.transfac_tfbs_id, g.stable_id, tf.factor_id, tf.factor_name, tf.relative_start, tf.relative_end, tf.chromosome, tf. absolute_start, tf.absolute_end, tf.strand, tf. core_match, tf.matrix_match, tf.length, tf.sequence ";
	
	public TransfacTfbsDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTfbs> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select "+SELECT_FIELDS+" from transfac_tfbs tf, gene g where g.stable_id = ? and g.gene_id=tf.gene_id", geneId, new BeanArrayListHandler(TransfacTfbs.class));
//		return new TransfacTFBSList((List<TransfacTfbs>)getFeatureList("select * from transfac_tfbs where ensembl_gene_id = ? ", geneId, new BeanArrayListHandler(TransfacTfbs.class)).getElements());
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTfbs> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select "+SELECT_FIELDS+" from transfac_tfbs tf, gene g, snp s, snp2transfac_tfbs s2t where s.name = ? and s.snp_id=s2t.snp_id and s2t.transfac_tfbs_id=tf.transfac_tfbs_id and tf.gene_id=g.gene_id", snpId, new BeanArrayListHandler(TransfacTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<TransfacTfbs>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select "+SELECT_FIELDS+" from transfac_tfbs tf, gene g, snp s, snp2transfac_tfbs s2t where s.name = ? and s.snp_id=s2t.snp_id and s2t.transfac_tfbs_id=tf.transfac_tfbs_id and tf.gene_id=g.gene_id", snpIds, new BeanArrayListHandler(TransfacTfbs.class));
//		List<FunctionalPropertyList> featList = getListOfFeatureList("select * from transfac_tfbs where snp_id = ? ",snpIds, new BeanArrayListHandler(TransfacTfbs.class));
//		List<TransfacTFBSList> TransfacTFBSList = new ArrayList<TransfacTFBSList>(featList.size());
//		for(FunctionalPropertyList pupas: featList) {
//			TransfacTFBSList.add(new TransfacTFBSList((List<TransfacTfbs>)pupas.getElements()));
//		}
//		return TransfacTFBSList;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<TransfacTfbs> getAll() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureList("select "+SELECT_FIELDS+" from transfac_tfbs tf, gene g where tf.gene_id=g.gene_id", new BeanArrayListHandler(TransfacTfbs.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<SNP>> getSnpsByIds(List<String> transfacIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select "+SNPDBManager.SELECT_FIELDS+" from transfac_tfbs tf, snp2transfac_tfbs s2t, snp s where tf.factor_id= ? and tf.transfac_tfbs_id=s2t.transfac_tfbs_id and s2t.snp_id=s.snp_id", transfacIds, new BeanArrayListHandler(SNP.class));
	}
	
}
