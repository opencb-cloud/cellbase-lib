package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.Transcript;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface TranscriptDBAdaptor extends FeatureDBAdaptor {

	
	public List<Transcript> getAll();

	public List<String> getAllEnsemblIds();

	public Transcript getByEnsemblId(String ensemblId);
	
	public List<Transcript> getAllByEnsemblIdList(List<String> ensemblIds);

	public List<Transcript> getAllById(String id);

	public List<List<Transcript>> getAllByIdList(List<String> ids);
	
	public List<Transcript> getByEnsemblGeneId(String ensemblGeneId);
	
	public List<List<Transcript>> getByEnsemblGeneIdList(List<String> ensemblGeneIds);

	
	public List<Transcript> getAllByBiotype(String biotype);
	
	public List<Transcript> getAllByBiotypeList(List<String> biotypeList);
	

	public List<Transcript> getAllByPosition(String chromosome, int position);

	public List<Transcript> getAllByPosition(Position position);

	public List<List<Transcript>> getAllByPositionList(List<Position> positions);
	

	public List<Transcript> getAllByRegion(String chromosome);

	public List<Transcript> getAllByRegion(String chromosome, int start);

	public List<Transcript> getAllByRegion(String chromosome, int start, int end);

	public List<Transcript> getAllByRegion(String chromosome, int start, int end, List<String> biotypes);

	public List<Transcript> getAllByRegion(Region region);

	public List<Transcript> getAllByRegion(Region region, List<String> biotypes);

	public List<List<Transcript>> getAllByRegionList(List<Region> regions);
	
	public List<List<Transcript>> getAllByRegionList(List<Region> regions, List<String> biotypes);

	public List<Transcript> getAllByCytoband(String chromosome, String cytoband);
	
	
	public List<Transcript> getAllBySnpId(String snpId);
	
	public List<List<Transcript>> getAllBySnpIdList(List<String> snpIds);
	
	
}
