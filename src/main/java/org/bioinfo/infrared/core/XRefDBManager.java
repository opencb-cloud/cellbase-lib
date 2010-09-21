package org.bioinfo.infrared.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.api.Query;
import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.MatrixHandler;
import org.bioinfo.infrared.common.DBConnector;
import org.bioinfo.infrared.common.DBManager;
import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.feature.DBName;
import org.bioinfo.infrared.core.feature.XRef;

public class XRefDBManager extends DBManager {

	public static final String GET_ALL_DBNAMES = "select dbname_id, dbname, display_label, dbname_type from dbname;";
	public static final String GET_ALL_DBNAMES_BY_TYPE = "select dbname_id, dbname, display_label, dbname_type from dbname where dbname_type= ? ;";
	public static final String PREP_QUERY = "select h2.display_id from xref h1, xref h2 where h1.display_id = ? and h1.stable_id = h2.stable_id and h2.dbname = ? group by h2.display_id;"; 
	public static final String GET_ALL_IDS_BY_DBNAME = "select x.display_id from xref x, dbname db where db.dbname = ? and db.dbname_id=x.dbname_id;";
	public static final String GET_ALL_IDS_BY_IDS = "select x2.display_id from xref x1, transcript2xref tx1, xref x2, transcript2xref tx2, dbname db where x1.display_id= ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname= '";
	public static final String GET_TRANSLATION = "select db.dbname, x2.display_id, x2.description from xref x1, transcript2xref tx1, xref x2, transcript2xref tx2, dbname db where x1.display_id= ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname= ? group by x2.display_id;";
	public static final String GET_ALL_IDENTIFIERS = "select db.dbname, x2.display_id, x2.description from xref x1, transcript2xref tx1, xref x2, transcript2xref tx2, dbname db where x1.display_id= ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and (db.dbname_type='Identifier' or db.dbname_type='Microarray Probeset') group by x2.display_id;";
	public static final String GET_ALL_FUNCTIONAL_ANNOTATIONS = "select db.dbname, x2.display_id, x2.description from xref x1, transcript2xref tx1, xref x2, transcript2xref tx2, dbname db where x1.display_id= ? and x1.xref_id=tx1.xref_id and tx1.transcript_id=tx2.transcript_id and tx2.xref_id=x2.xref_id and x2.dbname_id=db.dbname_id and db.dbname_type=('Pathway' or 'Functional Annotation') group by x2.display_id;";

	public XRefDBManager(DBConnector dBConnector) {
		super(dBConnector);
	}

