package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Transcript;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;

public interface TranscriptDBAdaptor extends FeatureDBAdaptor {

	

    public QueryResponse getAllById(String id, QueryOptions options);

    public QueryResponse getAllByIdList(List<String> idList, QueryOptions options);

    public QueryResponse next(String id, QueryOptions options);

	
//	public List<String> getAllEnsemblIds();
//
//	public Transcript getByEnsemblId(String ensemblId);
//	
//	public List<Transcript> getAllByEnsemblIdList(List<String> ensemblIdList);
//
//	public List<List<Transcript>> getAllByName(String name, List<String> exclude);
//
//	public List<List<List<Transcript>>> getAllByNameList(List<String> nameList, List<String> exclude);
//	
//	public List<Transcript> getByEnsemblGeneId(String ensemblGeneId);
//	
//	public List<List<Transcript>> getByEnsemblGeneIdList(List<String> ensemblGeneIdList);

	
//	public List<Transcript> getAllByBiotype(String biotype);
//	
//	public List<Transcript> getAllByBiotypeList(List<String> biotypeList);
	

//	public List<Transcript> getAllByPosition(String chromosome, int position);
//
//	public List<Transcript> getAllByPosition(Position position);

	public QueryResponse getAllByPosition(String chromosome, int position, QueryOptions options);
	
	public QueryResponse getAllByPosition(Position position, QueryOptions options);
	
//	public List<List<Transcript>> getAllByPositionList(List<Position> positionList);
	
	public QueryResponse getAllByPositionList(List<Position> positionList, QueryOptions options);

	
//	public List<Transcript> getAllByRegion(String chromosome);
//
//	public List<Transcript> getAllByRegion(String chromosome, int start);
//
//	public List<Transcript> getAllByRegion(String chromosome, int start, int end);
//
//	public List<Transcript> getAllByRegion(String chromosome, int start, int end, List<String> biotypeList);
	
	public QueryResponse getAllByRegion(String chromosome, int start, int end, QueryOptions options);

//	public List<Transcript> getAllByRegion(Region region);
//
//	public List<Transcript> getAllByRegion(Region region, List<String> biotypeList);

	public QueryResponse getAllByRegion(Region region, QueryOptions options);
	
	
//	public List<List<Transcript>> getAllByRegionList(List<Region> regionList);
//	
//	public List<List<Transcript>> getAllByRegionList(List<Region> regions, List<String> biotypeList);

	public QueryResponse getAllByRegionList(List<Region> regions, QueryOptions options);
	
	
	
//	public List<Transcript> getAllByCytoband(String chromosome, String cytoband);
//	
//	
//	public List<Transcript> getAllBySnpId(String snpId);
//	
//	public List<List<Transcript>> getAllBySnpIdList(List<String> snpIdList);


	public QueryResponse getAllByEnsemblExonId(String ensemblExonId, QueryOptions options);

	public QueryResponse getAllByEnsemblExonIdList(List<String> ensemblExonIdList, QueryOptions options);
	
	
	public QueryResponse getAllByTFBSId(String tfbsId, QueryOptions options);

	public QueryResponse getAllByTFBSIdList(List<String> tfbsIdList, QueryOptions options);
	
	
	public List<Transcript> getAllByProteinName(String proteinName);
	
	public List<List<Transcript>> getAllByProteinNameList(List<String> proteinNameList);
	
	
	public List<Transcript> getAllByMirnaMature(String mirnaID);
	
	public List<List<Transcript>> getAllByMirnaMatureList(List<String> mirnaIDList);
	
	
}
