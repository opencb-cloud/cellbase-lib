package org.bioinfo.infrared.lib.api;

import java.util.List;


import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.lib.common.Region;

public interface MutationDBAdaptor {
	
	public List<MutationPhenotypeAnnotation> getAllByRegion(Region region);
	
	public List<List<MutationPhenotypeAnnotation>> getAllByRegionList(List<Region> regionList);
	
}
