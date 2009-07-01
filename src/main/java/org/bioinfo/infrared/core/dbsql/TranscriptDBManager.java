package org.bioinfo.infrared.core.dbsql;

import java.sql.SQLException;
import java.util.List;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.BeanHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.core.Transcript;

public class TranscriptDBManager extends DBManager {

	private static final String SELECT_FIELDS = " t.stable_id,t.chromosome,t.start,t.end,t.strand,t.biotype ";
	public static final String TRANS_BY_ID_STM = "select " + SELECT_FIELDS + " from xref x, transcript2xref tx, transcript t where x.display_id = ? and x.xref_id = tx.xref_id and tx.transcript_id=t.transcript_id group by t.stable_id  order by t.start";
	public static final String GET_SEQUENCE_BY_ENSEMBL_ID = "SELECT ts.sequence FROM trasncript t, transcript_sequence ts WHERE t.stable_id = ? and t.transcript_id=ts.transcript_id ";
	public static final String GET_TRANSCRIPTLIST_BY_ID = "select " + SELECT_FIELDS + " from xref x, transcript2xref tx, transcript t where x.display_id = ? and x.xref_id = tx.xref_id and tx.transcript_id = t.transcript_id group by t.stable_id";
	
	public TranscriptDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList<Transcript> getTranscriptListById(String id) throws Exception{
//		return new TranscriptList((List<Transcript>)getFeatureListById(TRANS_BY_ID_STM, id, new BeanArrayListHandler(Transcript.class)).getElements());
		return getFeatureListById(TRANS_BY_ID_STM, id, new BeanArrayListHandler(Transcript.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<FeatureList<Transcript>> getTranscriptListByIds(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getListOfFeatureListByIds(TRANS_BY_ID_STM, ids, new BeanArrayListHandler(Transcript.class));
		
//		List<RosettaFeatureList> featList = getListOfFeatureList(TRANS_BY_ID_STM,ids, new BeanArrayListHandler(Transcript.class));
//		List<TranscriptList> transList = new ArrayList<TranscriptList>(featList.size());
//		for(RosettaFeatureList ros: featList) {
//			transList.add(new TranscriptList((List<Transcript>)ros.getElements()));
//		}
//		return transList;
	}
	@SuppressWarnings({ "unchecked" })
	public FeatureList<Transcript> getAllByExternalId(String id) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_TRANSCRIPTLIST_BY_ID, id, new BeanArrayListHandler(Transcript.class));
	}
	
	public String getSequenceByEnsemblId(String ensemblId) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return (String) dBConnector.getDbConnection().createSQLPrepQuery(GET_SEQUENCE_BY_ENSEMBL_ID).execute(ensemblId, new BeanHandler(String.class));
//		return (String)executePreparedQuery(GET_SEQUENCE_BY_ENSEMBL_ID, ensemblId, new BeanHandler(String.class));
	}
}
