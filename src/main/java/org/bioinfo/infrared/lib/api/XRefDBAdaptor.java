package org.bioinfo.infrared.lib.api;

import java.util.List;
import java.util.Map;

import org.bioinfo.infrared.coreold.feature.DBName;
import org.bioinfo.infrared.lib.common.XRefs;

public interface XRefDBAdaptor {

	public List<DBName> getAllDBNamesById(String id);
	
	// Returns all possible DB names
	public List<DBName> getAllDBNames();

	// Returns all possible DB names by type
	public List<DBName> getAllDBNamesByType(String type);

	// Returns an XRefs object according to a single ID and DB (used in FeatureId.java)
	public XRefs getByDBName(String id, String dbname);

	// Returns a list of XRefs objects according to a list of IDs and single DB (used in FeatureId.java)
	public List<XRefs> getByDBName(List<String> ids, String dbname);

	// Returns an XRefs object according to a single ID in the specified list of DDBB (used in FeatureId.java)
	public XRefs getByDBName(String id, List<String> dbnames);

	// Returns a list of XRefs objects according to a list of ID in the specified list of DDBB (used in FeatureId.java)
	public List<XRefs> getByDBName(List<String> ids, List<String> dbnames);


	// Returns a list of XRefs objects with all the possible identifiers for every single ID in a list (used in FeatureId.java)
	public List<XRefs> getAllIdentifiersByIds(List<String> ids);


	public List<XRefs> getAllFunctionalAnnotByIds(List<String> ids);

//	private List<XRefs> getAllXRefsByPrepQuery(List<String> ids, String prepQuerySql);

	@Deprecated
	public List<XRefs> getByDBNameOld(String id, String dbname);

	public List<String> getAllIdsByDBName(String dbname);

	@Deprecated
	public List<String> getIdsByDBName(List<String> ids, String dbname);

	//	public List<List<String>> getIdsByDBName(List<String> ids, List<String> dbnames) throws Exception{
	//		List<List<String>> xrefs = new ArrayList<List<String>>(ids.size());
	//		for(String id: ids) {
	//			getStringListListByIds(GET_ALL_IDS_BY_IDS+dbname+"' group by x2.display_id ", ids);
	//		}
	//		return xrefs;
	//	}

	@Deprecated
	public Map<String, List<XRefs>> getByDBNames(String id, List<String> dbnames);

	@Deprecated
	public List<Map<String, List<XRefs>>> getListByDBNames(List<String> ids, List<String> dbnames);
	
}
