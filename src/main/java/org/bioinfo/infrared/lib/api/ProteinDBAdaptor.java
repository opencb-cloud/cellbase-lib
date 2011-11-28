package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Protein;

public interface ProteinDBAdaptor extends FeatureDBAdaptor {

	
	@Override
	public List<Protein> getAll();

	
	public List<String> getAllUniprotIds();

	public Protein getByUniprotId(String uniprotId);

	public List<Protein> getAllByUniprotIdList(List<String> uniprotIdList);
	
	public List<Protein> getAllByName(String name);

	public List<List<Protein>> getAllByNameList(List<String> nameList);
	
	public List<String> getAllEnsemblIds();

	public Protein getByEnsemblId(String ensemblId);
	
	public List<Protein> getAllByEnsemblIdList(List<String> ensemblIdList);

	public Protein getByEnsemblTranscriptId(String transcriptId);
	
	public List<Protein> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList);
	
	
}
