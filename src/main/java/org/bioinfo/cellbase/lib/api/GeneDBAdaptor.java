package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.IntervalFeatureFrequency;
import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.cellbase.lib.common.core.Gene;

public interface GeneDBAdaptor extends FeatureDBAdaptor {

	
//	@Override
	
	public List<Gene> getAll(List<String> biotype, Boolean id);
		
	public List<String> getAllEnsemblIds();

	public Gene getByEnsemblId(String ensemblId);
	
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIdList);

	public Gene getByEnsemblId(String ensemblId, boolean fetchTranscriptsAndExons);
	
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIdList, boolean fetchTranscriptsAndExons);
	
	public List<Gene> getAllByName(String name);

	public List<List<Gene>> getAllByNameList(List<String> nameList);
	
	public Gene getByEnsemblTranscriptId(String transcriptId);
	
	public List<Gene> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList);

	
	public List<Gene> getAllByXref(String xref);
	
	public List<Gene> getAllByBiotype(String biotype);
	
	public List<Gene> getAllByBiotypeList(List<String> biotypeList);
	

	public List<Gene> getAllByPosition(String chromosome, int position);

	public List<Gene> getAllByPosition(Position position);

	public List<List<Gene>> getAllByPositionList(List<Position> positionList);
	

	public List<Gene> getAllByRegion(String chromosome);

	public List<Gene> getAllByRegion(String chromosome, int start);

	public List<Gene> getAllByRegion(String chromosome, int start, int end);

	public List<Gene> getAllByRegion(String chromosome, int start, int end, List<String> biotypeList);

	public List<Gene> getAllByRegion(Region region);

	public List<Gene> getAllByRegion(Region region, List<String> biotypeList);

	public List<List<Gene>> getAllByRegionList(List<Region> regionList);
	
	public List<List<Gene>> getAllByRegionList(List<Region> regions, List<String> biotypeList);

	public List<Gene> getAllByCytoband(String chromosome, String cytoband);
	
	
	public List<Gene> getAllBySnpId(String snpId);
	
	public List<List<Gene>> getAllBySnpIdList(List<String> snpIdList);


	public List<Gene> getAllByTf(String id);

	public List<List<Gene>> getAllByTfList(List<String> idList);

	public List<Gene> getAllByTfName(String tfName);

	public List<List<Gene>> getAllByTfNameList(List<String> tfNameList);
	
	public List<Gene> getAllTargetsByTf(String id);

	public List<List<Gene>> getAllTargetsByTfList(List<String> idList);
	

	public List<Gene> getAllByMiRnaMature(String mirbaseId);

	public List<List<Gene>> getAllByMiRnaMatureList(List<String> mirbaseIds);
	
	public List<Gene> getAllTargetsByMiRnaMature(String mirbaseId);

	public List<List<Gene>> getAllTargetsByMiRnaMatureList(List<String> mirbaseIds);


	public List<IntervalFeatureFrequency> getAllIntervalFrequencies(Region region, int interval);

}
