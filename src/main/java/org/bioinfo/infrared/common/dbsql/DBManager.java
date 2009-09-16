package org.bioinfo.infrared.common.dbsql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bioinfo.db.api.PreparedQuery;
import org.bioinfo.db.api.Query;
import org.bioinfo.db.handler.BeanArrayListHandler;
import org.bioinfo.db.handler.ResultSetHandler;
import org.bioinfo.infrared.common.feature.Feature;
import org.bioinfo.infrared.common.feature.FeatureList;


public class DBManager {
	
	protected DBConnector dBConnector;

	protected DBManager() {
		// ¿leer un fichero de configuracion por defecto?
		this.dBConnector = null;
	}
	
	protected DBManager(DBConnector dBConnector) {
		this.dBConnector = dBConnector;
	}
	
//	public Object executeQuery(String queryStm) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		Query query = dBConnector.getDbConnection().createSQLQuery(queryStm);
//		Object obj = query.execute();
//		query.close();
//		return obj;
//	}
//	
//	public Object executeQuery(String queryStm, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		Query query = dBConnector.getDbConnection().createSQLQuery(queryStm);
//		Object obj = query.execute(rsh); 
//		query.close();
//		return obj;
//	}
//	
//	public Object executePreparedQuery(String queryStm, String id, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(queryStm);
//		prepQuery.setParams(id);
//		Object obj = prepQuery.execute(rsh); 
//		prepQuery.close();
//		return obj;
//	}
	
	@SuppressWarnings("unchecked")
	public List<String> getStringList(String prepQueryStm) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		Query query = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		List<String> rosettaFeatureList = (List<String>) query.execute(new BeanArrayListHandler(String.class));
		query.close();
		return rosettaFeatureList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getStringListById(String prepQueryStm, String id) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		prepQuery.setParams(id);
		List<String> stringList = (List<String>) prepQuery.execute(new BeanArrayListHandler(String.class));
		prepQuery.close();
		return stringList;
	}
	
	@SuppressWarnings("unchecked")
	public List<List<String>> getStringListByIds(String prepQueryStm, List<String> ids) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		List<List<String>> listStringList = new ArrayList<List<String>>(ids.size());
		List<String> stringList;
		for(String id: ids) {
			prepQuery.setParams(id);
			
			stringList = (List<String>) prepQuery.execute(new BeanArrayListHandler(String.class));
			listStringList.add(stringList);
		}
		prepQuery.close();
		return listStringList;
	}
	
	
	
	
	
	public Feature getFeatureById(String prepQueryStm, String id, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		prepQuery.setParams(id);
		Feature feature = (Feature)prepQuery.execute(rsh);
		if(feature != null) {
			feature.setRosettaDBConnector(dBConnector);	
		}
		prepQuery.close();
		return feature;
	}
	
//	public Feature getFeatureByIdWithMultiParam(String prepQueryStm, String id, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
//		prepQuery.setParams(id);
//		Feature feature = (Feature)prepQuery.execute(rsh);
//		if(feature != null) {
//			feature.setRosettaDBConnector(dBConnector);	
//		}
//		prepQuery.close();
//		return feature;
//	}
	
	@SuppressWarnings("unchecked")
	public FeatureList getFeatureList(String prepQueryStm, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		Query query = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		FeatureList rosettaFeatureList = new FeatureList((List<Feature>)query.execute(rsh));
		rosettaFeatureList.setRosettaDBConnector(dBConnector);
		query.close();
		return rosettaFeatureList;
	}
	
	@SuppressWarnings("unchecked")
	public FeatureList getFeatureListById(String prepQueryStm, String id, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		prepQuery.setParams(id);
		FeatureList featureList = new FeatureList((List<Feature>)prepQuery.execute(rsh));
		if(featureList != null) {
			featureList.setRosettaDBConnector(dBConnector);
		}
		prepQuery.close();
		return featureList;
	}
	
