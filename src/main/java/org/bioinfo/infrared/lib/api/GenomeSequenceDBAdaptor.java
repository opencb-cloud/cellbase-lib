package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.GenomeSequence;
import org.bioinfo.infrared.lib.common.Region;

public interface GenomeSequenceDBAdaptor {

	
	GenomeSequence getByRegion(String chromosome, int start, int end);

	GenomeSequence getByRegion(String chromosome, int start, int end, int strand);
	

	List<GenomeSequence> getByRegionList(List<Region> regions);

	List<GenomeSequence> getByRegionList(List<Region> regions, int strand);

	String getRevComp(String sequence);

}
