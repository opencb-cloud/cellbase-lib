package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.CpGIsland;
import org.bioinfo.infrared.lib.common.Region;

public interface CpGIslandDBAdaptor {
	public List< CpGIsland> getAllByRegion(Region region);
	public List<List<CpGIsland>> getAllByRegionList(List<Region> regionList);
}
