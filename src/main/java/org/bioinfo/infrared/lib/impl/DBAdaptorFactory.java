package org.bioinfo.infrared.lib.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.bioinfo.commons.log.Logger;
import org.bioinfo.infrared.lib.api.BioPaxDBAdaptor;
import org.bioinfo.infrared.lib.api.CpGIslandDBAdaptor;
import org.bioinfo.infrared.lib.api.CytobandDBAdaptor;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.GenomeSequenceDBAdaptor;
import org.bioinfo.infrared.lib.api.GenomicVariantEffectDBAdaptor;
import org.bioinfo.infrared.lib.api.MirnaDBAdaptor;
import org.bioinfo.infrared.lib.api.MutationDBAdaptor;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.api.RegulatoryRegionDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.api.StructuralVariationDBAdaptor;
import org.bioinfo.infrared.lib.api.TfbsDBAdaptor;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.api.XRefsDBAdaptor;

public abstract class DBAdaptorFactory {

	protected Logger logger;
	
	protected static Map<String, String> speciesAlias;
	
	static {
		speciesAlias = new HashMap<String, String>(20);
		
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");speciesAlias.put("hsap", "HSAPIENS");speciesAlias.put("hsa", "HSAPIENS");
		speciesAlias.put("Mus musculus", "MMUSCULUS");speciesAlias.put("Mus_musculus", "MMUSCULUS");speciesAlias.put("mmusculus", "MMUSCULUS");speciesAlias.put("mmus", "MMUSCULUS");speciesAlias.put("mmu", "MMUSCULUS");
		speciesAlias.put("Rattus norvegicus", "RNORVEGICUS");speciesAlias.put("Rattus_norvegicus", "RNORVEGICUS");speciesAlias.put("rnorvegicus", "RNORVEGICUS");speciesAlias.put("rnor", "RNORVEGICUS");speciesAlias.put("rno", "RNORVEGICUS");
		speciesAlias.put("Danio rerio", "DRERIO");speciesAlias.put("Danio_rerio", "DRERIO");speciesAlias.put("drerio", "DRERIO");speciesAlias.put("drer", "DRERIO");speciesAlias.put("dre", "DRERIO");
		speciesAlias.put("Drosophila melanogaster", "DMELANOGASTER");speciesAlias.put("Drosophila_melanogaster", "DMELANOGASTER");speciesAlias.put("dmelanogaster", "DMELANOGASTER");speciesAlias.put("dmel", "DMELANOGASTER");speciesAlias.put("dme", "DMELANOGASTER");
		speciesAlias.put("Caenorhabditis elegans", "CELEGANS");speciesAlias.put("Caenorhabditis_elegans", "CELEGANS");speciesAlias.put("celegans", "CELEGANS");speciesAlias.put("cele", "CELEGANS");speciesAlias.put("cel", "CELEGANS");
		speciesAlias.put("Saccharomyces cerevisiae", "SCEREVISIAE");speciesAlias.put("Saccharomyces_cerevisiae", "SCEREVISIAE");speciesAlias.put("scerevisiae", "SCEREVISIAE");speciesAlias.put("scer", "SCEREVISIAE");speciesAlias.put("sce", "SCEREVISIAE");
		speciesAlias.put("Canis familiaris", "CFAMILIARIS");speciesAlias.put("Canis_familiaris", "CFAMILIARIS");speciesAlias.put("cfamiliaris", "CFAMILIARIS");speciesAlias.put("cfar", "CFAMILIARIS");speciesAlias.put("cfa", "CFAMILIARIS");
		speciesAlias.put("Sus scrofa", "SSCROFA");speciesAlias.put("Sus_scrofa", "SSCROFA");speciesAlias.put("sscrofa", "SSCROFA");speciesAlias.put("sscr", "SSCROFA");speciesAlias.put("ssc", "SSCROFA");
		speciesAlias.put("Plasmodium falciparum", "PFALCIPARUM");speciesAlias.put("Plasmodium_falciparum", "PFALCIPARUM");speciesAlias.put("pfalciparum", "PFALCIPARUM");speciesAlias.put("pfalc", "PFALCIPARUM");speciesAlias.put("pfa", "PFALCIPARUM");
		speciesAlias.put("Anopheles gambiae", "AGAMBIAE");speciesAlias.put("Anopheles_gambiae", "AGAMBIAE");speciesAlias.put("agambiae", "AGAMBIAE");speciesAlias.put("agam", "AGAMBIAE");speciesAlias.put("aga", "AGAMBIAE");
	}
	
