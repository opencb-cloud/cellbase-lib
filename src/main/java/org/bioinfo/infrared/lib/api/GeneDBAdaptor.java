package org.bioinfo.infrared.lib.api;

import java.util.List;

import org.bioinfo.infrared.core.Gene;

public interface GeneDBAdaptor extends FeatureDBAdaptor {

	
	public List<Gene> getGeneById(String geneId);
	
	public List<List<Gene>> getGeneByIdList(List<String> geneIdList);
	
}
