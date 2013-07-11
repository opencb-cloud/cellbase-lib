package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.GenomicVariant;
import org.bioinfo.cellbase.lib.common.Region;


// Como no es una fetaure no deberia implementas FeatureDBAdaptor no?
//public interface FeatureMapDBAdaptor extends FeatureDBAdaptor {	

@Deprecated
public interface GenomicRegionFeatureDBAdaptor {
	
	
	public String getByRegion(String chromosome, int start, int end);
	
	public String getByRegion(Region region);
	
	public List<String> getAllByRegionList(List<Region> regions);

	
	public String getByRegion(Region region, List<String> sources);	// sources: gene, exon, snp, ...
	
	public List<String> getAllByRegionList(List<Region> regions, List<String> sources);

	public List<String> getByVariants(List<GenomicVariant> variants, List<String> sources);

	List<String> getByVariants(List<GenomicVariant> variants);
	
//	public List<FeatureMap> getFeatureMapsByRegion(Region region);

//	public HashMap<String, List<String>> getConsequenceType(String chromosome, int position);
//
//	HashMap<String, List<String>> getConsequenceType(String chromosome,int position, String alternativeAllele);
	
	
//	public GenomicRegionFeatures getAllByRegion(Region region, List<String> featureTypes); // types: variation, regulatory, core, ...
//	public List<GenomicRegionFeatures> getAllByRegion(List<Region> regions, List<String> featureTypes);

	
}
