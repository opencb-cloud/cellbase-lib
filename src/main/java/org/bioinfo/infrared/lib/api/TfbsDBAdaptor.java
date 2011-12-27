package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.lib.common.Region;

public interface TfbsDBAdaptor extends FeatureDBAdaptor {

	public List<Tfbs> getAllByRegion(String chromosome);

	public List<Tfbs> getAllByRegion(String chromosome, int start);

	public List<Tfbs> getAllByRegion(String chromosome, int start, int end);

	public List<Tfbs> getAllByRegion(Region region);

	public List<List<Tfbs>> getAllByRegionList(List<Region> regionList);

	public List<Tfbs> getAllByInternalIdList(List<String> idList);

	public List<Tfbs> getAllByInternalId(String id);
	


	public List<Tfbs> getAllByTfGeneName(String geneName);

	public List<List<Tfbs>> getAllByTfGeneNameList(List<String> geneNameList);

	public List<Tfbs> getAllByTargetGeneStableId(String stableId);

	public List<List<Tfbs>> getAllByTargetGeneStableIdList(List<String> ids);


	public List<Pwm> getPwmByTfName(String tfName);
	
	public List<List<Pwm>> getPwmByTfNameList(List<String> tfNameList);

	
	public List<Object> getAllAnnotation();

	public List<Object> getAllAnnotationByCellTypeList(List<String> cellTypes);

}