	// Returns all possible DB names
	@SuppressWarnings("unchecked")
	public List<DBName> getAllDBNames() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<DBName> dbnames = new ArrayList<DBName>();
		Query query = dBConnector.getDbConnection().createSQLQuery(GET_ALL_DBNAMES);
		dbnames = (List<DBName>)query.execute(new BeanArrayListHandler(DBName.class));
		query.close();
		return dbnames;
	}
	
	// Returns all possible DB names by type
	@SuppressWarnings("unchecked")
	public List<DBName> getAllDBNamesByType(String type) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		List<DBName> dbnames = new ArrayList<DBName>();
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_DBNAMES_BY_TYPE);
		prepQuery.setParams(type);
		dbnames = (List<DBName>)prepQuery.execute(new BeanArrayListHandler(DBName.class));
		prepQuery.close();
		return dbnames;
	}
	
	// Returns an XRef object according to a single ID and DB (used in FeatureId.java)
	public XRef getByDBName(String id, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(id != null && !id.equals("")) {
			if(dbname != null && !dbname.equals("")) {
				PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
				prepQuery.setParams(id, dbname);
				Object[][] result = (Object[][])prepQuery.execute(new MatrixHandler());
				XRef xref = new XRef(id, dbname);
				for(int i=0; i<result.length; i++) {
					xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
				}
				prepQuery.close();
				return xref;
			}else {
				return new XRef(id);
			}
		}else {
			return null;
		}
	}

	// Returns a list of XRef objects according to a list of IDs and single DB (used in FeatureId.java)
	public List<XRef> getByDBName(List<String> ids, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(ids != null) {
			List<XRef> xrefs = new ArrayList<XRef>(ids.size());
			if(dbname != null && !dbname.equals("")) {
				XRef xref;
				PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
				Object[][] result;
				for(String id: ids) {
					if(id != null && !id.equals("")) {
						prepQuery.setParams(id, dbname);
						result = (Object[][])prepQuery.execute(new MatrixHandler());
						xref = new XRef(id, dbname);
						for(int i=0; i<result.length; i++) {
							xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
						}
						xrefs.add(xref);
					}else {
						xrefs.add(null);
					}
				}
				prepQuery.close();
			}else {
				for(String id: ids) {
					xrefs.add(new XRef(id));
				}
			}
			return xrefs;
		}else {
			return null;
		}
	}
	
	// Returns an XRef object according to a single ID in the specified list of DDBB (used in FeatureId.java)
	public XRef getByDBName(String id, List<String> dbnames) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(id != null && !id.equals("")) {
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
			XRef xref = new XRef(id);
			Object[][] result;
			if(dbnames != null) {
				for(String dbname: dbnames) {
					if(dbname != null && !dbname.equals("")) {
						prepQuery.setParams(id, dbname);
						result = (Object[][])prepQuery.execute(new MatrixHandler());
						xref.addDbName(dbname);
						for(int i=0; i<result.length; i++) {
							xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
						}
					}
				}
				prepQuery.close();
			}
			return xref;
		}else {
			return null;
		}
	}

	// Returns a list of XRef objects according to a list of ID in the specified list of DDBB (used in FeatureId.java)
	public List<XRef> getByDBName(List<String> ids, List<String> dbnames) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(ids != null) {
			List<XRef> xrefs = new ArrayList<XRef>(ids.size());
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
			Object[][] result;
			for(String id: ids) {
				if(id != null && !id.equals("")) {
					XRef xref = new XRef(id);
					if(dbnames != null) {
						for(String dbname: dbnames) {
							if(dbname != null && !dbname.equals("")) {
								prepQuery.setParams(id, dbname);
								result = (Object[][])prepQuery.execute(new MatrixHandler());
								xref.addDbName(dbname);
								for(int i=0; i<result.length; i++) {
									xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
								}
							}
						}
					}
					xrefs.add(xref);
				}else {
					xrefs.add(null);
				}
			}
			prepQuery.close();
			return xrefs;
		}else {
			return null;
		}
	}

	
	// Returns a list of XRef objects with all the possible identifiers for every single ID in a list (used in FeatureId.java)
	public List<XRef> getAllIdentifiersByIds(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(ids != null) {
//			List<XRef> xrefs = new ArrayList<XRef>(ids.size());
//			XRef xref;
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_IDENTIFIERS);
//			Object[][] result;
//			for(String id: ids) {
//				if(id != null && !id.equals("")) {
//					prepQuery.setParams(id);
//					result = (Object[][])prepQuery.execute(new MatrixHandler());
//					xref = new XRef(id);
//					for(int i=0; i<result.length; i++) {
//						xref.addXRefItem(result[i][0].toString(), result[i][1].toString(), result[i][2].toString());
//					}
//					xrefs.add(xref);
//				}else {
//					xrefs.add(null);
//				}
//			}
//			prepQuery.close();
//			return xrefs;
//		}else {
//			return null;
//		}
		return getAllXRefsByPrepQuery(ids, GET_ALL_IDENTIFIERS);
	}
	
	
	public List<XRef> getAllFunctionalAnnotByIds(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(ids != null) {
//			List<XRef> xrefs = new ArrayList<XRef>(ids.size());
//			XRef xref;
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_FUNCTIONAL_ANNOTATIONS);
//			Object[][] result;
//			for(String id: ids) {
//				if(id != null && !id.equals("")) {
//					prepQuery.setParams(id);
//					result = (Object[][])prepQuery.execute(new MatrixHandler());
//					xref = new XRef(id);
//					for(int i=0; i<result.length; i++) {
//						xref.addXRefItem(result[i][0].toString(), result[i][1].toString(), result[i][2].toString());
//					}
//					xrefs.add(xref);
//				}else {
//					xrefs.add(null);
//				}
//			}
//			prepQuery.close();
//			return xrefs;
//		}else {
//			return null;
//		}
		return getAllXRefsByPrepQuery(ids, GET_ALL_FUNCTIONAL_ANNOTATIONS);
	}
	
	private List<XRef> getAllXRefsByPrepQuery(List<String> ids, String prepQuerySql) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		if(ids != null) {
			List<XRef> xrefs = new ArrayList<XRef>(ids.size());
			XRef xref;
			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQuerySql);
			Object[][] result;
			for(String id: ids) {
				if(id != null && !id.equals("")) {
					prepQuery.setParams(id);
					result = (Object[][])prepQuery.execute(new MatrixHandler());
					xref = new XRef(id);
					for(int i=0; i<result.length; i++) {
						xref.addXRefItem(result[i][0].toString(), result[i][1].toString(), result[i][2].toString());
					}
					xrefs.add(xref);
				}else {
					xrefs.add(null);
				}
			}
			prepQuery.close();
			return xrefs;
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Deprecated
	public FeatureList<XRef> getByDBNameOld(String id, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return getFeatureListById(GET_TRANSLATION.replaceFirst("\\?", "'"+id+"'"), dbname, new BeanArrayListHandler(XRef.class));
	}

	public List<String> getAllIdsByDBName(String dbname) throws Exception{
		return getStringListById(GET_ALL_IDS_BY_DBNAME, dbname);
	}

	@Deprecated
	public List<String> getIdsByDBName(List<String> ids, String dbname) throws Exception{
		return getStringListByIds(GET_ALL_IDS_BY_IDS+dbname+"' group by x2.display_id ", ids);
	}

	//	public List<List<String>> getIdsByDBName(List<String> ids, List<String> dbnames) throws Exception{
	//		List<List<String>> xrefs = new ArrayList<List<String>>(ids.size());
	//		for(String id: ids) {
	//			getStringListListByIds(GET_ALL_IDS_BY_IDS+dbname+"' group by x2.display_id ", ids);
	//		}
	//		return xrefs;
	//	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public Map<String, FeatureList<XRef>> getByDBNames(String id, List<String> dbnames) throws Exception {
		Map<String,FeatureList<XRef>> featureMap = new HashMap<String, FeatureList<XRef>>((int)Math.round(dbnames.size()*1.4));
		for(String dbname: dbnames) {
			featureMap.put(dbname, getFeatureListById(GET_TRANSLATION.replaceFirst("\\?", "'"+id+"'"), dbname, new BeanArrayListHandler(XRef.class)));
		}
		return featureMap;
	}
	
	@Deprecated
	public List<Map<String, FeatureList<XRef>>> getListByDBNames(List<String> ids, List<String> dbnames) throws Exception {
		List<Map<String, FeatureList<XRef>>> xrefList = new ArrayList<Map<String,FeatureList<XRef>>>(ids.size());
		for(String id: ids) {
			xrefList.add(getByDBNames(id, dbnames));
		}
		return xrefList;
	}

}
