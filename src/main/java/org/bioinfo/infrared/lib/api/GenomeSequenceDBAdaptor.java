package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.common.GenomeSequenceFeature;
import org.bioinfo.infrared.lib.common.Region;

public interface GenomeSequenceDBAdaptor {

	
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end);

	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand);
	

	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions);

	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions, int strand);

	public String getRevComp(String sequence);

	
}
