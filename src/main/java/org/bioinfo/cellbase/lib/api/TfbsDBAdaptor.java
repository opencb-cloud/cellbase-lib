package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.regulatory.Tfbs;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;

public interface TfbsDBAdaptor extends FeatureDBAdaptor {

	
	public QueryResponse getAllById(String id, QueryOptions options);

	public QueryResponse getAllByIdList(List<String> idList, QueryOptions options);
	
	
//	public List<Tfbs> getAllByTfGeneName(String tfGeneName, String celltype, int start, int end);
//
//	public List<List<Tfbs>> getAllByTfGeneNameList(List<String> tfGeneNameList, String celltype, int start, int end);

	public QueryResponse getAllByTargetGeneId(String targetGeneId);

	public QueryResponse getAllByTargetGeneIdList(List<String> targetGeneIdList);
	
	
	public QueryResponse getAllByJasparId(String jasparId);

	public QueryResponse getAllByJasparIdList(List<String> jasparIdList);
	
	
//	public List<Protein> getTfInfoByTfGeneName(String tfGeneName);
//	
//	public List<List<Protein>> getTfInfoByTfGeneNameList(List<String> tfGeneNameList);

//	public List<Pwm> getAllPwmByTfGeneName(String tfName);
//	
//	public List<List<Pwm>> getAllPwmByTfGeneNameList(List<String> tfNameList);

	
//	public List<Tfbs> getAllByRegion(String chromosome);
//
//	public List<Tfbs> getAllByRegion(String chromosome, int start);
//
//	public List<Tfbs> getAllByRegion(String chromosome, int start, int end);
//
//	public List<Tfbs> getAllByRegion(Region region);
//
//	public List<List<Tfbs>> getAllByRegionList(List<Region> regionList);

	public QueryResponse getAllByPosition(String chromosome, int position, QueryOptions options);

	public QueryResponse getAllByPosition(Position position, QueryOptions options);
	
	public QueryResponse getAllByPositionList(List<Position> positionList, QueryOptions options);
	
	
	public QueryResponse getAllByRegion(String chromosome, int start, int end, QueryOptions options);

	public QueryResponse getAllByRegion(Region region, QueryOptions options);

	public QueryResponse getAllByRegionList(List<Region> regions, QueryOptions options);
	
	
//	public List<Tfbs> getAllByInternalIdList(List<String> idList);
//
//	public List<Tfbs> getAllByInternalId(String id);
	

	public List<Object> getAllAnnotation();

	public List<Object> getAllAnnotationByCellTypeList(List<String> cellTypes);

	
	public List<IntervalFeatureFrequency> getAllTfIntervalFrequencies(Region region, int interval);
}
