package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.variation.StructuralVariation;

public interface StructuralVariationDBAdaptor {
	
	
	public List< StructuralVariation> getAllByRegion(Region region);
	
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList);
	
	
	public List< StructuralVariation> getAllByRegion(Region region, int minLength, int maxLength);
	
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList, int minLength, int maxLength);
	
	
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval);
	
}
