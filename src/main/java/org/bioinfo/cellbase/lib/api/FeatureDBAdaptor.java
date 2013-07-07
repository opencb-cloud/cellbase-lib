package org.bioinfo.cellbase.lib.api;

import java.util.List;
import java.util.Map;

import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResult;


public interface FeatureDBAdaptor {

	
	public QueryResult getAll();
	
	public List<String> getAllIds();

	
	public Map<String, Object> getInfo(String id);
	
	public List<Map<String, Object>> getInfoByIdList(List<String> idList);
	
	public Map<String, Object> getFullInfo(String id);
	
	public List<Map<String, Object>> getFullInfoByIdList(List<String> idList);
	
	
	
	
	public Region getRegionById(String id);
	
	public List<Region> getAllRegionsByIdList(List<String> idList);
	
	
	public String getSequenceById(String id);

	public List<String> getAllSequencesByIdList(List<String> idList);

}
