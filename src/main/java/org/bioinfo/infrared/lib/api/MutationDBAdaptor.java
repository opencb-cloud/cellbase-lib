package org.bioinfo.infrared.lib.api;

import java.util.List;


import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.core.cellbase.SnpPhenotypeAnnotation;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface MutationDBAdaptor {
	
	
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByPosition(Position position);
	
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByPositionList(List<Position> position);
	
	
	
	public List<MutationPhenotypeAnnotation> getAllByRegion(Region region);
	
	public List<List<MutationPhenotypeAnnotation>> getAllByRegionList(List<Region> regionList);

	
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval);
	
}
