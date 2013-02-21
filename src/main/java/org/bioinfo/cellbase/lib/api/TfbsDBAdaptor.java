package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.Tfbs;

public interface TfbsDBAdaptor extends FeatureDBAdaptor {

	
	public List<Tfbs> getAllByTfGeneName(String tfGeneName, String celltype, int start, int end);

	public List<List<Tfbs>> getAllByTfGeneNameList(List<String> tfGeneNameList, String celltype, int start, int end);

	public List<Tfbs> getAllByTargetGeneName(String targetGeneName);

	public List<List<Tfbs>> getAllByTargetGeneNameList(List<String> targetGeneNameList);
	
	
	public List<Protein> getTfInfoByTfGeneName(String tfGeneName);
	
	public List<List<Protein>> getTfInfoByTfGeneNameList(List<String> tfGeneNameList);


	public List<Pwm> getAllPwmByTfGeneName(String tfName);
	
	public List<List<Pwm>> getAllPwmByTfGeneNameList(List<String> tfNameList);

	
	public List<Tfbs> getAllByRegion(String chromosome);

	public List<Tfbs> getAllByRegion(String chromosome, int start);

	public List<Tfbs> getAllByRegion(String chromosome, int start, int end);

	public List<Tfbs> getAllByRegion(Region region);

	public List<List<Tfbs>> getAllByRegionList(List<Region> regionList);

	public List<Tfbs> getAllByInternalIdList(List<String> idList);

	public List<Tfbs> getAllByInternalId(String id);
	

	public List<Object> getAllAnnotation();

	public List<Object> getAllAnnotationByCellTypeList(List<String> cellTypes);

	
	public List<IntervalFeatureFrequency> getAllTfIntervalFrequencies(Region region, int interval);
}
