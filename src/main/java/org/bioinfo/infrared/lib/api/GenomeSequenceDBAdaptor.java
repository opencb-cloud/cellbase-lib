package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.lib.common.GenomeSequenceFeature;
import org.bioinfo.infrared.lib.common.Region;

public interface GenomeSequenceDBAdaptor {

	
	GenomeSequenceFeature getByRegion(String chromosome, int start, int end);

	GenomeSequenceFeature getByRegion(String chromosome, int start, int end, int strand);
	

	List<GenomeSequenceFeature> getByRegionList(List<Region> regions);

	List<GenomeSequenceFeature> getByRegionList(List<Region> regions, int strand);

	String getRevComp(String sequence);

}