	public DBAdaptorFactory() {
		logger = new Logger();
		logger.setLevel(Logger.ERROR_LEVEL);
	}


	public abstract void setConfiguration(Properties properties);
	
	public abstract void open(String species, String version);
	
	public abstract void close();


	public abstract GeneDBAdaptor getGeneDBAdaptor(String species);
	
	public abstract GeneDBAdaptor getGeneDBAdaptor(String species, String version);

	
	public abstract TranscriptDBAdaptor getTranscriptDBAdaptor(String species);
	
	public abstract TranscriptDBAdaptor getTranscriptDBAdaptor(String species, String version);
	

	public abstract ExonDBAdaptor getExonDBAdaptor(String species);
	
	public abstract ExonDBAdaptor getExonDBAdaptor(String species, String version);
	

//	public abstract GenomicRegionFeatureDBAdaptor getFeatureMapDBAdaptor(String species);
//	
//	public abstract GenomicRegionFeatureDBAdaptor getFeatureMapDBAdaptor(String species, String version);
	
	
	public abstract GenomicVariantEffectDBAdaptor getGenomicVariantEffectDBAdaptor(String species);
	
	public abstract GenomicVariantEffectDBAdaptor getGenomicVariantEffectDBAdaptor(String species, String version);
	
	
	public abstract ProteinDBAdaptor getProteinDBAdaptor(String species);
	
	public abstract ProteinDBAdaptor getProteinDBAdaptor(String species, String version);
	
	
	public abstract SnpDBAdaptor getSnpDBAdaptor(String species);
	
	public abstract SnpDBAdaptor getSnpDBAdaptor(String species,  String version);

	
	public abstract GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species);
	
	public abstract GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species, String version);


	public abstract GeneDBAdaptor getChromosomeDBAdaptor(String species);

	


	public abstract CytobandDBAdaptor getCytobandDBAdaptor(String species);
	
	public abstract CytobandDBAdaptor getCytobandDBAdaptor(String species, String version);


	public abstract XRefsDBAdaptor getXRefDBAdaptor(String species);
	
	public abstract XRefsDBAdaptor getXRefDBAdaptor(String species, String version);
	
	
	public abstract TfbsDBAdaptor getTfbsDBAdaptor(String species);
	
	public abstract TfbsDBAdaptor getTfbsDBAdaptor(String species, String version);


	public abstract RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species);
	
	public abstract RegulatoryRegionDBAdaptor getRegulatoryRegionDBAdaptor(String species, String version);
	
	
	public abstract MirnaDBAdaptor getMirnaDBAdaptor(String species);
	
	public abstract MirnaDBAdaptor getMirnaDBAdaptor(String species, String version);


	public abstract BioPaxDBAdaptor getBioPaxDBAdaptor(String species);
	
	public abstract BioPaxDBAdaptor getBioPaxDBAdaptor(String species, String version);


	public abstract MutationDBAdaptor getMutationDBAdaptor(String species);
	
	public abstract MutationDBAdaptor getMutationDBAdaptor(String species, String version);

	
	public abstract CpGIslandDBAdaptor getCpGIslandDBAdaptor(String species);
	
	public abstract CpGIslandDBAdaptor getCpGIslandDBAdaptor(String species, String version);
	
	
	public abstract StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(String species);
	
	public abstract StructuralVariationDBAdaptor getStructuralVariationDBAdaptor(String species, String version);
	
}
