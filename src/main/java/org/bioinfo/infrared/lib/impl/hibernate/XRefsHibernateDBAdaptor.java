package org.bioinfo.infrared.lib.impl.hibernate;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Dbname;
import org.bioinfo.infrared.lib.api.XRefsDBAdaptor;
import org.bioinfo.infrared.lib.common.XRefs;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class XRefsHibernateDBAdaptor extends HibernateDBAdaptor implements XRefsDBAdaptor {
	
	
	public XRefsHibernateDBAdaptor(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	
	@Override
	public List<Dbname> getAllDBNames() {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Dbname> getAllDBNamesById(String id) {
		Query query = this.openSession().createQuery("select db.* from Xref x, Dbname db where x.display_id = :Id and x.dbname_id=db.dbname_id").setParameter("Id", id);
		return (List<Dbname>)executeAndClose(query);
	}


	@Override
	public List<String> getAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Dbname> getAllDBNamesByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllIdsByDBName(String dbname) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	@Override
	public XRefs getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<XRefs> getAllByIdList(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public XRefs getById(String id, String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<XRefs> getAllByIdList(List<String> ids, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	@Override
	public XRefs getByDBName(String id, String dbname) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<XRefs> getAllByDBName(List<String> ids, String dbname) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public XRefs getByDBNameList(String id, List<String> dbnames) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<XRefs> getAllByDBNameList(List<String> ids, List<String> dbnames) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	@Override
	public XRefs getByDBName(String id, String dbname, String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<XRefs> getAllByDBName(List<String> ids, String dbname, String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public XRefs getByDBNameList(String id, List<String> dbnames, String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<XRefs> getAllByDBNameList(List<String> ids, List<String> dbnames, String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// Returns all possible DB names
//	@SuppressWarnings("unchecked")
//	public List<DBName> getAllDBNames() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		List<DBName> dbnames = new ArrayList<DBName>();
//		Query query = dBConnector.getDbConnection().createSQLQuery(GET_ALL_DBNAMES);
//		dbnames = (List<DBName>)query.execute(new BeanArrayListHandler(DBName.class));
//		query.close();
//		return dbnames;
//	}
//
//	// Returns all possible DB names by type
//	@SuppressWarnings("unchecked")
//	public List<DBName> getAllDBNamesByType(String type) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		List<DBName> dbnames = new ArrayList<DBName>();
//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_DBNAMES_BY_TYPE);
//		prepQuery.setParams(type);
//		dbnames = (List<DBName>)prepQuery.execute(new BeanArrayListHandler(DBName.class));
//		prepQuery.close();
//		return dbnames;
//	}
//
//	// Returns an XRefs object according to a single ID and DB (used in FeatureId.java)
//	public XRefs getByDBName(String id, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(id != null && !id.equals("")) {
//			if(id.contains("'")) {
//				id = id.replace("'", "\\\\'");
//			}
//			if(dbname != null && !dbname.equals("")) {
//				PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
//				prepQuery.setParams(id, dbname);
//				Object[][] result = (Object[][])prepQuery.execute(new MatrixHandler());
//				XRefs xref = new XRefs(id, dbname);
//				for(int i=0; i<result.length; i++) {
//					xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
//				}
//				prepQuery.close();
//				return xref;
//			}else {
//				return new XRefs(id);
//			}
//		}else {
//			return null;
//		}
//	}
//
//	// Returns a list of XRefs objects according to a list of IDs and single DB (used in FeatureId.java)
//	public List<XRefs> getByDBName(List<String> ids, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(ids != null) {
//			List<XRefs> xrefs = new ArrayList<XRefs>(ids.size());
//			if(dbname != null && !dbname.equals("")) {
//				XRefs xref;
//				PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
//				Object[][] result;
//				for(String id: ids) {
//					if(id != null && !id.equals("")) {
//						if(id.contains("'")) {
//							id = id.replace("'", "\\\\'");
//						}
//						prepQuery.setParams(id, dbname);
//						result = (Object[][])prepQuery.execute(new MatrixHandler());
//						xref = new XRefs(id, dbname);
//						for(int i=0; i<result.length; i++) {
//							xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
//						}
//						xrefs.add(xref);
//					}else {
//						xrefs.add(null);
//					}
//				}
//				prepQuery.close();
//			}else {
//				for(String id: ids) {
//					xrefs.add(new XRefs(id));
//				}
//			}
//			return xrefs;
//		}else {
//			return null;
//		}
//	}
//
//	// Returns an XRefs object according to a single ID in the specified list of DDBB (used in FeatureId.java)
//	public XRefs getByDBName(String id, List<String> dbnames) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(id != null && !id.equals("")) {
//			if(id.contains("'")) {
//				id = id.replace("'", "\\\\'");
//			}
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
//			XRefs xref = new XRefs(id);
//			Object[][] result;
//			if(dbnames != null) {
//				for(String dbname: dbnames) {
//					if(dbname != null && !dbname.equals("")) {
//						prepQuery.setParams(id, dbname);
//						result = (Object[][])prepQuery.execute(new MatrixHandler());
//						xref.addDbName(dbname);
//						for(int i=0; i<result.length; i++) {
//							xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
//						}
//					}
//				}
//				prepQuery.close();
//			}
//			return xref;
//		}else {
//			return null;
//		}
//	}
//
//	// Returns a list of XRefs objects according to a list of ID in the specified list of DDBB (used in FeatureId.java)
//	public List<XRefs> getByDBName(List<String> ids, List<String> dbnames) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(ids != null) {
//			List<XRefs> xrefs = new ArrayList<XRefs>(ids.size());
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_TRANSLATION);
//			XRefs xref;
//			Object[][] result;
//			for(String id: ids) {
//				if(id != null && !id.equals("")) {
//					if(id.contains("'")) {
//						id = id.replace("'", "\\\\'");
//					}
//					xref = new XRefs(id);
//					if(dbnames != null) {
//						for(String dbname: dbnames) {
//							if(dbname != null && !dbname.equals("")) {
//								prepQuery.setParams(id, dbname);
//								result = (Object[][])prepQuery.execute(new MatrixHandler());
//								xref.addDbName(dbname);
//								for(int i=0; i<result.length; i++) {
//									xref.addXRefItem(dbname, result[i][1].toString(), result[i][2].toString());
//								}
//							}
//						}
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
//	}
//
//
//	// Returns a list of XRefs objects with all the possible identifiers for every single ID in a list (used in FeatureId.java)
//	public List<XRefs> getAllIdentifiersByIds(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		//		if(ids != null) {
//		//			List<XRefs> xrefs = new ArrayList<XRefs>(ids.size());
//		//			XRefs xref;
//		//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_IDENTIFIERS);
//		//			Object[][] result;
//		//			for(String id: ids) {
//		//				if(id != null && !id.equals("")) {
//		//					prepQuery.setParams(id);
//		//					result = (Object[][])prepQuery.execute(new MatrixHandler());
//		//					xref = new XRefs(id);
//		//					for(int i=0; i<result.length; i++) {
//		//						xref.addXRefItem(result[i][0].toString(), result[i][1].toString(), result[i][2].toString());
//		//					}
//		//					xrefs.add(xref);
//		//				}else {
//		//					xrefs.add(null);
//		//				}
//		//			}
//		//			prepQuery.close();
//		//			return xrefs;
//		//		}else {
//		//			return null;
//		//		}
//		return getAllXRefsByPrepQuery(ids, GET_ALL_IDENTIFIERS);
//	}
//
//
//	public List<XRefs> getAllFunctionalAnnotByIds(List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		//		if(ids != null) {
//		//			List<XRefs> xrefs = new ArrayList<XRefs>(ids.size());
//		//			XRefs xref;
//		//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(GET_ALL_FUNCTIONAL_ANNOTATIONS);
//		//			Object[][] result;
//		//			for(String id: ids) {
//		//				if(id != null && !id.equals("")) {
//		//					prepQuery.setParams(id);
//		//					result = (Object[][])prepQuery.execute(new MatrixHandler());
//		//					xref = new XRefs(id);
//		//					for(int i=0; i<result.length; i++) {
//		//						xref.addXRefItem(result[i][0].toString(), result[i][1].toString(), result[i][2].toString());
//		//					}
//		//					xrefs.add(xref);
//		//				}else {
//		//					xrefs.add(null);
//		//				}
//		//			}
//		//			prepQuery.close();
//		//			return xrefs;
//		//		}else {
//		//			return null;
//		//		}
//		return getAllXRefsByPrepQuery(ids, GET_ALL_FUNCTIONAL_ANNOTATIONS);
//	}
//
//	private List<XRefs> getAllXRefsByPrepQuery(List<String> ids, String prepQuerySql) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		if(ids != null) {
//			List<XRefs> xrefs = new ArrayList<XRefs>(ids.size());
//			XRefs xref;
//			PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQuerySql);
//			Object[][] result;
//			for(String id: ids) {
//				if(id != null && !id.equals("")) {
//					if(id.contains("'")) {
//						id = id.replace("'", "\\\\'");
//					}
//					prepQuery.setParams(id);
//					result = (Object[][])prepQuery.execute(new MatrixHandler());
//					xref = new XRefs(id);
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
//	}
//
//	@SuppressWarnings("unchecked")
//	@Deprecated
//	public FeatureList<XRefs> getByDBNameOld(String id, String dbname) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		return getFeatureListById(GET_TRANSLATION.replaceFirst("\\?", "'"+id+"'"), dbname, new BeanArrayListHandler(XRefs.class));
//	}
//
//	public List<String> getAllIdsByDBName(String dbname) throws Exception{
//		return getStringListById(GET_ALL_IDS_BY_DBNAME, dbname);
//	}
//
//	@Deprecated
//	public List<String> getIdsByDBName(List<String> ids, String dbname) throws Exception{
//		return getStringListByIds(GET_ALL_IDS_BY_IDS+dbname+"' group by x2.display_id ", ids);
//	}
//
//	//	public List<List<String>> getIdsByDBName(List<String> ids, List<String> dbnames) throws Exception{
//	//		List<List<String>> xrefs = new ArrayList<List<String>>(ids.size());
//	//		for(String id: ids) {
//	//			getStringListListByIds(GET_ALL_IDS_BY_IDS+dbname+"' group by x2.display_id ", ids);
//	//		}
//	//		return xrefs;
//	//	}
//
//	@SuppressWarnings("unchecked")
//	@Deprecated
//	public Map<String, FeatureList<XRefs>> getByDBNames(String id, List<String> dbnames) throws Exception {
//		Map<String,FeatureList<XRefs>> featureMap = new HashMap<String, FeatureList<XRefs>>((int)Math.round(dbnames.size()*1.4));
//		for(String dbname: dbnames) {
//			featureMap.put(dbname, getFeatureListById(GET_TRANSLATION.replaceFirst("\\?", "'"+id+"'"), dbname, new BeanArrayListHandler(XRefs.class)));
//		}
//		return featureMap;
//	}
//
//	@Deprecated
//	public List<Map<String, FeatureList<XRefs>>> getListByDBNames(List<String> ids, List<String> dbnames) throws Exception {
//		List<Map<String, FeatureList<XRefs>>> xrefList = new ArrayList<Map<String,FeatureList<XRefs>>>(ids.size());
//		for(String id: ids) {
//			xrefList.add(getByDBNames(id, dbnames));
//		}
//		return xrefList;
//	}
}
