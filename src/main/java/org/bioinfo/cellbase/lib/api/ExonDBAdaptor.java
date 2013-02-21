package org.bioinfo.cellbase.lib.api;

import java.util.List;

import org.bioinfo.cellbase.lib.common.Position;
import org.bioinfo.cellbase.lib.common.Region;
import org.bioinfo.infrared.core.cellbase.Exon;
import org.bioinfo.infrared.core.cellbase.ExonToTranscript;

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


	List<String> getAllSequencesByIdList(List<String> ensemblIdList, int strand);

	
	
	public List<ExonToTranscript> getAllExonToTranscriptByEnsemblGeneId(String geneId);
	
	public List<List<ExonToTranscript>> getAllExonToTranscriptByEnsemblGeneIdList(List<String> geneIdList);
	
	public List<ExonToTranscript> getAllExonToTranscriptByEnsemblTranscriptId(String transcriptId);
	
	public List<List<ExonToTranscript>> getAllExonToTranscriptByEnsemblTranscriptIdList(List<String> transcriptIdList);
	
	
}
