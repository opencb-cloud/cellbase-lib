package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.GenomeSequenceFeature;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.infrared.core.cellbase.GenomeSequence;

public interface GenomeSequenceDBAdaptor {

	
	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end);

	public GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand);
	

	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions);

	public List<GenomeSequenceFeature> getByRegionList(List<Region> regions, int strand);

	public String getRevComp(String sequence);

	
}
