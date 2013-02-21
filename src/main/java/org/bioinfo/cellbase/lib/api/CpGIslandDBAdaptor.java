package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.infrared.core.cellbase.CpGIsland;

public interface CpGIslandDBAdaptor {
	
	public List< CpGIsland> getAllByRegion(Region region);
	
	public List<List<CpGIsland>> getAllByRegionList(List<Region> regionList);
	
	
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval);
}
