package org.bioinfo.infrared.core.dbsql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.infrared.common.dbsql.DBConnector;
import org.bioinfo.infrared.common.dbsql.DBManager;
import org.bioinfo.infrared.common.feature.FeatureList;
import org.bioinfo.infrared.core.XRef;

public class XRefDBManager extends DBManager {

	
	public static final String PREP_QUERY = "select h2.display_id from xref h1, xref h2 where h1.display_id = ? and h1.stable_id = h2.stable_id and h2.dbname = ? group by h2.display_id"; 
	public static final String GET_ALL_IDS_BY_DBNAME = "select x.display_id from xref x, dbname db where db.dbname = ? and db.dbname_id=x.dbname_id";
	public static final String GET_ALL_IDS_BY_IDS = "select x2.display_id from xref x1, transcript2xref tx1, xref x2, transcript2xref tx2, dbname db where x1.display_id= ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname='";
	public static final String GET_TRANSLATION = "select x2.display_id, db.dbname, db.display_label, x2.description from xref x1, xref x2, transcript2xref tx1, transcript2xref tx2, dbname db where x1.display_id=? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id  and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname= ? group by x2.display_id;";
	
	public XRefDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}
	
	public List<String> getAllIdsByDBName(String dbname) throws Exception{
		return getStringListById(GET_ALL_IDS_BY_DBNAME, dbname);
	}
	
	public List<List<String>> getIdsByDBName(List<String> ids, String dbname) throws Exception{
		return getStringListByIds(GET_ALL_IDS_BY_IDS+dbname+"' group by x2.display_id ", ids);
	}

	@SuppressWarnings("unchecked")
	public FeatureList<XRef> getByDBName(String id, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_TRANSLATION.replaceFirst("\\?", "'"+id+"'"), dbname, new BeanArrayListHandler(XRef.class));
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, FeatureList<XRef>> getByDBNames(String id, List<String> dbnames) throws Exception {
		Map<String,FeatureList<XRef>> featureMap = new HashMap<String, FeatureList<XRef>>((int)Math.round(dbnames.size()*1.4));
		for(String dbname: dbnames) {
			featureMap.put(dbname, getFeatureListById(GET_TRANSLATION.replaceFirst("\\?", "'"+id+"'"), dbname, new BeanArrayListHandler(XRef.class)));
		}
		return featureMap;
	}
	
	public List<Map<String, FeatureList<XRef>>> getListByDBNames(List<String> ids, List<String> dbnames) throws Exception {
		List<Map<String, FeatureList<XRef>>> xrefList = new ArrayList<Map<String,FeatureList<XRef>>>(ids.size());
		for(String id: ids) {
			xrefList.add(getByDBNames(id, dbnames));
		}
		return xrefList;
	}
	
	
	
	
}
