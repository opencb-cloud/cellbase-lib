package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.Gene;
import org.bioinfo.infrared.coreold.feature.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface GeneDBAdaptor extends FeatureDBAdaptor {

	
	public List<Gene> getGeneById(String geneId);
	
	public List<List<Gene>> getGeneByIdList(List<String> geneIdList);
	
	public List<String> getAllEnsemblIds();

	public List<Gene> getAll();

	public Gene getByEnsemblId(String ensemblId);

	public Gene getByEnsemblTranscriptId(String transcriptId);

	public List<Gene> getAllByExternalId(String id);

	public List<List<Gene>> getAllByExternalIds(List<String> ids);

	public List<Gene> getAllByBiotype(String biotype);

	public List<Gene> getAllByPosition(String chromosome, int position);

	public List<Gene> getAllByPosition(Position position);

	public List<List<Gene>> getAllByPositions(List<Position> positions);

	public List<Gene> getAllByRegion(String chromosome);

	public List<Gene> getAllByRegion(String chromosome, int start);

	public List<Gene> getAllByRegion(String chromosome, int start, int end);

	public List<Gene> getAllByRegion(String chromosome, int start, int end, List<String> biotypes);

	public List<Gene> getAllByRegion(Region region);

	public List<Gene> getAllByRegion(Region region, List<String> biotypes);

	public List<List<Gene>> getAllByRegions(List<Region> regions);

	public List<Gene> getGeneListBySNP(String snpId);

	public String getSequenceByEnsemblId(String ensemblId);

	public List<Gene> test(String snpId);


}
