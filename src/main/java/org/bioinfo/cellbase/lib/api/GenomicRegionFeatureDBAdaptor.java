package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.GenomicVariant;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.impl.hibernate.GenomicRegionFeatures;
import org.bioinfo.infrared.core.cellbase.FeatureMap;


// Como no es una fetaure no deberia implementas FeatureDBAdaptor no?
//public interface FeatureMapDBAdaptor extends FeatureDBAdaptor {	

public interface GenomicRegionFeatureDBAdaptor {
	
	
	public GenomicRegionFeatures getByRegion(String chromosome, int start, int end);
	
	public GenomicRegionFeatures getByRegion(Region region);
	
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions);

	
	public GenomicRegionFeatures getByRegion(Region region, List<String> sources);	// sources: gene, exon, snp, ...
	
	public List<GenomicRegionFeatures> getAllByRegionList(List<Region> regions, List<String> sources);

	public List<GenomicRegionFeatures> getByVariants(List<GenomicVariant> variants, List<String> sources);

	List<GenomicRegionFeatures> getByVariants(List<GenomicVariant> variants);
	
	public List<FeatureMap> getFeatureMapsByRegion(Region region);

//	public HashMap<String, List<String>> getConsequenceType(String chromosome, int position);
//
//	HashMap<String, List<String>> getConsequenceType(String chromosome,int position, String alternativeAllele);
	
	
//	public GenomicRegionFeatures getAllByRegion(Region region, List<String> featureTypes); // types: variation, regulatory, core, ...
//	public List<GenomicRegionFeatures> getAllByRegion(List<Region> regions, List<String> featureTypes);

	
}
