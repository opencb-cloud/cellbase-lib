package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.StructuralVariation;
import org.bioinfo.infrared.lib.common.Region;

public interface StructuralVariationDBAdaptor {
	
	public List< StructuralVariation> getAllByRegion(Region region);
	
	public List<List<StructuralVariation>> getAllByRegionList(List<Region> regionList);
	
}
