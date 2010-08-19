package org.bioinfo.infrared.funcannot;

import org.bioinfo.infrared.common.feature.FunctionalFeature;


public class Biocarta extends FunctionalFeature{

//	private String bioCartatId;
//	private List<String> transcrits;
//	private String description;
	
	
	public Biocarta(String bioCartatId) {
		super(bioCartatId,"");
	}
	
	public Biocarta(String bioCartatId, String name) {
		super(bioCartatId, name);
	}
	
}
