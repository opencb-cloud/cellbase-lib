package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Cytoband;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.common.Region;

public interface CytobandDBAdaptor extends FeatureDBAdaptor {

	
	public List<Cytoband> getAllByRegion(String chromosome);

	public List<Cytoband> getAllByRegion(String chromosome, int start);

	public List<Cytoband> getAllByRegion(String chromosome, int start, int end);

	public List<Cytoband> getAllByRegion(Region region);

	public List<List<Cytoband>> getAllByRegionList(List<Region> regionList);

	List<String> getAllChromosomesName();
	

	

	
}
