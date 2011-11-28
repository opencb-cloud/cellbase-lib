package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface GeneDBAdaptor extends FeatureDBAdaptor {

	
	@Override
	public List<Gene> getAll();
	
	
	public List<String> getAllEnsemblIds();

	public Gene getByEnsemblId(String ensemblId);
	
	public List<Gene> getAllByEnsemblIdList(List<String> ensemblIdList);

	public List<Gene> getAllByName(String name);

	public List<List<Gene>> getAllByNameList(List<String> nameList);
	
	public Gene getByEnsemblTranscriptId(String transcriptId);
	
	public List<Gene> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList);

	
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

}
