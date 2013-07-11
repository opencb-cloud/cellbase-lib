package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;


public interface GenomeSequenceDBAdaptor {

	
	public QueryResponse getByRegion(String chromosome, int start, int end, QueryOptions options);

	public QueryResponse getAllByRegionList(List<Region> regions, QueryOptions options);

	public String getRevComp(String sequence);

	
}
