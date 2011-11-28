package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Protein;

public interface ProteinDBAdaptor extends FeatureDBAdaptor {

	
	@Override
	public List<Protein> getAll();

	
	public List<String> getAllUniprotIds();

	public Protein getById(String id);

	public List<Protein> getAllByIdList(List<String> idList);
	
	public List<String> getAllEnsemblIds();

	public Protein getByEnsemblId(String ensemblId);
	
	public List<Protein> getAllByEnsemblIdList(List<String> ensemblIdList);

	public Protein getByEnsemblTranscriptId(String transcriptId);
	
	public List<Protein> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList);
	
	
}
