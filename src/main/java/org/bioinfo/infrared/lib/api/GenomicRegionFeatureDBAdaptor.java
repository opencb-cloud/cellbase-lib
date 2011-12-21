package org.bioinfo.infrared.lib.api;

import java.util.HashMap;
import java.util.List;

import org.bioinfo.infrared.lib.common.GenomicRegionFeatures;
import org.bioinfo.infrared.lib.common.Region;


// Como no es una fetaure no deberia implementas FeatureDBAdaptor no?
//public interface FeatureMapDBAdaptor extends FeatureDBAdaptor {	

public interface GenomicRegionFeatureDBAdaptor {
	
	
	public GenomicRegionFeatures getByRegion(String chromosome, int start, int end);
	
	public GenomicRegionFeatures getByRegion(Region region);
	
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions);

	
	public GenomicRegionFeatures getByRegion(Region region, List<String> sources);	// sources: gene, exon, snp, ...
	
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions, List<String> sources);

	public HashMap<String, List<String>> getConsequenceType(String chromosome, int position);

	HashMap<String, List<String>> getConsequenceType(String chromosome,int position, String alternativeAllele);
	
	
//	public GenomicRegionFeatures getAllByRegion(Region region, List<String> featureTypes); // types: variation, regulatory, core, ...
//	public List<GenomicRegionFeatures> getAllByRegion(List<Region> regions, List<String> featureTypes);

	
}
