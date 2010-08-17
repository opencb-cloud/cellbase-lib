package org.bioinfo.infrared.variation.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.variation.ExonicSplicingSilencer;

public class ExonicSplicingSilencerDBManager extends DBManager {
	
	public ExonicSplicingSilencerDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ExonicSplicingSilencer> getAllByTranscriptId(String transId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from exonic_splicing_silencer where transcript_stable_id = ? ", transId, new BeanArrayListHandler(ExonicSplicingSilencer.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ExonicSplicingSilencer> getAllByGeneId(String geneId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from exonic_splicing_silencer where gene_stable_id = ? ", geneId, new BeanArrayListHandler(ExonicSplicingSilencer.class));
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<ExonicSplicingSilencer> getAllBySnpId(String snpId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById("select * from exonic_splicing_silencer where snp_name = ? ", snpId, new BeanArrayListHandler(ExonicSplicingSilencer.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<ExonicSplicingSilencer>> getAllBySnpIds(List<String> snpIds) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds("select * from exonic_splicing_silencer where snp_name = ? ", snpIds, new BeanArrayListHandler(ExonicSplicingSilencer.class));
	}
	
}
