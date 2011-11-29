package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.Gene;
import org.bioinfo.infrared.lib.common.Position;
import org.bioinfo.infrared.lib.common.Region;

public interface ExonDBAdaptor extends FeatureDBAdaptor {

	
	@Override
	public List<Exon> getAll();

	
	public List<String> getAllEnsemblIds();

	public Exon getByEnsemblId(String ensemblId);
	
	public List<Exon> getAllByEnsemblIdList(List<String> ensemblIdList);
	
	public List<Exon> getByEnsemblTranscriptId(String transcriptId);
	
	public List<List<Exon>> getByEnsemblTranscriptIdList(List<String> transcriptIdList);

	public List<Exon> getByEnsemblGeneId(String geneId);
	
	public List<List<Exon>> getByEnsemblGeneIdList(List<String> geneIdList);
	
	
	public List<Exon> getAllByPosition(String chromosome, int position);

	public List<Exon> getAllByPosition(Position position);

	public List<List<Exon>> getAllByPositionList(List<Position> positionList);
	

	public List<Exon> getAllByRegion(String chromosome);

	public List<Exon> getAllByRegion(String chromosome, int start);

	public List<Exon> getAllByRegion(String chromosome, int start, int end);
	

	public List<Exon> getAllByRegion(Region region);

	public List<List<Exon>> getAllByRegionList(List<Region> regionList);
	
	public List<Exon> getAllByCytoband(String chromosome, String cytoband);
	
	
	public List<Exon> getAllBySnpId(String snpId);
	
	public List<List<Exon>> getAllBySnpIdList(List<String> snpIdList);

	
}
