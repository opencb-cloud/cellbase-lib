package org.bioinfo.infrared.lib.impl;

import java.util.Map;
import java.util.Properties;

import org.bioinfo.infrared.lib.api.GeneDBAdaptor;

public abstract class DBAdaptorFactory {

	protected static Map<String, String> speciesAlias;

	static {

		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");
		speciesAlias.put("Homo sapiens", "HSAPIENS");speciesAlias.put("Homo_sapiens", "HSAPIENS");speciesAlias.put("hsapiens", "HSAPIENS");

	}


	public abstract void setConfiguration(Properties properties);


	public abstract GeneDBAdaptor getGeneDBAdaptor(String species);

	public abstract GeneDBAdaptor getTranscriptDBAdaptor(String species);

	public abstract GeneDBAdaptor getExonDBAdaptor(String species);

	public abstract GeneDBAdaptor getProteinDBAdaptor(String species);

	public abstract GeneDBAdaptor getSnpDBAdaptor(String species);

	public abstract GeneDBAdaptor getCytobandDBAdaptor(String species);

	public abstract GeneDBAdaptor getChromosomeDBAdaptor(String species);

	public abstract GeneDBAdaptor getGenomeDBAdaptor(String species);

	public abstract GeneDBAdaptor getXRefDBAdaptor(String species);

}
