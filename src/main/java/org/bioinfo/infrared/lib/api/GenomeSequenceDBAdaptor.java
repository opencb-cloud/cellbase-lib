package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.common.Region;

public interface GenomeSequenceDBAdaptor {

	
	public GenomeSequence getByRegion(String chromosome, int start, int end);

	public GenomeSequence getByRegion(String chromosome, int start, int end, int strand);
	

	public List<GenomeSequence> getByRegionList(List<Region> regions);

	public List<GenomeSequence> getByRegionList(List<Region> regions, int strand);

	public String getRevComp(String sequence);

	
}
