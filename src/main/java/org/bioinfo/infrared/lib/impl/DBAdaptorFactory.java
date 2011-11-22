package org.bioinfo.infrared.lib.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.bioinfo.commons.log.Logger;
import org.bioinfo.infrared.lib.api.GeneDBAdaptor;
import org.bioinfo.infrared.lib.impl.hibernate.GenomeSequenceDBAdaptor;

public abstract class DBAdaptorFactory {

	protected Logger logger;
	
	protected static Map<String, String> speciesAlias;
	
	static {
		speciesAlias = new HashMap<String, String>(20);
		
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Mus musculus", "MMUSCULUS");speciesAlias.put("Mus_musculus", "MMUSCULUS");speciesAlias.put("mmusculus", "MMUSCULUS");
		speciesAlias.put("Rattus norvegicus", "RNORVEGICUS");speciesAlias.put("Rattus_norvegicus", "RNORVEGICUS");speciesAlias.put("rnorvegicus", "RNORVEGICUS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Danio rerio", "DRERIO");speciesAlias.put("Danio_rerio", "DRERIO");speciesAlias.put("drerio", "DRERIO");

	}
	
	public DBAdaptorFactory() {
		logger = new Logger();
		logger.setLevel(Logger.DEBUG_LEVEL);
	}


	public abstract void setConfiguration(Properties properties);
	
	public abstract void close();


	public abstract GeneDBAdaptor getGeneDBAdaptor(String species);
	
	public abstract GeneDBAdaptor getGeneDBAdaptor(String species, String version);

	
	public abstract GeneDBAdaptor getTranscriptDBAdaptor(String species);

	public abstract GeneDBAdaptor getExonDBAdaptor(String species);

	public abstract GeneDBAdaptor getProteinDBAdaptor(String species);

	public abstract GeneDBAdaptor getSnpDBAdaptor(String species);

	public abstract GeneDBAdaptor getCytobandDBAdaptor(String species);

	public abstract GeneDBAdaptor getChromosomeDBAdaptor(String species);

	
	public abstract GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species);
	
	public abstract GenomeSequenceDBAdaptor getGenomeSequenceDBAdaptor(String species, String version);

	
	public abstract GeneDBAdaptor getXRefDBAdaptor(String species);

	
}
