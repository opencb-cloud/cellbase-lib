package org.bioinfo.infrared.lib.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.bioinfo.commons.log.Logger;
import org.bioinfo.infrared.lib.api.CytobandDBAdaptor;
import org.bioinfo.infrared.lib.api.ExonDBAdaptor;
import org.bioinfo.infrared.lib.api.GenomicRegionFeatureDBAdaptor;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.api.ProteinDBAdaptor;
import org.bioinfo.infrared.lib.api.SnpDBAdaptor;
import org.bioinfo.infrared.lib.api.TranscriptDBAdaptor;
import org.bioinfo.infrared.lib.impl.hibernate.GenomeSequenceDBAdaptor;

public abstract class DBAdaptorFactory {

	protected Logger logger;
	
	protected static Map<String, String> speciesAlias;
	
	static {
		speciesAlias = new HashMap<String, String>(20);
		
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");speciesAlias.put("hsap", "HSAPIENS");speciesAlias.put("hsa", "HSAPIENS");
		speciesAlias.put("Mus musculus", "MMUSCULUS");speciesAlias.put("Mus_musculus", "MMUSCULUS");speciesAlias.put("mmusculus", "MMUSCULUS");speciesAlias.put("mmus", "MMUSCULUS");speciesAlias.put("mmu", "MMUSCULUS");
		speciesAlias.put("Rattus norvegicus", "RNORVEGICUS");speciesAlias.put("Rattus_norvegicus", "RNORVEGICUS");speciesAlias.put("rnorvegicus", "RNORVEGICUS");speciesAlias.put("rnor", "RNORVEGICUS");speciesAlias.put("rno", "RNORVEGICUS");
		speciesAlias.put("Danio rerio", "DRERIO");speciesAlias.put("Danio_rerio", "DRERIO");speciesAlias.put("drerio", "DRERIO");speciesAlias.put("drer", "DRERIO");speciesAlias.put("dre", "DRERIO");

	}
	
	public DBAdaptorFactory() {
		logger = new Logger();
		logger.setLevel(Logger.ERROR_LEVEL);
	}


	public abstract void setConfiguration(Properties properties);
	
	public abstract void close();


	public abstract GeneDBAdaptor getGeneDBAdaptor(String species);
	
	public abstract GeneDBAdaptor getGeneDBAdaptor(String species, String version);

	
	public abstract TranscriptDBAdaptor getTranscriptDBAdaptor(String species);
	
	public abstract TranscriptDBAdaptor getTranscriptDBAdaptor(String species, String version);
	

	public abstract ExonDBAdaptor getExonDBAdaptor(String species);
	
	public abstract ExonDBAdaptor getExonDBAdaptor(String species, String version);
	

	public abstract GenomicRegionFeatureDBAdaptor getFeatureMapDBAdaptor(String species);
	
	public abstract GenomicRegionFeatureDBAdaptor getFeatureMapDBAdaptor(String species, String version);
	
	
	public abstract ProteinDBAdaptor getProteinDBAdaptor(String species);
	
	public abstract ProteinDBAdaptor getProteinDBAdaptor(String species, String version);
	
	
	public abstract SnpDBAdaptor getSnpDBAdaptor(String species);
	
	public abstract SnpDBAdaptor getSnpDBAdaptor(String species,  String version);

	
	public abstract GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species);
	
	public abstract GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species, String version);


	public abstract GeneDBAdaptor getChromosomeDBAdaptor(String species);

	
	public abstract GeneDBAdaptor getXRefDBAdaptor(String species);


	public abstract CytobandDBAdaptor getCytobandDBAdaptor(String species);
	
	public abstract CytobandDBAdaptor getCytobandDBAdaptor(String species, String version);

	
}
