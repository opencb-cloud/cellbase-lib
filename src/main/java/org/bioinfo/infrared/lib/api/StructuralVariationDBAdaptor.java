package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.StructuralVariation;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Region;

public interface StructuralVariationDBAdaptor {
	
	
	public List< StructuralVariation> getAllByRegion(Region region);
	
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList);
	
	
	public List< StructuralVariation> getAllByRegion(Region region, int minLength, int maxLength);
	
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList, int minLength, int maxLength);
	
	
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval);
	
}
