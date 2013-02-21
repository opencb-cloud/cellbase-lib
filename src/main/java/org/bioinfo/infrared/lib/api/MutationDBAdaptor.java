package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.MutationPhenotypeAnnotation;
import org.bioinfo.infrared.lib.common.IntervalFeatureFrequency;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface MutationDBAdaptor {
	
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByGeneName(String geneName);
	
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByGeneNameList(List<String> geneNameList);
	
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByEnsemblTranscript(String ensemblTranscript);
	
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByEnsemblTranscriptList(List<String> ensemblTranscriptList);
	
	
	public List<MutationPhenotypeAnnotation> getAllMutationPhenotypeAnnotationByPosition(Position position);
	
	public List<List<MutationPhenotypeAnnotation>> getAllMutationPhenotypeAnnotationByPositionList(List<Position> position);
	
	
	
	public List<MutationPhenotypeAnnotation> getAllByRegion(Region region);
	
	public List<List<MutationPhenotypeAnnotation>> getAllByRegionList(List<Region> regionList);

	
	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval);
	
}
