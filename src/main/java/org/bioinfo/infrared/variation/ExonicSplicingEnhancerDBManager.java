package org.bioinfo.infrared.variation;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.variation.ExonicSplicingEnhancer;

public class ExonicSplicingEnhancerDBManager extends DBManager {
	
	
	public ExonicSplicingEnhancerDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ExonicSplicingEnhancer> getAllByTranscriptId(String transId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from exonic_splicing_enhancer where transcript_stable_id = ? ", transId, new BeanArrayListHandler(ExonicSplicingEnhancer.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ExonicSplicingEnhancer> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from exonic_splicing_enhancer where gene_stable_id = ? ", geneId, new BeanArrayListHandler(ExonicSplicingEnhancer.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ExonicSplicingEnhancer> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from exonic_splicing_enhancer where snp_name = ? ", snpId, new BeanArrayListHandler(ExonicSplicingEnhancer.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<ExonicSplicingEnhancer>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select * from exonic_splicing_enhancer where snp_name = ? ", snpIds, new BeanArrayListHandler(ExonicSplicingEnhancer.class));
	}
	
}
