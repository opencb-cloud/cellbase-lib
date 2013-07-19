package org.bioinfo.cellbase.lib.api.RegulatoryRegion;

import java.util.List;

import org.bioinfo.cellbase.lib.api.FeatureDBAdaptor;
import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.regulatory.Tfbs;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryOptions;
import org.bioinfo.cellbase.lib.impl.dbquery.QueryResponse;

public interface TfbsDBAdaptor extends RegulatoryRegionDBAdaptor {

	


//	public List<Tfbs> getAllByTfGeneName(String tfGeneName, String celltype, int start, int end);
//
//	public List<List<Tfbs>> getAllByTfGeneNameList(List<String> tfGeneNameList, String celltype, int start, int end);

	public QueryResponse getAllByTargetGeneId(String targetGeneId, QueryOptions options);

	public QueryResponse getAllByTargetGeneIdList(List<String> targetGeneIdList, QueryOptions options);
	
	
	public QueryResponse getAllByJasparId(String jasparId, QueryOptions options);

	public QueryResponse getAllByJasparIdList(List<String> jasparIdList, QueryOptions options);
	
	
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

//	public QueryResponse getAllByPosition(String chromosome, int position, QueryOptions options);


//	public List<Tfbs> getAllByInternalIdList(List<String> idList);
//
//	public List<Tfbs> getAllByInternalId(String id);
	

	public List<Object> getAllAnnotation();

	public List<Object> getAllAnnotationByCellTypeList(List<String> cellTypes);

	
	public List<IntervalFeatureFrequency> getAllTfIntervalFrequencies(Region region, int interval);
}