//	@SuppressWarnings("unchecked")
//	public FeatureList getFeatureListByMultiParam(String prepQueryStm, List<String> ids, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
//		prepQuery.setParams(ids);
//		FeatureList featureList = new FeatureList((List<Feature>)prepQuery.execute(rsh));
//		if(featureList != null) {
//			featureList.setRosettaDBConnector(dBConnector);
//		}
//		prepQuery.close();
//		return featureList;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public Map getFeatureHashByIds(String prepQueryStm, List<String> ids, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//		Map<String,FeatureList> featureMap = new HashMap<String, FeatureList>((int)Math.round(ids.size()*1.4)); 
//		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
//		FeatureList featureList;
//		for(String id: ids) {
//			prepQuery.setParams(id);
//			featureList = (FeatureList)prepQuery.execute(rsh);
//			if(featureList != null) {
//				featureList.setRosettaDBConnector(dBConnector);	
//			}
//			featureMap.put(id, featureList);
//		}
//		prepQuery.close();
//		return featureMap;
//	}
	
	@SuppressWarnings("unchecked")
	public FeatureList getFeatureListByIds(String prepQueryStm, List<String> ids, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		FeatureList featureList = new FeatureList(ids.size());
		Feature feature;
		for(String id: ids) {
			prepQuery.setParams(id);
			feature = (Feature)prepQuery.execute(rsh);
			if(feature != null) {
				feature.setRosettaDBConnector(dBConnector);	
			}
			featureList.add(feature);
		}
		prepQuery.close();
		return featureList;
	}
	
	@SuppressWarnings("unchecked")
	public List getListOfFeatureListByIds(String prepQueryStm, List<String> ids, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		List<FeatureList> featureList = new ArrayList<FeatureList>(ids.size());
		FeatureList featList;
		Object queryResult = null;
		for(String id: ids) {
			prepQuery.setParams(id);
			
			queryResult = prepQuery.execute(rsh);
			if(queryResult == null) {
				featList = null;
			}else {
				featList = new FeatureList((List<Feature>)queryResult);
				featList.setRosettaDBConnector(dBConnector);
			}
			featureList.add(featList);
		}
		prepQuery.close();
		return featureList;
	}
	
	
	/*
	@Deprecated
	public RosettaFeature getFeature(String prepQueryStm, String id, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		prepQuery.setParams(id);
		RosettaFeature feature = (RosettaFeature)prepQuery.execute(rsh);
		if(feature != null) {
			feature.setRosettaDBConnector(dBConnector);	
		}
		prepQuery.close();
		return feature;
	}
	@Deprecated
	@SuppressWarnings("unchecked")
	public RosettaFeatureList getFeatureList(String prepQueryStm, List<String> ids, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		RosettaFeatureList rosettaFeatureList = new RosettaFeatureList(ids.size());
		RosettaFeature rosettaFeature = null;
		for(String id: ids) {
			prepQuery.setParams(id);
			rosettaFeature = (RosettaFeature)prepQuery.execute(rsh);
			rosettaFeatureList.add(rosettaFeature);
//			if(rosettaFeature != null) {
//				rosettaFeatureList.add(rosettaFeature);
//			}
		}
		rosettaFeatureList.setRosettaDBConnector(dBConnector);
		prepQuery.close();
		return rosettaFeatureList;
	}
	

	
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<RosettaFeatureList> getListOfFeatureList(String prepQueryStm, List<String> ids, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		List<RosettaFeatureList> rosettaFeatureList = new ArrayList<RosettaFeatureList>(ids.size());
		RosettaFeatureList featList;
		for(String id: ids) {
			prepQuery.setParams(id);
			featList = new RosettaFeatureList((List<RosettaFeature>)prepQuery.execute(rsh));
			featList.setRosettaDBConnector(dBConnector);
			rosettaFeatureList.add(featList);
//			if(featList != null && featList.size()>0) {
//				featList.setRosettaDBConnector(dBConnector);
//				rosettaFeatureList.add(featList);
//			}
		}
		prepQuery.close();
		return rosettaFeatureList;
	}
	
	
	@Deprecated
	@SuppressWarnings("unchecked")
	public RosettaFeatureList getFeatureList(String prepQueryStm, String id, ResultSetHandler rsh) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		PreparedQuery prepQuery = dBConnector.getDbConnection().createSQLPrepQuery(prepQueryStm);
		prepQuery.setParams(id);
		RosettaFeatureList rosettaFeatureList = new RosettaFeatureList((List<RosettaFeature>)prepQuery.execute(rsh));
		rosettaFeatureList.setRosettaDBConnector(dBConnector);
		prepQuery.close();
		return rosettaFeatureList;
	}
	@Deprecated
	public Query createQuery() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return dBConnector.getDbConnection().createSQLQuery();
	}
	@Deprecated
	public Query createQuery(String query) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return dBConnector.getDbConnection().createSQLQuery(query);
	}
	@Deprecated
	public PreparedQuery createPreparedQuery(String preparedQuery) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		return dBConnector.getDbConnection().createSQLPrepQuery(preparedQuery);
	}
	
	*/
	
	/**
	 * @param dBConnector the dBConnector to set
	 */
	public void setRosettaDBConnector(DBConnector dBConnector) {
		this.dBConnector = dBConnector;
	}

	/**
	 * @return the dBConnector
	 */
	public DBConnector getRosettaDBConnector() {
		return dBConnector;
	}
}