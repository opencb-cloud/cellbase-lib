package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.core.cellbase.Pwm;
import org.bioinfo.infrared.core.cellbase.Tfbs;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface TfbsDBAdaptor extends FeatureDBAdaptor {

	public List<Tfbs> getAllByRegion(String chromosome);

	public List<Tfbs> getAllByRegion(String chromosome, int start);

	public List<Tfbs> getAllByRegion(String chromosome, int start, int end);

	public List<Tfbs> getAllByRegion(Region region);

	public List<List<Tfbs>> getAllByRegionList(List<Region> regionList);

	public List<Tfbs> getAllByInternalIdList(List<String> idList);

	public List<Tfbs> getAllByInternalId(String id);
	


	public List<Tfbs> getAllByTfGeneName(String id);

	public List<List<Tfbs>> getAllByTfGeneName(List<String> ids);

	public List<Tfbs> getAllByTargetGeneStableId(String id);

	public List<List<Tfbs>> getAllByTargetGeneStableId(List<String> ids);

	public List<Object> getAnnotation();

	public List<Object> getAnnotation(List<String> cellTypes);

	public List<Pwm> getPwnByTfName(String tfName);
	
	public List<List<Pwm>> getPwnByTfName(List<String> tfName);

}
