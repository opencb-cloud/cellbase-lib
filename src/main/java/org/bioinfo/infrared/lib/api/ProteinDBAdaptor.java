package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.cellbase.Protein;
import org.bioinfo.infrared.core.cellbase.ProteinFeature;
import org.bioinfo.infrared.core.cellbase.ProteinInteraction;
import org.bioinfo.infrared.core.cellbase.ProteinSequence;
import org.bioinfo.infrared.core.cellbase.ProteinXref;
import org.bioinfo.infrared.lib.common.ProteinRegion;
import org.bioinfo.infrared.lib.common.Region;

public interface ProteinDBAdaptor extends FeatureDBAdaptor {

	
	@Override
	public List<Protein> getAll();

	public List<String> getAllUniprotAccessions();
	
	public List<String> getAllUniprotNames();

	
	public List<Protein> getAllByUniprotAccession(String uniprotId);

	public List<List<Protein>> getAllByUniprotAccessionList(List<String> uniprotIdList);

	
	public List<Protein> getAllByProteinName(String name);

	public List<List<Protein>> getAllByProteinNameList(List<String> nameList);
	
	
	public List<Protein> getAllByEnsemblGene(String ensemblGene);
	
	public List<List<Protein>> getAllByEnsemblGeneList(List<String> ensemblGeneList);

	public List<Protein> getAllByEnsemblTranscriptId(String transcriptId);
	
	public List<List<Protein>> getAllByEnsemblTranscriptIdList(List<String> transcriptIdList);
	
	public List<Protein> getAllByGeneName(String geneName);
	
	public List<List<Protein>> getAllByGeneNameList(List<String> geneNameList);
	
	
	public List<ProteinSequence> getAllProteinSequenceByProteinName(String name);
	
	public List<List<ProteinSequence>> getAllProteinSequenceByProteinNameList(List<String> nameList);
	
	
	public List<ProteinFeature> getAllProteinFeaturesByUniprotId(String name);

	public List<List<ProteinFeature>> getAllProteinFeaturesByUniprotIdList(List<String> nameList);
	
	public List<ProteinFeature> getAllProteinFeaturesByGeneName(String name);

	public List<List<ProteinFeature>> getAllProteinFeaturesByGeneNameList(List<String> nameList);
	
	public List<ProteinFeature> getAllProteinFeaturesByProteinXref(String name);

	public List<List<ProteinFeature>> getAllProteinFeaturesByProteinXrefList(List<String> nameList);
	
	
	public List<ProteinInteraction> getAllProteinInteractionsByProteinName(String name);
	
	public List<ProteinInteraction> getAllProteinInteractionsByProteinName(String name, String source);

	public List<List<ProteinInteraction>> getAllProteinInteractionsByProteinNameList(List<String> nameList);
	
	public List<List<ProteinInteraction>> getAllProteinInteractionsByProteinNameList(List<String> nameList, String source);
	
	
	public List<ProteinRegion> getAllProteinRegionByGenomicRegion(Region region);
	
	public List<List<ProteinRegion>> getAllProteinRegionByGenomicRegionList(List<Region> regionList);
	
	
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name);

	public List<List<ProteinXref>> getAllProteinXrefsByProteinNameList(List<String> nameList);
	
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name, String dbname);

	public List<List<ProteinXref>> getAllProteinXrefsByProteinNameList(List<String> nameList, String dbname);
	
	public List<ProteinXref> getAllProteinXrefsByProteinName(String name, List<String> dbname);

	public List<List<ProteinXref>> getAllProteinXrefsByProteinNameList(List<String> nameList, List<String> dbname);

}
