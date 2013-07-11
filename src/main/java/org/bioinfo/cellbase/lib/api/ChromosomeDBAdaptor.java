package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;


public interface ChromosomeDBAdaptor {

	public QueryResponse getAll(QueryOptions options);

	public QueryResponse getById(String id, QueryOptions options);

	public QueryResponse getAllByIdList(List<String> idList, QueryOptions options);

//	List<Cytoband> getCytobandByName(String name);
//	List<List<Cytoband>> getCytobandByNameList(List<String> nameList);
//	List<String> getChromosomeNames();
}
