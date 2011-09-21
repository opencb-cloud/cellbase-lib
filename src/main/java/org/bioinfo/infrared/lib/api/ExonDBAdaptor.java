package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.Exon;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface ExonDBAdaptor extends FeatureDBAdaptor {

	
	public List<Exon> getAll();

	public List<String> getAllEnsemblIds();

	public Exon getByEnsemblId(String ensemblId);
	
	public List<Exon> getAllByEnsemblIdList(List<String> ensemblIds);

	public List<Exon> getAllById(String id);

	public List<List<Exon>> getAllByIdList(List<String> ids);
	
	public List<Exon> getByEnsemblTranscriptId(String transcriptId);
	
	public List<List<Exon>> getByEnsemblTranscriptIdList(List<String> transcriptIds);

	public List<Exon> getByEnsemblGeneId(String geneId);
	
	public List<List<Exon>> getByEnsemblGeneIdList(List<String> geneIds);
	
	
//	public List<Exon> getAllByBiotype(String biotype);
//	
//	public List<Exon> getAllByBiotypeList(List<String> biotypeList);
	

	public List<Exon> getAllByPosition(String chromosome, int position);

	public List<Exon> getAllByPosition(Position position);

	public List<List<Exon>> getAllByPositionList(List<Position> positions);
	

	public List<Exon> getAllByRegion(String chromosome);

	public List<Exon> getAllByRegion(String chromosome, int start);

	public List<Exon> getAllByRegion(String chromosome, int start, int end);

//	public List<Exon> getAllByRegion(String chromosome, int start, int end, List<String> biotypes);

	public List<Exon> getAllByRegion(Region region);

//	public List<Exon> getAllByRegion(Region region, List<String> biotypes);

	public List<List<Exon>> getAllByRegionList(List<Region> regions);
	
//	public List<List<Exon>> getAllByRegionList(List<Region> regions, List<String> biotypes);

	public List<Exon> getAllByCytoband(String chromosome, String cytoband);
	
	
	public List<Exon> getAllBySnpId(String snpId);
	
	public List<List<Exon>> getAllBySnpIdList(List<String> snpIds);

	
}
